package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/chart")
public class ChartController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("productSales", productService.queryProductSales());
		model.addAttribute("inventories", productService.queryInventory());
		return "chart";
	}
}