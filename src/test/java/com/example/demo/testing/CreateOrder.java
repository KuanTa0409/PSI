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
public class CreateOrder {
	
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
		// 銷售部門的 員工拉維爾(3L) 收到 客戶Allen01(1L) 訂單明細: 蛋黃酥(1L)12個、太陽餅(5L)12個
		Customer c1 = customerRepository.findById(1L).get(); //客戶Allen01
		Employee e1 = employeeRepository.findById(3L).get(); //員工拉維爾
		Product p1 = productRepository.findById(1L).get(); //蛋黃酥
		Product p2 = productRepository.findById(5L).get(); //太陽餅
		
		// 訂單
		Order order = new Order();
		order.setDate(new Date());
		order.setMemo("請小心碰撞!");
		order.setCustomer(c1);
		order.setEmployee(e1);
		
		// 訂單明細1
		// 蛋黃酥12個
		OrderItem oItem1 = new OrderItem();
		oItem1.setAmount(12);
		oItem1.setProduct(p1);
		oItem1.setOrder(order);
		
		// 訂單明細2
		// 太陽餅12個
		OrderItem oItem2 = new OrderItem();
		oItem2.setAmount(12);
		oItem2.setProduct(p2);
		oItem2.setOrder(order);
		
		// 先保存主檔(Order)，再保存副檔(OrderItem)
		orderRepository.save(order);
		orderItemRepository.save(oItem1);
		orderItemRepository.save(oItem2);
		
		System.out.println("CreateOrder OK!");
	}
}

