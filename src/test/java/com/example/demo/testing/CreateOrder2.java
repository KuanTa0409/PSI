package com.example.demo.testing;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;

@SpringBootTest
public class CreateOrder2 {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void test() {
		// 銷售部門的 員工馬洛斯(6L) 收到 客戶Becky02(2L) 訂單明細: 鳳梨酥(4L)20個、Q餅(6L)15個
		Customer c1 = customerRepository.findById(2L).get(); //客戶Becky02
		Employee e1 = employeeRepository.findById(6L).get(); //員工馬洛斯
		Product p1 = productRepository.findById(4L).get(); //鳳梨酥
		Product p2 = productRepository.findById(6L).get(); //Q餅
		
		// 訂單
		Order order = new Order();
		order.setDate(new Date());
		order.setMemo("包裝完整，年節送親友!");
		order.setCustomer(c1);
		order.setEmployee(e1);
		
		// 訂單明細1
		// 鳳梨酥20個
		OrderItem oItem1 = new OrderItem();
		oItem1.setAmount(20);
		oItem1.setProduct(p1);
		oItem1.setOrder(order);
		
		// 訂單明細2
		// Q餅15個
		OrderItem oItem2 = new OrderItem();
		oItem2.setAmount(15);
		oItem2.setProduct(p2);
		oItem2.setOrder(order);
		
		orderRepository.save(order);
		orderItemRepository.save(oItem1);
		orderItemRepository.save(oItem2);
		
		System.out.println("CreateOrder2 OK!");
	}
}
