package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> findAll(){
		return customerRepository.findAll();
	}
	
	public Customer findById(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer id NOT found!"));
	}
	
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public void deleteById(Long id) {
		customerRepository.deleteById(id);
	}
}
