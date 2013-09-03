package com.nixsolutions.domain;

import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="worker")
@AttributeOverrides({
    @AttributeOverride(name="person_id", column = @Column),
    @AttributeOverride(name="name", column=@Column(name="LASTNAME"))
})
public class Worker extends Person{

	@Column(name="company_name")
	private String companyName;
	
	@Column(name="skill")
	private String skill;
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getSkill() {
		return skill;
	}
	
	public void setSkill(String skill) {
		this.skill = skill;
	}

	@Override
	public String toString() {
		return "Worker [companyName=" + companyName + ", skill=" + skill
				+ ", getId()=" + getId() + ", getName()=" + getName() + "]";
	}
	
	
	
}
