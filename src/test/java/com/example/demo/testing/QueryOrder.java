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
		
		orders.forEach(order -> {
			// 該筆訂單總價
			int total = order.getOrderItems()
					         .stream()
					         .mapToInt(item -> 
					        	 item.getAmount() * item.getProduct().getPrice())
					         .sum();
			System.out.printf("訂單編號:%d ,客戶:%s ,員工:%s, 總價:%d\n",
					order.getId(),
					order.getCustomer().getName(),
					order.getEmployee().getName(),
					total);
			System.out.println("訂單明細:");
			order.getOrderItems().forEach(item -> {
				System.out.printf("\t明細編號:%d ,商品:%s ,數量:%d ,單價:%d ,小計:%d\n",
						item.getId(),
						item.getProduct().getName(),
						item.getAmount(),
						item.getProduct().getPrice(),
						(item.getProduct().getPrice())*(item.getAmount()));
			});
			System.out.println("-------------------------------------------");
		});
		int sumTotal = orders.stream()
				             .mapToInt(order -> order.getOrderItems()
                                                     .stream()
                                                     .mapToInt(item -> item.getProduct().getPrice() * item.getAmount())
                                                     .sum())
				             .sum();               
		System.out.printf("所有訂單的總價:%d",sumTotal);
	}
}
