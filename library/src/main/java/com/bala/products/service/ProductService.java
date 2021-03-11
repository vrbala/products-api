package com.bala.products.service;

import com.bala.products.dto.Product;

import java.util.List;

public interface ProductService {
    public List<Product> firstPage();
    public Product findByProductId(String productId);
    public List<Product> pagedResults(int limit, int page);
}
