package com.user.bean;

import org.springframework.stereotype.Component;

import com.user.page.Page;

/**
 * 用户
 */
@Component
 public class User extends Page {
	
	//id
	private String id;
	
	//登录名
 	private String loginname;
	
 	//密码
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
