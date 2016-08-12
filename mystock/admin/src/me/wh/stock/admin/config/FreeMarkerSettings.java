package me.wh.stock.admin.config;

import org.springframework.stereotype.Component;

import coo.mvc.config.AbstractFreeMarkerSettings;

/**
 * FreeMarker设置组件。
 */
@Component("coo.boot.main.config.FreeMarkerSettings")
public class FreeMarkerSettings extends AbstractFreeMarkerSettings {
	/**
	 * 构造方法。
	 */
	public FreeMarkerSettings() {
		setOrder(100);
		addTemplatePath("classpath:/me/wh/stock/admin/actions/");
		addTemplatePath("classpath:/me/wh/stock/admin/macros/");
        addAutoImport("sys", "sys.ftl");
	}
}