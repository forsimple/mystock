package admin;

import java.io.IOException;
import java.net.MalformedURLException;

import me.wh.stock.admin.service.TicketService;
import me.wh.stock.admin.timer.TicketTimerService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

 
public class TicketServiceTest   {
    @Autowired
    private   TicketService ticketService;
    @Value("sina.allticket.url")
    private String url;
  
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
