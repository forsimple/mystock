package me.wh.stock.admin.actions.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.hibernate.cache.EntityCacheManager;
import coo.core.hibernate.search.EntityIndexManager;
import coo.core.message.MessageSource;
import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;
import coo.mvc.dwz.DwzResultBuild;

/**
 * 系统维护。
 */
@Controller
@RequestMapping("/system")
@Auth(AdminPermission.CODE)
public class RepairedAction {
	@Resource
	private EntityIndexManager entityIndexManager;
	@Resource
	private EntityCacheManager entityCacheManager;
	@Resource
	private MessageSource messageSource;

	/**
	 * 查看全文索引实体类列表页面。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("indexed-entity-list")
	public void listIndexedEntity(Model model) {
		model.addAttribute("indexedEntityClasses",
				entityIndexManager.getIndexedEntityClasses());
	}

	/**
	 * 重建实体类全文索引。
	 * 
	 * @param indexedEntityClasses
	 *            实体类列表
	 * @return 返回操作成功信息。
	 */
	@RequestMapping("indexed-entity-build")
	public ModelAndView buildIndexedEntity(Class<?>[] indexedEntityClasses) {
		entityIndexManager.startAndWait(indexedEntityClasses);
		return new DwzResultBuild().success("indexed.entity.build.success")
				.reloadNavTab().build();
	}

	/**
	 * 查看缓存实体类列表页面。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("cached-entity-list")
	public void listCachedEntity(Model model) {
		model.addAttribute("cachedEntityClasses",
				entityCacheManager.getCachedEntityClasses());
	}

	/**
	 * 清理实体类缓存。
	 * 
	 * @param cachedEntityClasses
	 *            实体类列表
	 * @return 返回操作成功信息。
	 */
	@RequestMapping("cached-entity-evict")
	public ModelAndView evictCachedEntity(Class<?>[] cachedEntityClasses) {
		entityCacheManager.evictEntityRegion(cachedEntityClasses);
		return new DwzResultBuild().success("cached.entity.evict.success")
				.reloadNavTab().build();
	}

	/**
	 * 清空所有实体缓存。
	 * 
	 * @return 返回操作成功信息。
	 */
	@RequestMapping("entity-regions-evict")
	public ModelAndView evictEntityRegions() {
		entityCacheManager.evictEntityRegions();
		return new DwzResultBuild().success("entity.regions.evict.success")
				.reloadNavTab().build();
	}

	/**
	 * 清空所有集合缓存。
	 * 
	 * @return 返回操作成功信息。
	 */
	@RequestMapping("collection-regions-evict")
	public ModelAndView evictCollectionRegions() {
		entityCacheManager.evictCollectionRegions();
		return new DwzResultBuild().success("collection.regions.evict.success")
				.reloadNavTab().build();
	}

	/**
	 * 清空所有查询缓存。
	 * 
	 * @return 返回操作成功信息。
	 */
	@RequestMapping("query-regions-evict")
	public ModelAndView evictQueryRegions() {
		entityCacheManager.evictQueryRegions();
		return new DwzResultBuild().success("query.regions.evict.success")
				.reloadNavTab().build();
	}

	/**
	 * 清空所有缓存。
	 * 
	 * @return 返回操作成功信息。
	 */
	@RequestMapping("all-regions-evict")
	public ModelAndView evictAllRegions() {
		entityCacheManager.evictAllRegions();
		return new DwzResultBuild().success("all.regions.evict.success")
				.reloadNavTab().build();
	}
}
