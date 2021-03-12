package com.bala.products.service;

import com.bala.products.dto.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<Product> firstPage();
    public Product findByProductId(String productId);
    public List<Product> pagedResults(int limit, int page);
}
