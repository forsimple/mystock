package admin;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.annotation.Resource;

import me.wh.stock.admin.entity.Ticket;
import me.wh.stock.admin.service.TicketService;
import me.wh.stock.admin.timer.TicketTimerService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import comm.BaseTest;

import coo.core.hibernate.dao.Dao;

 
public class TicketServiceTest  extends BaseTest {

    @Value("sina.allticket.url")
    private String url;
    @Resource
    private TicketService ticketServcie;
    @Resource
    protected Dao<Ticket> ticketDao;
    
    @Test
    public void synchfqTest(){
        Ticket t =ticketDao.get("000002");
        ticketServcie.syncTicketHfqHistory(t );
    }
  
    @Test
    public void saveTest() throws MalformedURLException, IOException{
        
        ClassPathXmlApplicationContext content=new ClassPathXmlApplicationContext("app-context.xml");
        TicketTimerService service = content.getBean(TicketTimerService.class);
        service.syscTicket();
        
      /*  InputStream in=new URL(url).openConnection().getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(in,"GBK"));
        String line;
        while((line=reader.readLine()) !=null){
            System.out.println(line);
        }*/
    }

}
