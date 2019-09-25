package com.spring.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "hobby")
public class Hobby {
	
	@Id
	@Column(name = "hobby_id")
	private String hobby_id;

	@ManyToMany
	@JoinTable(name = "contact_hobby_detail", 
	joinColumns = @JoinColumn(name="hobby_id"), 
	inverseJoinColumns = @JoinColumn(name = "contact_id"))
	private Set<Contact> contacts = new HashSet<Contact>();
	
	
	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getHobby_id() {
		return hobby_id;
	}

	public void setHobby_id(String hobby_id) {
		this.hobby_id = hobby_id;
	}

	@Override
	public String toString() {
		return "Hobby [hobby_id=" + hobby_id + "]";
	}
	
}
