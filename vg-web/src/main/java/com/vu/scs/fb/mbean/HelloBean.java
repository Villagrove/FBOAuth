package com.vu.scs.fb.mbean;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
 
@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {
	
	/*
	@ManagedProperty(value="#{message}")
	private MessageBean messageBean;
 
	//must povide the setter method
	public void setMessageBean(MessageBean messageBean) {
		this.messageBean = messageBean;
	}
	*/
 
	private static final long serialVersionUID = 1L;
 
	private String name;
 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}