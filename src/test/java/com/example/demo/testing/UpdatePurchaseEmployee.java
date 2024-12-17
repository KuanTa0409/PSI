package com.example.demo.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Purchase;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PurchaseRepository;

@SpringBootTest
public class UpdatePurchaseEmployee {

	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	public void test() {
		// 將 採購單(3L)的員工 改為布萊特(5L)
		Employee e1 = employeeRepository.findById(5L).get();
		Purchase p1 = purchaseRepository.findById(3L).get();
		
		// 重新配置關聯
		p1.setEmployee(e1);
		
		purchaseRepository.save(p1);
		
		System.out.println("UpdatePurchaseEmployee OK!");
	}
}
