package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase_item")
public class PurchaseItem { //採購明細

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Integer amount; //採購數量
	
	// 多(採購明細)對一(採購單)
	@ManyToOne
	@JoinColumn(name = "purchase_id")
	private Purchase purchase;
	
	// 多(採購明細)對一(商品)
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
