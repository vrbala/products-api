package com.bala.products.service;

import com.bala.products.dto.Product;
import com.bala.products.mapper.ProductMapper;
import com.bala.products.model.ProductModel;
import com.bala.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class EventPersistenceServiceImpl {

    @Autowired
    private ProductRepository repository;

    public Product findByProductId(String productId) {
        Optional<ProductModel> pm = repository.findByProductId(productId);
        if(pm.isPresent()) {
            return ProductMapper.productModelToDto(pm.get());
        }

        return null;
    }

    public void createProduct(Product product) {
        ProductModel pm = ProductMapper.productDtoToModel(product);
        repository.save(pm);
    }

    public void updateProduct(Product pd) {

        Optional<ProductModel> pmo = repository.findByProductId(pd.getId());
        if (pmo.isPresent()) {
            ProductModel pm = pmo.get();
            ProductMapper.productDtoIntoModel(pd, pm);
            repository.save(pm);
        }
    }

    public boolean deleteByProductId(String productId) {
        Optional<ProductModel> pm = repository.findByProductId(productId);
        if(pm.isPresent()) {
            repository.delete(pm.get());
            return true;
        }

        return false;
    }
}
