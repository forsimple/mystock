package me.wh.stock.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import coo.core.security.entity.OrganEntity;

/**
 * 机构。
 */
@Entity
@Table(name = "Organ")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organ extends OrganEntity<Organ, User, Actor> {
	private static final long serialVersionUID = 9197249548328662679L;
}
