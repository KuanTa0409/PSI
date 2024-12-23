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

import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@GetMapping("/")    // th:object="${department}" 
	public String index(@ModelAttribute Department department, Model model) {
		model.addAttribute("departments", departmentService.findAll());
		return "department";
	}
	
	@PostMapping("/")
	public String create(Department department) {
		departmentService.save(department);
		return "redirect:/department/";
	}
	
	@GetMapping("/edit/{id}") // 顯示編輯頁面
	public String edit(@PathVariable("id") Long id, Model model) {
		Department department = departmentService.findById(id);
		model.addAttribute("department", department);
		return "department-edit";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, Department department) {
		department.setId(id);
		departmentService.save(department);
		return "redirect:/department/";
	}
	
	@GetMapping("/delete/{id}")
	public String deletet(@PathVariable("id") Long id) {
		departmentService.deleteById(id);
		return "redirect:/department/";
	}
}
