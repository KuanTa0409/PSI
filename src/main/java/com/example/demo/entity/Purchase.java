package com.example.demo.entity;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "purchase")
public class Purchase { //採購單

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date; //採購日期
	
	// 多(採購單)對一(供應商)
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
	// 多(採購單)對一(員工)
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	// 一(採購單)對多(採購明細)
	@OneToMany(mappedBy = "purchase")
	@OrderBy("id ASC")
	private Set<PurchaseItem> purchaseItems = new LinkedHashSet<>();
}