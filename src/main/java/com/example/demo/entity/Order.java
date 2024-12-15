package com.example.demo.entity;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders") //因為 order 在 mysql 是保留字
public class Order { //訂單

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Date date;
	
	@Column
	private String memo; //備註
	
	// 多(訂單)對一(客戶)
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	// 一(訂單)對多(訂單明細)
	@OneToMany(mappedBy = "order")
	@OrderBy("id ASC")
	private Set<OrderItem> orderItems = new LinkedHashSet<>();
}