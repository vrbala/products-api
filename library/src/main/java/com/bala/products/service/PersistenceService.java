package com.bala.products.service;

import com.bala.products.dto.Product;

public interface PersistenceService {
    public Product findByProductId(String productId);
    public void createProduct(Product product);
    public void updateProduct(Product pd);
    public boolean deleteByProductId(String productId);
}
