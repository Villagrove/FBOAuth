package com.vu.scs.fb.mbean;
 
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.vu.scs.fb.bean.PersonDetail;
 
@ManagedBean
@SessionScoped
public class BasicProfileBean implements Serializable {
	
	/*
	@ManagedProperty(value="#{message}")
	private MessageBean messageBean;
 
	//must povide the setter method
	public void setMessageBean(MessageBean messageBean) {
		this.messageBean = messageBean;
	}
	*/
 
	private static final long serialVersionUID = 1L;
 
	private PersonDetail personDetail;

	public PersonDetail getPersonDetail() {
		return personDetail;
	}

	public void setPersonDetail(PersonDetail personDetail) {
		this.personDetail = personDetail;
	}
 
	
}