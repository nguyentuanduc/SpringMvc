package com.spring.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

@Entity(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "unit_price")
	private BigDecimal  unitPrice;
	
	@Column(name = "manufacturer")
	private String manufacturer;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "condition_type")
	private String condition;
	
	@Column(name = "units_in_stock")
	private long unitsInStock;
	
	@Column(name = "units_in_order")
	private long unitsInOrder;
	
	@Column(name = "discontinued", columnDefinition = "BIT", length = 1)
	private Boolean discontinued;
	
	@Column(name = "created", updatable = false)
	@CreationTimestamp
	private Date created;
	
	@Column(name = "updated")
	@UpdateTimestamp
	private Date updated;
	
	
	@Transient
	private MultipartFile productImage;
	
	@Transient
	private List<String> listCategory;
	
	
	public List<String> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<String> listCategory) {
		if(this.listCategory == null){
			this.listCategory = new ArrayList<String>();
		}
		this.listCategory = listCategory;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}



	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Publish> publishs = new HashSet<Publish>();
	
	@ManyToMany
	@JoinTable(name = "product_category_detail", 
	joinColumns = @JoinColumn(name = "product_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categorys = new HashSet<Category>();
	
	
	public Set<Publish> getPublishs() {
		return publishs;
	}

	public void setPublishs(Set<Publish> publishs) {
		this.publishs = publishs;
	}

	public Set<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(Set<Category> categorys) {
		this.categorys = categorys;
	}

	public void addPublish(Publish publish) {
		publish.setProduct(this);
		getPublishs().add(publish);
	}
	
	public void removePublish(Publish publish) {
		getPublishs().remove(publish);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unit_price) {
		this.unitPrice = unit_price;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public long getUnitsInOrder() {
		return unitsInOrder;
	}

	public void setUnitsInOrder(long unitsInOrder) {
		this.unitsInOrder = unitsInOrder;
	}

	

	public Boolean getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Boolean discontinued) {
		this.discontinued = discontinued;
	}

	
	
	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", unitPrice=" + unitPrice
				+ ", manufacturer=" + manufacturer + ", category=" + category + ", condition=" + condition
				+ ", unitsInStock=" + unitsInStock + ", unitsInOrder=" + unitsInOrder + ", discontinued=" + discontinued
				+ ", created=" + created + ", updated=" + updated + ", productImage=" + productImage + ", listCategory="
				+ listCategory + ", publishs=" + publishs + ", categorys=" + categorys + "]";
	}

	

	

	
	
	
	
	
}
