package me.wh.stock.admin.timer;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import me.wh.stock.admin.entity.Ticket;
import me.wh.stock.admin.service.TicketService;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import coo.core.hibernate.dao.Dao;

@Component
@Lazy(false)
public class TicketTimerService {

    @Resource
    private TicketService ticketServcie;
    
    @Resource
    protected Dao<Ticket> ticketDao;
    
    @Scheduled(cron="0 33 15 * * ?")
    public void syscTicket(){
        System.out.println("start.........");
        ticketServcie.updateAllTicket();
        System.out.println("end.........");
    }
    
    @Scheduled(cron="0 53 14 * * ?")
    @Transactional
    public void syscHfthistory(){
        System.out.println("start sync HFQ数据.........");
        ticketServcie.syncAllTicketHfqHistory( );
        
        System.out.println("end sync HFQ数据.........");
    }
   

    public TicketService getTicketServcie() {
        return ticketServcie;
    }

    public void setTicketServcie(TicketService ticketServcie) {
        this.ticketServcie = ticketServcie;
    }
    
    
}
