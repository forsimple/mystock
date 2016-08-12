package me.wh.stock.admin.actions.ticket;

import javax.annotation.Resource;

import me.wh.stock.admin.service.TicketService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.core.security.annotations.Auth;

@Controller
@RequestMapping("/ticket")
@Auth
public class TicketAction {

    @Resource
    private TicketService ticketServcie;

    @RequestMapping("init")
    public void initTicket() {
       ticketServcie.updateAllTicket();
    }
}
