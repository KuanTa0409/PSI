package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	public Employee findById(Long id) {
		return employeeRepository.findById(id).get();
	}
	
	public Employee save(Employee employee) {
		// 確認部門是否存在
		Department department = departmentRepository.findById(employee.getDepartment().getId()).get();
		
		employee.setDepartment(department);
		return employeeRepository.save(employee);
	}
	
	public void deleteById(Long id) {
		employeeRepository.deleteById(id);
	}
}
