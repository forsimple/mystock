package me.wh.stock.admin.timer;

import javax.annotation.Resource;

import me.wh.stock.admin.service.TicketService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TicketTimerService {

    @Resource
    private TicketService ticketServcie;
    
    @Scheduled(cron="0 04 21 * * ?")
    public void syscTicket(){
        System.out.println("start.........");
        ticketServcie.updateAllTicket();
    }
    
}
