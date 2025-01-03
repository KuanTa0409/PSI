package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("customer", new Customer());
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		
		// 客戶的訂單數量
		Map<Long, Integer> orderCounts = new HashMap<>();
		customers.forEach(customer -> {
			orderCounts.put(customer.getId(), customerService.getOrderCount(customer.getId()));
		});
		model.addAttribute("orderCounts", orderCounts);
		return "customer"; 
	}
	
	@PostMapping(value = {"/", "/create"})
	public String create(Customer customer) {
		customerService.save(customer);
		return "redirect:/customer/";
	}
	
	@GetMapping("/edit/{id}") // 修改頁面的呈現
	public String edit(@PathVariable("id") Long id, Model model) {
		Customer customer = customerService.findById(id);
		model.addAttribute("customer", customer);
		return "customer-edit";
	}
	
	@PutMapping("/{id}") // 對資料庫進行修改
	public String update(@PathVariable("id") Long id, Customer customer) {
		customer.setId(id);
		customerService.save(customer);
		return "redirect:/customer/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		customerService.deleteById(id);
		return "redirect:/customer/";
	}
}