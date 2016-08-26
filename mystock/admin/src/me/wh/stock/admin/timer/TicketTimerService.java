package me.wh.stock.admin.timer;

import me.wh.stock.admin.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TicketTimerService {

    @Autowired
    private TicketService ticketServcie;
    
    @Scheduled(cron="0 30 15 * * ?")
    public void syscTicket(){
        ticketServcie.updateAllTicket();
    }
    
}
