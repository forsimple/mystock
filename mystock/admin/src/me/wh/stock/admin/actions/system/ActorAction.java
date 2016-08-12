package me.wh.stock.admin.actions.system;

import javax.annotation.Resource;

import me.wh.stock.core.entity.Actor;
import me.wh.stock.core.entity.User;
import me.wh.stock.core.service.SecurityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;
import coo.mvc.dwz.DwzResultBuild;

/**
 * 职务管理。
 */
@Controller
@RequestMapping("/system")
@Auth(AdminPermission.CODE)
public class ActorAction {
	@Resource
	private SecurityService securityService;

	/**
	 * 查看职务列表。
	 * 
	 * @param model
	 *            数据模型
	 * @param user
	 *            关联用户
	 */
	@RequestMapping("actor-list")
	public void list(Model model, User user) {
		model.addAttribute(user);
	}

	/**
	 * 新增职务。
	 * 
	 * @param model
	 *            数据模型
	 * @param user
	 *            关联用户
	 */
	@RequestMapping("actor-add")
	public void add(Model model, User user) {
		Actor actor = new Actor();
		actor.setUser(user);
		model.addAttribute(actor);
		model.addAttribute("rootOrgan", securityService.getCurrentOrgan());
		model.addAttribute("roles", securityService.getAllRole());
	}

	/**
	 * 保存职务。
	 * 
	 * @param actor
	 *            职务
	 * @return 返回提示信息。
	 */
	@RequestMapping("actor-save")
	public ModelAndView save(Actor actor) {
		securityService.createActor(actor);
		return new DwzResultBuild().success("actor.add.success").closeDialog()
				.reloadDialog("actor-list", "", "").build();
	}

	/**
	 * 编辑职务。
	 * 
	 * @param model
	 *            数据模型
	 * @param actor
	 *            职务
	 */
	@RequestMapping("actor-edit")
	public void edit(Model model, Actor actor) {
		model.addAttribute("rootOrgan", securityService.getCurrentUser()
				.getDefaultActor().getOrgan());
		model.addAttribute("roles", securityService.getAllRole());
	}

	/**
	 * 更新职务。
	 * 
	 * @param actor
	 *            职务
	 * @return 返回提示信息。
	 */
	@RequestMapping("actor-update")
	public ModelAndView update(Actor actor) {
		securityService.updateActor(actor);
		return new DwzResultBuild().success("actor.edit.success").closeDialog()
				.reloadDialog("actor-list", "", "").reloadNavTab().build();
	}

	/**
	 * 删除职务。
	 * 
	 * @param actor
	 *            职务
	 * @return 返回提示信息。
	 */
	@RequestMapping("actor-delete")
	public ModelAndView delete(Actor actor) {
		securityService.deleteActor(actor);
		return new DwzResultBuild().success("actor.delete.success")
				.reloadDialog().build();
	}

	/**
	 * 设置默认职务。
	 * 
	 * @param actor
	 *            职务
	 * @return 返回提示信息。
	 */
	@RequestMapping("actor-set-default")
	public ModelAndView setDefault(Actor actor) {
		securityService.setDefaultActor(actor);
		return new DwzResultBuild().success("actor.set.default.success")
				.reloadDialog().reloadNavTab().build();
	}
}
