package me.wh.stock.admin.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import me.wh.stock.admin.entity.Ticket;
import me.wh.stock.admin.timer.HfqHistoryThread;

import org.apache.lucene.search.SortField;
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
    @Value("${sina.allticket.url}")
    private String url;
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
                if (values.length != 16) {
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
        List<Ticket> groupTicket=new ArrayList<Ticket>();
        for(int i=0;i<all.size();i++){
            groupTicket.add(all.get(i));
            if(groupTicket.size()%10==0){
                CountDownLatch latch=new CountDownLatch(groupTicket.size());//两个工人的协作 
                try {
                    for(int j=0;j<groupTicket.size();j++){
                        exec.execute(new HfqHistoryThread(groupTicket.get(j), latch));
                    }
                    latch.await();
                    groupTicket.clear();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
        if(groupTicket.size()>0){
            CountDownLatch latch=new CountDownLatch(groupTicket.size());//两个工人的协作 
            try {
                for(int j=0;j<groupTicket.size();j++){
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
        boolean result = false;

        return result;
    }

    public boolean syncAllTicketHfqHistoryToday() {
        boolean result = false;

        return result;
    }

}
