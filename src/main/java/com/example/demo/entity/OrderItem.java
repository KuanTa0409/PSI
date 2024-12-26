package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "order_item")
public class OrderItem { //訂單明細

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Integer amount; //訂購數量
	
	// 多(訂單明細)對一(訂單)
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	// 多(訂單明細)對一(商品)
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Transient  // 不需要存入資料庫
    private OrderItem originOrderItem;  

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public OrderItem getOriginOrderItem() {
		return originOrderItem;
	}

	public void setOriginOrderItem(OrderItem originOrderItem) {
		this.originOrderItem = originOrderItem;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}