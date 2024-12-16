package com.example.demo.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private Integer cost; 
	
	@Column
	private Integer price; 
	
	@Column
	private String description; 
	
	// 一(商品)對多(採購明細)
	@OneToMany(mappedBy = "product")
	@OrderBy("id ASC")
	//透過 商品 查找 採購明細s
	private Set<PurchaseItem> purchaseItems = new LinkedHashSet<>();
	
	// 一(商品)對多(訂單明細)
	@OneToMany(mappedBy = "product")
	@OrderBy("id ASC")
	private Set<OrderItem> orderItems = new LinkedHashSet<>();
}