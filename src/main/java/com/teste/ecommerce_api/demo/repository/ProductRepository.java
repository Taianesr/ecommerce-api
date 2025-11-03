package com.teste.ecommerce_api.demo.repository;

import com.teste.ecommerce_api.demo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    ProductEntity findBySku(String sku);

    void removeBySku(String sku);

}
