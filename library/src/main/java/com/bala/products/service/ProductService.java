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
        Product p = new Product();

        MetaData md = new MetaData();
        md.setCanonical(pm.getCanonical());
        md.setDescription(pm.getDescription());
        md.setKeywords(pm.getKeywords());
        md.setSiteName(pm.getSiteName());
        md.setPageTitle(pm.getPageTitle());

        PricingInformation pi = new PricingInformation();
        pi.setCurrentPrice(pm.getCurrentPrice());
        pi.setStandardPrice(pm.getStandardPrice());
        pi.setStandardPriceNoVat(pm.getStandardPriceNoVat());

        ProductDescription pd = new ProductDescription();
        pd.setSubtitle(pm.getSubtitle());
        pd.setText(pm.getText());
        pd.setTitle(pm.getTitle());

        p.setId(pm.getProductId());
        p.setModelNumber(pm.getModelNumber());
        p.setName(pm.getName());
        p.setProductType(pm.getProductType());
        p.setPricingInformation(pi);
        p.setProductDescription(pd);
        p.setMetaData(md);

        return p;
    }
}
