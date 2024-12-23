package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Supplier;
import com.example.demo.service.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping("/")
	public String index(@ModelAttribute Supplier supplier, Model model) {
		model.addAttribute("suppliers", supplierService.findAll());
		return "supplier";
	}
	
	@GetMapping("/edit/{id}")  // 顯示修改頁面
	public String edit(@PathVariable("id") Long id, Model model) {
		Supplier supplier = supplierService.findById(id);
		model.addAttribute("supplier", supplier);
		return "supplier-edit";
	}
	
	@PostMapping(value = {"/", "/create"})
	public String create(Supplier supplier) {
		supplierService.save(supplier);
		return "redirect:/supplier/";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, Supplier supplier) {
		supplier.setId(id);
		supplierService.save(supplier);
		return "redirect:/supplier/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		supplierService.deleteById(id);
		return "redirect:/supplier/";
	}
}
