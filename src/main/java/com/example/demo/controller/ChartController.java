package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.ProductRepository;

@Controller
@RequestMapping("/chart")
public class ChartController {
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/")
	public String index(Model model) {
		// 全部商品的個別銷售數量
		model.addAttribute("productSales", productRepository.queryProductSales());
		return "chart";
	}
	
}