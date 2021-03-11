package com.bala.products.service;

import com.bala.products.dto.MetaData;
import com.bala.products.dto.PricingInformation;
import com.bala.products.dto.Product;
import com.bala.products.dto.ProductDescription;
import com.bala.products.mapper.ProductMapper;
import com.bala.products.model.ProductModel;
import com.bala.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> firstPage() {
        Iterable<ProductModel> pms = repository.findAll();
        return ProductServiceImpl.toProductDtos(pms);
    }

    public Product findByProductId(String productId) {
        Optional<ProductModel> pm = repository.findByProductId(productId);
        if(pm.isPresent()) {
            return ProductMapper.productModelToDto(pm.get());
        }

        return null;
    }

    public List<Product> pagedResults(int limit, int page) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        Iterable<ProductModel> pms = repository.findAll(pageRequest);

        return ProductServiceImpl.toProductDtos(pms);
    }

    private static List<Product> toProductDtos(Iterable<ProductModel> pms) {
        List<Product> products = StreamSupport.stream(pms.spliterator(), false)
                .map(ProductMapper::productModelToDto)
                .collect(Collectors.toList());

        return products;
    }
}
