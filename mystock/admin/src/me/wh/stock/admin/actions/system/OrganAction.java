package me.wh.stock.admin.actions.system;

import java.util.List;

import javax.annotation.Resource;

import me.wh.stock.core.entity.Organ;
import me.wh.stock.core.service.SecurityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.message.MessageSource;
import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;
import coo.mvc.dwz.DwzResultBuild;

/**
 * 机构管理
 */
@Controller("system.organ")
@RequestMapping("/system")
@Auth(AdminPermission.CODE)
public class OrganAction {
	@Resource
	private SecurityService securityService;
	@Resource
	private MessageSource messageSource;

	/**
	 * 查看机构列表。
	 * 
	 * @param model
	 *            数据模型
	 * @param selectedOrganId
	 *            选中的机构ID
	 */
	@RequestMapping("organ-list")
	public void list(Model model, String selectedOrganId) {
		Organ rootOrgan = securityService.getRootOrgan();
		if (selectedOrganId == null) {
			selectedOrganId = rootOrgan.getId();
		}
		model.addAttribute("selectedOrganId", selectedOrganId);
		model.addAttribute("rootOrgan", rootOrgan);
	}

	/**
	 * 新增机构。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("organ-add")
	public void add(Model model) {
		Organ rootOrgan = securityService.getRootOrgan();
		Organ organ = new Organ();
		organ.setParent(rootOrgan);
		model.addAttribute("parentOrgans", rootOrgan.getChildTree());
		model.addAttribute("rootOrgan", rootOrgan);
		model.addAttribute(organ);
	}

	/**
	 * 保存机构
	 * 
	 * @param organ
	 *            机构
	 * @return 返回提示信息。
	 */
	@RequestMapping("organ-save")
	public ModelAndView save(Organ organ) {
		securityService.createOrgan(organ);
		return new DwzResultBuild().success("organ.add.success").closeDialog()
				.reloadNavTab("selectedOrganId=" + organ.getId()).build();
	}

	/**
	 * 编辑机构。
	 * 
	 * @param model
	 *            数据模型
	 * @param organ
	 *            机构
	 */
	@RequestMapping("organ-edit")
	public void edit(Model model, Organ organ) {
		Organ rootOrgan = securityService.getRootOrgan();
		model.addAttribute("rootOrgan", rootOrgan);
		List<Organ> parentOrgans = rootOrgan.getChildTree();
		parentOrgans.remove(organ);
		model.addAttribute("parentOrgans", parentOrgans);
	}

	/**
	 * 更新机构。
	 * 
	 * @param organ
	 *            机构
	 * @return 返回提示信 息。
	 */
	@RequestMapping("organ-update")
	public ModelAndView update(Organ organ) {
		securityService.updateOrgan(organ);
		return new DwzResultBuild().success("organ.edit.success")
				.reloadNavTab("selectedOrganId=" + organ.getId()).build();
	}

	/**
	 * 删除机构。
	 * 
	 * @param organ
	 *            机构
	 * @return 返回提示信息。
	 */
	@RequestMapping("organ-delete")
	public ModelAndView delete(Organ organ) {
		securityService.deleteOrgan(organ);
		return new DwzResultBuild()
				.success("organ.delete.success")
				.closeDialog()
				.reloadNavTab(
						"selectedOrganId="
								+ securityService.getCurrentOrgan().getId())
				.build();
	}

	/**
	 * 启用机构。
	 * 
	 * @param organ
	 *            机构
	 * @return 返回提示信息。
	 */
	@RequestMapping("organ-enable")
	public ModelAndView enable(Organ organ) {
		securityService.enableOrgan(organ);
		return new DwzResultBuild().success("organ.enable.success")
				.reloadNavTab("selectedOrganId=" + organ.getId()).build();
	}

	/**
	 * 停用机构。
	 * 
	 * @param organ
	 *            机构
	 * @return 返回提示信息。
	 */
	@RequestMapping("organ-disable")
	public ModelAndView disable(Organ organ) {
		securityService.disableOrgan(organ);
		return new DwzResultBuild().success("organ.disable.success")
				.reloadNavTab("selectedOrganId=" + organ.getId()).build();
	}
}