package com.example.demo.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

@SpringBootTest
public class CreateEmployee {

	@Autowired
	DepartmentRepository departmentRepository; // department_id欄位
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	public void test() {
		// 取得部門
		Department d1 = departmentRepository.findById(1L).get(); //資訊部
		Department d2 = departmentRepository.findById(2L).get(); //採購部
		Department d3 = departmentRepository.findById(3L).get(); //銷售部
		
		// 員工
		Employee e1 = new Employee("西露卡");
		Employee e2 = new Employee("莫托斯");
		Employee e3 = new Employee("拉維爾");
		Employee e4 = new Employee("弗洛倫");
		Employee e5 = new Employee("布萊特");
		Employee e6 = new Employee("馬洛斯");
		
		// 配置關聯(員工與部門的關係)，由多(員工)方維護關聯
		e1.setDepartment(d1); //資訊部的 西露卡
		e2.setDepartment(d2); //採購部的 莫托斯
		e3.setDepartment(d3); //銷售部的 拉維爾
		e4.setDepartment(d1); //資訊部的 弗洛倫
		e5.setDepartment(d2); //採購部的 布萊特
		e6.setDepartment(d3); //銷售部的 馬洛斯
		
		employeeRepository.save(e1);
		employeeRepository.save(e2);
		employeeRepository.save(e3);
		employeeRepository.save(e4);
		employeeRepository.save(e5);
		employeeRepository.save(e6);
		
		System.out.println("CreateEmployee OK!");
	}
}
