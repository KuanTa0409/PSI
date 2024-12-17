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
public class CreatePurchase3 {

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
		// 資訊部門的 員工西露卡 對 供應商Supply_Lin 採購 太陽餅250個
		// 透過 Update 修改員工
		Employee e1 = employeeRepository.findById(1L).get(); //西露卡
		Supplier s1 = supplierRepository.findById(1L).get(); //Supply_Lin
		Product p1 = productRepository.findById(5L).get(); //太陽餅
		
		// 採購單1
		Purchase pur3 = new Purchase();
		pur3.setDate(new Date());
		
		// 配置關聯
		pur3.setEmployee(e1);
		pur3.setSupplier(s1);
		
		// 採購單明細1
		// 太陽餅250個
		PurchaseItem pItem3 = new PurchaseItem();
		pItem3.setAmount(250);
		pItem3.setProduct(p1);
		pItem3.setPurchase(pur3);
		
		purchaseRepository.save(pur3);
		purchaseItemRepository.save(pItem3);
		
		System.out.println("CreatePurchase3 OK!");
	}
}
