package me.wh.stock.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import coo.core.security.entity.RoleEntity;

/**
 * 角色。
 */
@Entity
@Table(name = "Role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role extends RoleEntity<User, Actor> {
	private static final long serialVersionUID = 6906333329211672538L;
}
