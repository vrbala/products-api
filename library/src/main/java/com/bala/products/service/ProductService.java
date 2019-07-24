package com.bala.products.service;

import com.bala.products.dto.MetaData;
import com.bala.products.dto.PricingInformation;
import com.bala.products.dto.Product;
import com.bala.products.dto.ProductDescription;
import com.bala.products.model.ProductModel;
import com.bala.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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
        return ProductService.toProductDtos(pms);
    }

    public Product findByProductId(String productId) {
        Optional<ProductModel> pm = repository.findByProductId(productId);
        if(pm.isPresent()) {
            return ProductService.toProductDto(pm.get());
        }

        return null;
    }

    public List<Product> pagedResults(int limit, int page) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        Iterable<ProductModel> pms = repository.findAll(pageRequest);

        return ProductService.toProductDtos(pms);
    }

    private static List<Product> toProductDtos(Iterable<ProductModel> pms) {
        List<Product> products = StreamSupport.stream(pms.spliterator(), false)
                .map(ProductService::toProductDto)
                .collect(Collectors.toList());

        return products;
    }

    public void createProduct(Product product) {
        ProductModel pm = ProductService.toProductModel(product);
        repository.save(pm);
    }

    public void updateProduct(Product pd) {

        Optional<ProductModel> pmo = repository.findByProductId(pd.getId());
        if (pmo.isPresent()) {
            ProductModel pm = pmo.get();
            pm.setCanonical(pd.getMetaData().getCanonical())
                    .setDescription(pd.getMetaData().getDescription())
                    .setKeywords(pd.getMetaData().getKeywords())
                    .setSiteName(pd.getMetaData().getSiteName())
                    .setPageTitle(pd.getMetaData().getPageTitle())
                    .setCurrentPrice(pd.getPricingInformation().getCurrentPrice())
                    .setStandardPrice(pd.getPricingInformation().getStandardPrice())
                    .setStandardPriceNoVat(pd.getPricingInformation().getStandardPriceNoVat())
                    .setSubtitle(pd.getProductDescription().getSubtitle())
                    .setText(pd.getProductDescription().getText())
                    .setTitle(pd.getProductDescription().getTitle())
                    .setProductId(pd.getId())
                    .setName(pd.getName())
                    .setProductType(pd.getProductType())
                    .setModelNumber(pd.getModelNumber());
            repository.save(pm);
        }
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

    private static ProductModel toProductModel(Product pd) {
        return new ProductModel()
                .setCanonical(pd.getMetaData().getCanonical())
                .setDescription(pd.getMetaData().getDescription())
                .setKeywords(pd.getMetaData().getKeywords())
                .setSiteName(pd.getMetaData().getSiteName())
                .setPageTitle(pd.getMetaData().getPageTitle())
                .setCurrentPrice(pd.getPricingInformation().getCurrentPrice())
                .setStandardPrice(pd.getPricingInformation().getStandardPrice())
                .setStandardPriceNoVat(pd.getPricingInformation().getStandardPriceNoVat())
                .setSubtitle(pd.getProductDescription().getSubtitle())
                .setText(pd.getProductDescription().getText())
                .setTitle(pd.getProductDescription().getTitle())
                .setProductId(pd.getId())
                .setName(pd.getName())
                .setProductType(pd.getProductType())
                .setModelNumber(pd.getModelNumber());
    }

}
