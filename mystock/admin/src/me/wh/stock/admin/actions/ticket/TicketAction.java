package me.wh.stock.admin.actions.ticket;

import javax.annotation.Resource;

import me.wh.stock.admin.entity.Ticket;
import me.wh.stock.admin.service.TicketService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.base.model.Page;
import coo.core.model.SearchModel;
import coo.core.security.annotations.Auth;
import coo.mvc.dwz.DwzResultBuild;

@Controller
@RequestMapping("/ticket")
@Auth
public class TicketAction {

    @Resource
    private TicketService ticketServcie;

    @RequestMapping("ticket-sync")
    public ModelAndView initTicket() {
       ticketServcie.updateAllTicket();
       return new DwzResultBuild().success("ticket.sync.success")
               .reloadNavTab().build();
    }
    @RequestMapping("ticket-list")
    public void list(Model model, SearchModel searchModel) {
        Page<Ticket> page= ticketServcie.searchTicket(searchModel);
        model.addAttribute("ticketPage",page );
    }
    
}
