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
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	// 一(部門)對多(員工)
	@OneToMany(mappedBy = "department")
	@OrderBy("id ASC") //透過部門查找員工，員工會以 由小到大 排序呈現
	private Set<Employee> employees = new LinkedHashSet<>();
}