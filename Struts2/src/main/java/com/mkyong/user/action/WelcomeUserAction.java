package com.mkyong.user.action;

import java.util.List;

public class WelcomeUserAction{

	private String username;

    private String field;

    private Bean bean;

    private List<Bean> beans;

    private MyEnum myEnum;
	 
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// all struts logic here
	public String execute() {
//        System.out.println("username = " + username);
//        System.out.println("field = " + field);
//        System.out.println("beans.get(0).getField() = " + beans.get(0).getField());

        System.out.println("myEnum = " + myEnum);

		return "SUCCESS";

	}

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Bean getBean() {
        return bean;
    }

    public void setBean(Bean bean) {
        this.bean = bean;
    }

    public List<Bean> getBeans() {
        return beans;
    }

    public void setBeans(List<Bean> beans) {
        this.beans = beans;
    }

    public void setMyEnum(MyEnum myEnum) {
        this.myEnum = myEnum;
    }

    public MyEnum getMyEnum() {
        return myEnum;
    }
}