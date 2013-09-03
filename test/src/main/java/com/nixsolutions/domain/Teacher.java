package com.nixsolutions.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="teacher")
public class Teacher extends Person{

	@Column(name="school")
	private String school;
	
	public String getSchool() {
		return school;
	}
	
	public void setSchool(String school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return "Teacher [school=" + school + ", getId()=" + getId()
				+ ", getName()=" + getName() + "]";
	}

	
	
}
