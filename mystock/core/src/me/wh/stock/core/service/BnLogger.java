package me.wh.stock.core.service;

import me.wh.stock.core.entity.BnLog;

import org.springframework.stereotype.Service;

import coo.core.security.service.AbstractBnLogger;

/**
 * 业务日志组件。
 */
@Service
public class BnLogger extends AbstractBnLogger<BnLog> {
	@Override
	public BnLog newBnLog() {
		return new BnLog();
	}
}