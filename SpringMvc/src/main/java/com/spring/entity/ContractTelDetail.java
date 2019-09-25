package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "contact_tel_detail")
public class ContractTelDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "version")
	private int version;
	
	@Column(name = "tel_type")
	private String tel_type;
	
	@Column(name = "tel_number")
	private String tel_number;

	@ManyToOne
	@JoinColumn(name = "CONTACT_ID")
	private Contact contact;
	
	
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTel_type() {
		return tel_type;
	}

	public void setTel_type(String tel_type) {
		this.tel_type = tel_type;
	}

	public String getTel_number() {
		return tel_number;
	}

	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}

	@Override
	public String toString() {
		return "ContractTelDetail [id=" + id + ", version=" + version + ", tel_type=" + tel_type + ", tel_number="
				+ tel_number + "]";
	}
	
	
	
}
