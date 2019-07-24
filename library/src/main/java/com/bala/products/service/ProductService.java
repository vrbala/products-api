package com.bala.products.service;

import com.bala.products.dto.MetaData;
import com.bala.products.dto.PricingInformation;
import com.bala.products.dto.Product;
import com.bala.products.dto.ProductDescription;
import com.bala.products.exceptions.ResourceNotFound;
import com.bala.products.model.ProductModel;
import com.bala.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> firstPage() {
        Iterable<ProductModel> pms = repository.findAll();
        List<Product> products = StreamSupport.stream(pms.spliterator(), false)
                .map(ProductService::toProductDto)
                .collect(Collectors.toList());

        return products;
    }

    public Product findByProductId(String productId) {
        Optional<ProductModel> pm = repository.findByProductId(productId);
        if(pm.isPresent()) {
            return ProductService.toProductDto(pm.get());
        }

        return null;
    }

    // TODO: could go to a utils class
    private static Product toProductDto(ProductModel pm) {

        MetaData md = new MetaData()
                .setCanonical(pm.getCanonical())
                .setDescription(pm.getDescription())
                .setKeywords(pm.getKeywords())
                .setSiteName(pm.getSiteName())
                .setPageTitle(pm.getPageTitle());

        PricingInformation pi = new PricingInformation()
                .setCurrentPrice(pm.getCurrentPrice())
                .setStandardPrice(pm.getStandardPrice())
                .setStandardPriceNoVat(pm.getStandardPriceNoVat());

        ProductDescription pd = new ProductDescription()
                .setSubtitle(pm.getSubtitle())
                .setText(pm.getText())
                .setTitle(pm.getTitle());


        return new Product()
                .setId(pm.getProductId())
                .setName(pm.getName())
                .setProductType(pm.getProductType())
                .setModelNumber(pm.getModelNumber())
                .setProductDescription(pd)
                .setPricingInformation(pi)
                .setMetaData(md);
    }
}
