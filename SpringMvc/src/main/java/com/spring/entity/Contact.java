package com.spring.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity(name = "contact")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "FIRST_NAME")
	private String first_name;
	
	@Column(name = "LAST_NAME")
	private String last_name;
	
	@Column(name = "BIRTH_DATE")
	private Date birth_date;
	
	@Column(name = "VERSION")
	private int version;

	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ContractTelDetail> contractTelDetails = new HashSet<ContractTelDetail>();
	
	public Set<ContractTelDetail> getContractTelDetails() {
		return contractTelDetails;
	}

	public void setContractTelDetails(Set<ContractTelDetail> contractTelDetails) {
		this.contractTelDetails = contractTelDetails;
	}

	public void addContractTelDetail(ContractTelDetail contractTelDetail) {
		contractTelDetail.setContact(this);
		getContractTelDetails().add(contractTelDetail);
	}
	
	public void removeContractTelDetail(ContractTelDetail contractTelDetail) {
		getContractTelDetails().remove(contractTelDetail);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}



	@Override
	public String toString() {
		return "Contact [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", birth_date="
				+ birth_date + ", version=" + version + "]";
	}
	
	
}
