package com.example.demo.testing;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Product;
import com.example.demo.entity.Purchase;
import com.example.demo.entity.PurchaseItem;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchaseItemRepository;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.SupplierRepository;

@SpringBootTest
public class CreatePurchase {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	PurchaseItemRepository purchaseItemRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void test() {
		// 採購部門的 員工莫托斯 對 供應商Supply_Lin 採購 蛋黃酥50個 與 Q餅60個
		Employee e1 = employeeRepository.findById(2L).get(); //莫托斯
		Supplier s1 = supplierRepository.findById(1L).get(); //Supply_Lin
		Product p1 = productRepository.findById(1L).get(); //蛋黃酥
		Product p2 = productRepository.findById(6L).get(); //Q餅
		
		// 採購單1
		Purchase pur1 = new Purchase();
		pur1.setDate(new Date());
		
		// 配置關聯
		pur1.setSupplier(s1);
		pur1.setEmployee(e1);
		
		// 採購單明細1
		// 蛋黃酥50個
		PurchaseItem pItem1 = new PurchaseItem();
		pItem1.setAmount(50);
		pItem1.setProduct(p1);
		pItem1.setPurchase(pur1);
		
		// 採購單明細2
		// Q餅60個
		PurchaseItem pItem2 = new PurchaseItem();
		pItem2.setAmount(60);
		pItem2.setProduct(p2);
		pItem2.setPurchase(pur1);
		
		purchaseRepository.save(pur1);
		purchaseItemRepository.save(pItem1);
		purchaseItemRepository.save(pItem2);	
		
		System.out.println("CreatePurchase OK!");
	}
}
