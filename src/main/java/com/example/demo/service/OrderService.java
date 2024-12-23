package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.validator.InventoryValidator;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private InventoryValidator inventoryValidator;
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	public Order findById(Long id) {
		return orderRepository.findById(id).get();
	}
	
	public Order save(Order order) {
		return orderRepository.save(order);
	}
	
	public void deleteById(Long id) {
		orderRepository.deleteById(id);
	}
	
	public OrderItem findOrderItemById(Long id) {
		return orderItemRepository.findById(id).get();
	}
	
	public OrderItem saveOrderItem(OrderItem orderItem, Long orderId) {
		// 訂單檔(主檔)
        Order order = findById(orderId);
        // 訂單項目與訂單檔(主檔)建立關聯 (由多方建立關聯)
        orderItem.setOrder(order);
		return orderItemRepository.save(orderItem);
	}
	
	public void deleteOrderItem(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}
