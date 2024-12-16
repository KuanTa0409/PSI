package com.example.demo.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@SpringBootTest
public class CreateCustomer {

	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	public void test() {
		//                       String name, String city, String phone
		Customer c1 = new Customer("Allen01", "台北", "0975868158");
		Customer c2 = new Customer("Becky02", "台中", "0913153277");
		Customer c3 = new Customer("Candy03", "台南", "0927289331");
		
		customerRepository.save(c1);
		customerRepository.save(c2);
		customerRepository.save(c3);
		
		System.out.println("CreateCustomer OK!");
	}
	// String name, String city, String phone
}
