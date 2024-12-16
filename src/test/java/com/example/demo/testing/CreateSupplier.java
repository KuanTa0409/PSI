package com.example.demo.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;

@SpringBootTest
public class CreateSupplier {

	@Autowired
	SupplierRepository supplierRepository;
	
	@Test
	public void test() {
		//                          String name, String memo
		Supplier s1 = new Supplier("Supply_Lin", "周一公休");
		Supplier s2 = new Supplier("Supply_Wang", "例假日不配送貨");
		Supplier s3 = new Supplier("Supply_Cheng", "不運送危險及易碎物品");
		
		supplierRepository.save(s1);
		supplierRepository.save(s2);
		supplierRepository.save(s3);
		
		System.out.println("CreateSupplier OK!");
	}
}
