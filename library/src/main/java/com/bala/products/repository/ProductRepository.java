package com.bala.products.repository;

import com.bala.products.model.ProductModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// TODO: extend PagingAndSortingRepository
public interface ProductRepository extends PagingAndSortingRepository<ProductModel, Long> {


    @Query("select productId, name, modelNumber, productType, pageTitle, siteName, description, keywords, canonical, standardPrice, standardPriceNoVat, currentPrice, title, subtitle, text from ProductModel where productId = :productId")
    Optional<ProductModel> findByProductId(@Param("productId") String productId);
}
