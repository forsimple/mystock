package me.wh.stock.admin.actions.ticket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;

/**
 * 股票管理菜单。
 */
@Controller("ticket.menu")
@RequestMapping("/ticket")
@Auth(AdminPermission.CODE)
public class MenuAction {
	/**
	 * 查看菜单。
	 */
	@RequestMapping("menu")
	public void menu() {
	}
}
