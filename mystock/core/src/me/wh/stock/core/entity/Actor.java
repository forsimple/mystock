package me.wh.stock.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import coo.core.security.entity.ActorEntity;

/**
 * 职务。
 */
@Entity
@Table(name = "Actor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Actor extends ActorEntity<Organ, User, Role> {
	private static final long serialVersionUID = 4518572192909879160L;
}
