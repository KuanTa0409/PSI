package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Product;
import com.example.demo.entity.view.Inventory;
import com.example.demo.entity.view.ProductSales;
import com.example.demo.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		return productRepository.findById(id).get();
	}
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
	
	public List<Inventory> queryInventory(){
		return productRepository.queryInventory();
	}
	
	public List<ProductSales> queryProductSales(){
		return productRepository.queryProductSales();
	}
}
