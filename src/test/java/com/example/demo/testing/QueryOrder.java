package com.example.demo.testing;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

@SpringBootTest
public class QueryOrder {

	@Autowired
	OrderRepository orderRepository;
	
	@Test
	public void test() {
		List<Order> orders = orderRepository.findAll();
		
		
	}
}
