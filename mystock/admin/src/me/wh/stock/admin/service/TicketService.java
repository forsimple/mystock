package me.wh.stock.admin.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import me.wh.stock.admin.entity.HFQHistory;
import me.wh.stock.admin.entity.HFQHistoryKey;
import me.wh.stock.admin.entity.Ticket;
import me.wh.stock.admin.timer.HfqHistoryThread;
import me.wh.stock.admin.util.JsoupUtils;
import me.wh.stock.core.util.JacksonUtil;

import org.apache.lucene.search.SortField;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coo.base.model.Page;
import coo.base.util.BeanUtils;
import coo.base.util.CollectionUtils;
import coo.base.util.DateUtils;
import coo.core.hibernate.dao.Dao;
import coo.core.hibernate.search.FullTextCriteria;
import coo.core.model.SearchModel;

@Service
public class TicketService {

    @Resource
    protected Dao<Ticket> ticketDao;
    @Resource
    protected Dao<HFQHistory> hFQHistoryDao;

    @Value("${sina.allticket.url}")
    private String url;
    @Value("${sina.houfuquantrade.url}")
    private String hfqUrlFormatter;
    private static final Logger LOG = LoggerFactory.getLogger(TicketService.class.getName());

    public boolean saveOrUpdate(Ticket t) {
        if (t == null || t.getId() == null) {
            return false;
        }
        Ticket dbticket = ticketDao.get(t.getId());
        if (dbticket == null) {
            ticketDao.save(t);
        } else {
            BeanUtils.copyFields(t, dbticket);
            ticketDao.update(dbticket);
        }
        return true;
    }

    @Transactional
    public void updateAllTicket() {

        try {
            InputStream in = new URL(url).openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));
            String line;
            int num = 0;
            while ((line = reader.readLine()) != null) {
                num++;
                if (num < 2) {
                    continue;
                }
                String[] values = line.split(",");
                if (values.length < 16) {
                    continue;
                }
                // code,name,industry,area,pe,outstanding,totals,totalAssets,liquidAssets,fixedAssets,reserved,reservedPerShare,esp,bvps,pb,timeToMarket
                Ticket t = new Ticket();
                t.setId(values[0]);
                t.setName(values[1]);
                t.setIndustry(values[2]);
                t.setArea(values[3]);
                t.setPe(Double.parseDouble(values[4]));
                t.setOutstanding(Double.parseDouble(values[5]));
                t.setTotals(Double.parseDouble(values[6]));
                t.setTotalAssets(Double.parseDouble(values[7]));
                t.setLiquidAssets(Double.parseDouble(values[8]));
                t.setFixedAssets(Double.parseDouble(values[9]));
                t.setReserved(Double.parseDouble(values[10]));
                t.setReservedPerShare(Double.parseDouble(values[11]));
                t.setEsp(Double.parseDouble(values[12]));
                t.setBvps(Double.parseDouble(values[13]));
                t.setPb(Double.parseDouble(values[14]));
                if (values[15] != null && values[15].length() == 8) {
                    t.setTimeToMarket(DateUtils.parse(values[15]));
                }
                t.setUpdateTime(new Date());
                saveOrUpdate(t);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = true)
    public Page<Ticket> searchTicket(SearchModel searchModel) {
        FullTextCriteria criteria = ticketDao.createFullTextCriteria();
        criteria.addSortDesc("timeToMarket", SortField.Type.STRING);
        Page<Ticket> page = ticketDao.searchPage(criteria, searchModel);
        return page;
    }

    public boolean syncAllTicketHfqHistory(Ticket t) {
        boolean result = false;
        List<Ticket> all = ticketDao.getAll();
        if (CollectionUtils.isEmpty(all)) {
            return true;
        }

        final ExecutorService exec = Executors.newFixedThreadPool(10);
        List<Ticket> groupTicket = new ArrayList<Ticket>();
        for (int i = 0; i < all.size(); i++) {
            groupTicket.add(all.get(i));
            if (groupTicket.size() % 10 == 0) {
                CountDownLatch latch = new CountDownLatch(groupTicket.size());
                try {
                    for (int j = 0; j < groupTicket.size(); j++) {
                        exec.execute(new HfqHistoryThread(groupTicket.get(j), latch));
                    }
                    latch.await();
                    groupTicket.clear();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (groupTicket.size() > 0) {
            CountDownLatch latch = new CountDownLatch(groupTicket.size());// 两个工人的协作
            try {
                for (int j = 0; j < groupTicket.size(); j++) {
                    exec.execute(new HfqHistoryThread(groupTicket.get(j), latch));
                }
                latch.await();
                groupTicket.clear();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOG.info("同步完成。。。");
        return result;
    }

    public boolean syncTicketHfqHistory(Ticket t) {
        return syncTicketHfqHistory(t, null, null);
    }

    public boolean syncTicketHfqHistory(Ticket t, Date start, Date end) {

        if (start == null) {
            start = t.getTimeToMarket();
        }
        if (end == null) {
            end = new Date();
        }
        boolean result = true;
        String id = t.getId();

        // String
        // url=String.format(TicketConstant.LIVE_DATA_URL,"http://",TicketConstant.pageMap.get("sinahq",id,);
        List<Integer[]> seasons = getSeasons(start, end);
        List<HFQHistory> allHis = new ArrayList<HFQHistory>();
        if (seasons != null && seasons.size() > 0) {
            for (Integer[] s : seasons) {
                String hfqUrl = String.format(hfqUrlFormatter, "http://", id, s[0], s[1]);
                List<List<String>> results = JsoupUtils.getTableById(hfqUrl, "FundHoldSharesTable", 2);
                if (CollectionUtils.isEmpty(results)) {
                    return result;
                }
                for (List<String> row : results) {
                    HFQHistory his = new HFQHistory();
                    HFQHistoryKey key = new HFQHistoryKey();
                    his.setKey(key);
                    key.setTradeDate(DateUtils.parse(row.get(0)));
                    key.setCode(id);

                    his.setOpen(Double.parseDouble(row.get(1)));
                    his.setHigh(Double.parseDouble(row.get(2)));
                    his.setClose(Double.parseDouble(row.get(3)));
                    his.setLow(Double.parseDouble(row.get(4)));
                    his.setVolume(Double.parseDouble(row.get(5)));
                    his.setAmount(Double.parseDouble(row.get(6)));
                    his.setFactor(Double.parseDouble(row.get(6)));
                    allHis.add(his);
                }

            }

        }

        if (allHis.size() > 0) {
            for (HFQHistory his : allHis) {
                if (hFQHistoryDao.get(his.getKey()) == null) {
                    hFQHistoryDao.save(his);
                }
            }
        }

        return result;
    }

    public List<Integer[]> getSeasons(Date start, Date end) {

        List<Integer[]> res = new ArrayList<Integer[]>();
        DateTime startTime = new DateTime(start);
        DateTime endTime = new DateTime(end);

        Integer[] firstarr = new Integer[2];
        firstarr[0] = startTime.getYear();
        firstarr[1] = (startTime.getMonthOfYear() - 1) / 3 + 1;
        res.add(firstarr);

        int endSeason = (endTime.getMonthOfYear() - 1) / 3 + 1;

        while (true) {
            int startSeason = (startTime.getMonthOfYear() - 1) / 3 + 1;
            System.out.println(startTime.getYear() + "\t" + startSeason);
            if (startTime.getYear() == endTime.getYear() && startSeason == endSeason) {
                return res;
            } else {
                startTime = startTime.plusMonths(3);
                Integer[] arr = new Integer[2];
                arr[0] = startTime.getYear();
                arr[1] = (startTime.getMonthOfYear() - 1) / 3 + 1;
                res.add(arr);
            }
        }
    }

    @Test
    public void testGetSeasons() {
        DateTime start = new DateTime("2016-07-11");
        String json = JacksonUtil.toJson(getSeasons(start.toDate(), new DateTime().toDate()));
        System.out.println(json);
    }

    public static void main(String[] args) {
        DateTime startTime = new DateTime();
        startTime = startTime.plusMonths(3);
        Integer[] arr = new Integer[2];
        arr[0] = startTime.getYear();
        arr[1] = (startTime.getMonthOfYear() - 1) / 3 + 1;

        System.out.println(startTime.getYear() + "\t" + startTime.getMonthOfYear());

        TicketService service = new TicketService();
        DateTime start = new DateTime("2016-08-11");
        List<Integer[]> res = service.getSeasons(start.toDate(), new DateTime().toDate());
        System.out.println(res);

    }

}
