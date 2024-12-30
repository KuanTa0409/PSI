package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.entity.OrderItem;
import com.example.demo.entity.view.Inventory;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.OrderService;

@Component
public class InventoryValidator implements Validator { // 驗證訂單明細(OrderItem)

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public boolean supports(Class<?> clazz) { // 只接受 OrderItem 類型或其子類的物件
		return OrderItem.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderItem orderItem = (OrderItem) target;
		// 從傳入的物件，取得原始訂單(修改訂單明細)
		OrderItem originItem = orderItem.getId() != null?
				orderItem.getOriginOrderItem() : null;

		if (orderItem.getAmount() == null || orderItem.getAmount() == 0) {
			errors.rejectValue("amount", "order_item.amount.required", "請輸入數量");
		} else {
			// 庫存數量檢查
			Long id = orderItem.getProduct().getId();
			Inventory inventory = productRepository.findInventoryById(id);
			
			// remains: 庫存數量 (採購數量 - 銷售數量)
			int purchaseAmount = inventory.getAmount1() == null ? 0 : inventory.getAmount1();
			int salesAmount = inventory.getAmount2() == null ? 0 : inventory.getAmount2();
			int remains = purchaseAmount - salesAmount;
			
			// 如果是修改訂單，加回原本訂單數量
            if (originItem != null) {
                remains += originItem.getAmount();
            }
			
			// 訂單明細數量 是否大於庫存剩餘數量
			if (orderItem.getAmount() > remains) {
				errors.rejectValue("amount", "order_item.amount.insufficient", "目前庫存數量不足 (庫存: " + remains + ")");
			}
		}
	}
}
