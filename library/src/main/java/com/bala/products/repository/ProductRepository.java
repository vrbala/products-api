package com.bala.products.repository;

import com.bala.products.model.ProductModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductModel, Long> {

    @Query("select pm from ProductModel pm where pm.productId = :productId")
    Optional<ProductModel> findByProductId(@Param("productId") String productId);
}
