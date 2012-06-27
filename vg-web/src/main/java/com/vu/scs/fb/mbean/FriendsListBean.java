package com.vu.scs.fb.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.vu.scs.fb.bean.Person;

@ManagedBean
@SessionScoped
public class FriendsListBean implements Serializable {

	/*
	 * @ManagedProperty(value="#{message}") private MessageBean messageBean;
	 * 
	 * //must povide the setter method public void setMessageBean(MessageBean
	 * messageBean) { this.messageBean = messageBean; }
	 */

	private static final long serialVersionUID = 1L;

	private List<Person> personsList;

	public List<Person> getPersonsList() {
		personsList = new ArrayList<Person>(); //TODO - temp
		
		return personsList;
	}

	public void setPersonsList(List<Person> personsList) {
		this.personsList = personsList;
	}


}