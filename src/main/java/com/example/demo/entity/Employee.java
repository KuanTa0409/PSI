package com.example.demo.entity;

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
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
		
	// 多(員工)對一(部門)
	@ManyToOne
	@JoinColumn(name = "department_id") //員工的部門序號
	//多方負責維護，需記住id
	private Department department;
	
	// 一(員工)對多(採購單)
	@OneToMany(mappedBy = "employee")
	@OrderBy("id ASC") 
	// 透過 員工 查找 採購單s
	private Set<Purchase> purchases = new LinkedHashSet<>();
}