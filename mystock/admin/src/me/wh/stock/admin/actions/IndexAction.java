package me.wh.stock.admin.actions;

import javax.annotation.Resource;

import me.wh.stock.core.service.SecurityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.core.security.annotations.Auth;

/**
 * 主页。
 */
@Controller
@RequestMapping("/")
@Auth
public class IndexAction {
	@Resource
	private SecurityService securityService;

	/**
	 * 查看主页。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("index")
	public void index(Model model) {
		model.addAttribute("currentUser", securityService.getCurrentUser());
	}
}
