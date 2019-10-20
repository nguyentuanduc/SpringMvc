package com.spring.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "category")
public class Category {
	
	@Id
	@Column(name = "category_id")
	private String category_id;


	@ManyToMany
	@JoinTable(name = "product_category_detail", 
	joinColumns = @JoinColumn(name="category_id"), 
	inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> products = new HashSet<Product>();
	
	
	
	public Set<Product> getContacts() {
		return products;
	}

	public void setContacts(Set<Product> param_products) {
		this.products = param_products;
    
    }
    

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
    
    @Override
    public String toString() {
        return "Category [category_id=" + category_id + "]";
    }

}
