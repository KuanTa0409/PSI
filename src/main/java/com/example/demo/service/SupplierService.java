package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;

@Service
@Transactional
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }
    
    public Supplier findById(Long id) {
        return supplierRepository.findById(id).get();
    }
    
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
    
    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }
}
