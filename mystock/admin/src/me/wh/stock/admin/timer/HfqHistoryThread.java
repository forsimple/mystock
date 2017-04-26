package me.wh.stock.admin.timer;

import java.util.concurrent.CountDownLatch;

import me.wh.stock.admin.entity.Ticket;
import me.wh.stock.admin.service.TicketService;
import coo.core.util.SpringUtils;

public class HfqHistoryThread implements Runnable {
    private Ticket ticket;
    private CountDownLatch latch;

    public HfqHistoryThread(Ticket ticket, CountDownLatch latch) {
       this.ticket=ticket;
       this.latch=latch;
    }

    @Override
    public void run() {
        TicketService service= (TicketService) SpringUtils.getBean("ticketService");
        service.syncTicketHfqHistory(ticket);
        latch.countDown();
    }

    
    
    
}
