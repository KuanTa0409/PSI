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
public class CreatePurchase2 {

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
		// 採購部門的 員工布萊特 對 供應商Supply_Cheng 採購 咖哩綠豆椪150個、太陽餅160個、鳳梨酥200個
		Employee e1 = employeeRepository.findById(5L).get(); //布萊特
		Supplier s1 = supplierRepository.findById(3L).get(); //Supply_Cheng
		Product p1 = productRepository.findById(3L).get(); //咖哩綠豆椪
		Product p2 = productRepository.findById(5L).get(); //太陽餅
		Product p3 = productRepository.findById(4L).get(); //鳳梨酥
		
		// 採購單2
		Purchase pur2 = new Purchase();
		pur2.setDate(new Date());
		
		// 配置關聯
		pur2.setEmployee(e1);
		pur2.setSupplier(s1);
		
		// 採購單明細1
		// 咖哩綠豆椪150個
		PurchaseItem pItem1 = new PurchaseItem();
		pItem1.setAmount(150);
		pItem1.setProduct(p1);
		pItem1.setPurchase(pur2);
		
		// 採購單明細2
		// 太陽餅160個
		PurchaseItem pItem2 = new PurchaseItem();
		pItem2.setAmount(160);
		pItem2.setProduct(p2);
		pItem2.setPurchase(pur2);
		
		// 採購單明細3
		// 鳳梨酥200個
		PurchaseItem pItem3 = new PurchaseItem();
		pItem3.setAmount(2000);
		pItem3.setProduct(p3);
		pItem3.setPurchase(pur2);	
		
		purchaseRepository.save(pur2);
		purchaseItemRepository.save(pItem1);
		purchaseItemRepository.save(pItem2);
		purchaseItemRepository.save(pItem3);
		
		System.out.println("CreatePurchase2 OK!");
	}
}
