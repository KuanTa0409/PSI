package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.view.Inventory;
import com.example.demo.entity.view.ProductSales;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	// nativeQuery = true，表示它們是原生SQL查詢而不是JPQL。查詢結果會映射到 Inventory 和 ProductSales 這兩個介面上
	
	// 查詢所有商品的採購數量(purchase_item)與銷售數量(order_item)
	// amount1: 採購數量, amount2: 銷售數量
	@Query(nativeQuery = true, 
			value = "select p.id, p.name, p.cost, p.price, "
			+ "(select sum(amount) from purchase_item where product_id = p.id limit 1) as amount1, "
			+ "(select sum(amount) from order_item where product_id = p.id limit 1) as amount2 " 
			+ "from product p")
	List<Inventory> queryInventory();

	// 查詢某商品(根據商品 id)的採購數量(purchase_item)與銷售數量(order_item)
	// amount1: 採購數量, amount2: 銷售數量
	@Query(nativeQuery = true, 
			value = "select p.id, p.name, p.cost, p.price, "
			+ "(select sum(amount) from purchase_item where product_id = p.id limit 1) as amount1, "
			+ "(select sum(amount) from order_item where product_id = p.id limit 1) as amount2 "
			+ "from product p where p.id=:id ")
	Inventory findInventoryById(Long id); // 返回的是單一商品的庫存資訊 而不是列表
	// where p.id=:id 條件限定只查詢特定商品

	// 全部商品的個別銷售數量
	@Query(nativeQuery = true, 
			value = "select p.id, p.name, sum(o.amount) as total " 
					+ "from order_item o, product p "
					+ "where o.product_id = p.id " + "group by p.id, p.name ")
	List<ProductSales> queryProductSales();
}
