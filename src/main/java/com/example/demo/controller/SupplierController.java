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
import com.example.demo.repository.SupplierRepository;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@GetMapping("/")
	public String index(@ModelAttribute Supplier supplier, Model model) {
		model.addAttribute("suppliers", supplierRepository.findAll());
		return "supplier";
	}
	
	@GetMapping("/edit/{id}")  // 顯示修改頁面
	public String edit(@PathVariable("id") Long id, Model model) {
		Supplier supplier = supplierRepository.findById(id).get();
		model.addAttribute("supplier", supplier);
		return "supplier-edit";
	}
	
	@PostMapping(value = {"/", "/create"})
	public String create(Supplier supplier) {
		supplierRepository.save(supplier);
		return "redirect:/supplier/";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, Supplier supplier) {
		supplier.setId(id);
		supplierRepository.save(supplier);
		return "redirect:/supplier/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		supplierRepository.deleteById(id);
		return "redirect:/supplier/";
	}
}
