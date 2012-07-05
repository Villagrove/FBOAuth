package com.vu.scs.fb.mbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.vu.scs.fb.service.LoginService;

@ManagedBean
@SessionScoped
public class AccessTokenBean implements Serializable {

	/*@Autowired
	private LoginService loginService;*/

	/*
	 * @ManagedProperty(value="#{message}") private MessageBean messageBean;
	 * 
	 * //must povide the setter method public void setMessageBean(MessageBean
	 * messageBean) { this.messageBean = messageBean; }
	 */

	private static final long serialVersionUID = 1L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String loginToFacebook() {
		LoginService loginService = new LoginService();
		loginService.loginToFacebook();
		return "dashboard";
	}

}