package com.bala.products.service;

import com.bala.products.dto.Product;
import org.springframework.stereotype.Service;

@Service
public interface EventPersistenceService {
    public Product findByProductId(String productId);
    public void createProduct(Product product);
    public void updateProduct(Product pd);
    public boolean deleteByProductId(String productId);
}
