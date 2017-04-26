package admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.wh.stock.admin.Const.TicketConstant;
import me.wh.stock.admin.util.JsoupUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class JsoupTest {
    
    
    
    @Test
    public void parseTableTest(){
        String urlFormat="http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_FuQuanMarketHistory/stockid/%s.phtml?year=%d&jidu=%d";
        try {
            Document doc = Jsoup.connect(String.format(urlFormat, "000002",2017,1)).get();
            Elements trs=doc.select("#FundHoldSharesTable tr");
            if(trs!=null){
                List<List<String>> results=new ArrayList<List<String>>();
               Iterator< Element > it=   trs.iterator();
                while(it.hasNext()){
                    List<String>  oneRow=new ArrayList< String >();
                    Element tr=it.next();
                    for(Element td :tr.children()){
                        System.out.print ( td.text()+"\t");
                    }
                    System.out.println("  "+tr.children().size());
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getTableTest(){
        System.out.println(String.format(TicketConstant.SINA_DAY_PRICE_URL, "http://",TicketConstant.domainMap.get("vsf"),TicketConstant.pageMap.get("jv"),1));
        String urlFormat="http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_FuQuanMarketHistory/stockid/%s.phtml?year=%d&jidu=%d";
        JsoupUtils.getTableById(String.format(urlFormat, "000002",2016,3), "FundHoldSharesTable", 1);
    }

}
