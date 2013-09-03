package com.nixsolutions.restlaba.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.logging.LogFactory;

/**
 * Role.
 * 
 * @author zinchenko
 * 
 */
@XmlRootElement(name="role")
@Entity
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	public Role() {
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		try {
			return BeanUtils.describe(this).toString();
		} catch (Exception e) {
			LogFactory.getLog(Role.class).error(
					"could not correct invoke a toString method", e);
		}
		return super.toString();
	}

	@Override
	public int hashCode() {
		
		return HashCodeBuilder.reflectionHashCode(this);
		
	}

	@Override
	public boolean equals(final Object obj) {
		
		return EqualsBuilder.reflectionEquals(this, obj);
		
	}

}
