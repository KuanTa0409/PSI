package com.example.demo.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Purchase;
import com.example.demo.entity.PurchaseItem;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PurchaseItemRepository;
import com.example.demo.repository.PurchaseRepository;

@SpringBootTest
public class UpdatePurchaseAmount {

	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PurchaseItemRepository purchaseItemRepository;
	
	@Test
	public void test() {
		// 將 採購明細(5L)的數量 改為200
		PurchaseItem pItem = purchaseItemRepository.findById(5L).get();
		
		pItem.setAmount(200);
		
		purchaseItemRepository.save(pItem);
		
		System.out.println("UpdatePurchaseAmount OK!");
	}
}
