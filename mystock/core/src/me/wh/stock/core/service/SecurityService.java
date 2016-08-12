package me.wh.stock.core.service;

import me.wh.stock.core.entity.Actor;
import me.wh.stock.core.entity.Organ;
import me.wh.stock.core.entity.Role;
import me.wh.stock.core.entity.User;

import org.springframework.stereotype.Service;

import coo.core.security.service.AbstractSecurityService;

/**
 * 安全服务。
 */
@Service
public class SecurityService extends
		AbstractSecurityService<Organ, User, Role, Actor> {
}