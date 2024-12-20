package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> findAll(){
		return departmentRepository.findAll();
	}
	
	public Department findById(Long id) {
		return departmentRepository.findById(id).get();
	}
	
	public Department save(Department department) {
		return departmentRepository.save(department);
	}
	
	public void deleteById(Long id) {
		departmentRepository.deleteById(id);
	}
}
