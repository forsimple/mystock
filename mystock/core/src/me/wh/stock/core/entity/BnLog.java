package me.wh.stock.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Indexed;

import coo.core.security.entity.BnLogEntity;

/**
 * 业务日志。
 */
@Entity
@Table(name = "BnLog")
@Indexed(index = "BnLog")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class BnLog extends BnLogEntity {
	private static final long serialVersionUID = -183679360294817527L;
}
