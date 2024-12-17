package com.example.demo.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@SpringBootTest
public class CreateProduct {

	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void test() {

		//                String name, Integer cost, Integer price, String description
		Product p1 = new Product("蛋黃酥", 45, 45+15, "綿密口感");
		Product p2 = new Product("芋頭酥", 42, 42+15, "甜而不膩");
		Product p3 = new Product("咖哩綠豆椪", 43, 43+15, "鹹甜交織");
		Product p4 = new Product("鳳梨酥", 40, 40+15, "奶香濃郁");
		Product p5 = new Product("太陽餅", 38, 38+15, "紮實彈牙");
		Product p6 = new Product("Q餅", 45, 45+05, "新鮮限定");
		
		productRepository.save(p1);
		productRepository.save(p2);
		productRepository.save(p3);
		productRepository.save(p4);
		productRepository.save(p5);
		productRepository.save(p6);
		
		System.out.println("CreateProduct OK!");
	}
}
