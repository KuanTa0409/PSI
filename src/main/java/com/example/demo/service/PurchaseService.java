package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Purchase;
import com.example.demo.entity.PurchaseItem;
import com.example.demo.repository.PurchaseItemRepository;
import com.example.demo.repository.PurchaseRepository;

@Service
@Transactional
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    
    @Autowired
    private PurchaseItemRepository purchaseItemRepository;
    
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }
    
    public Purchase findById(Long id) {
        return purchaseRepository.findById(id).get();
    }
    
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
    
    public void deleteById(Long id) {
        purchaseRepository.deleteById(id);
    }
    
    public PurchaseItem findpItemById(Long id) {
    	return purchaseItemRepository.findById(id).get();
    }
    
    public PurchaseItem savePurchaseItem(PurchaseItem purchaseItem, Long purchaseId) {
    	// 取得採購單檔
        Purchase purchase = findById(purchaseId);
        // 配置關聯(由多方建立關聯)
        purchaseItem.setPurchase(purchase);
        return purchaseItemRepository.save(purchaseItem);
    }
    
    public void deletePurchaseItem(Long purchaseItemId) {
        purchaseItemRepository.deleteById(purchaseItemId);
    }
}