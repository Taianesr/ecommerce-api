package com.teste.ecommerce_api.demo.service;

import com.teste.ecommerce_api.demo.controller.dto.front.ProductFrontDto;
import com.teste.ecommerce_api.demo.controller.dto.product.ProductDto;
import com.teste.ecommerce_api.demo.entity.ProductEntity;
import com.teste.ecommerce_api.demo.mapper.ProductMapper;
import com.teste.ecommerce_api.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BucketService bucketService;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, BucketService bucketService, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.bucketService = bucketService;
        this.productMapper = productMapper;
    }

    public void createProduct(ProductDto productDto) {

        ProductEntity entity = productMapper.toEntity(productDto);
        log.debug("Mapped ProductDto to ProductEntity: {}", entity);

        productRepository.save(entity);
        log.info("Product successfully created in database. SKU: {}", entity.getSku());

    }

    public ProductFrontDto getProductBySku(String sku) {

        log.debug("Searching for product with SKU: {}", sku);
        ProductEntity entity = productRepository.findBySku(sku);
        log.debug("Product found by sku: '{}'", sku);

        ProductFrontDto productFrontDto=  productMapper.toFrontDto(entity, generatePreSignedImageUrl(entity.getImageName()));



        return productFrontDto;
    }

    public List<ProductFrontDto> getAllProducts() {

        log.debug("Searching all products...");

        List<ProductEntity> entities = productRepository.findAll();

        log.debug("Retrieved {} products from database.", entities.size());

        List<ProductFrontDto> productsToFront = new ArrayList<>();

        for (ProductEntity product : entities) {
            ProductFrontDto productFrontDto = productMapper.toFrontDto(product, generatePreSignedImageUrl(product.getImageName()));
            productsToFront.add(productFrontDto);
            log.debug("Mapped ProductEntity to ProductDto: {}", productFrontDto);
        }

        return productsToFront;

    }


    public List<ProductFrontDto> deleteAllProducts() {
        try {
            log.info("Starting deletion of all products from database.");
            productRepository.deleteAll();
            log.info("All products successfully deleted from database.");
            return Collections.emptyList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar todos os produtos: " + e.getMessage(), e);
        }

    }

    @Transactional
    public List<ProductFrontDto> deleteProductBySku(String sku) {
        try {
            log.info("Starting deletion of product with sku: {}", sku);
            productRepository.removeBySku(sku);
            log.info("Product with SKU: {} successfully deleted.", sku);

            return Collections.emptyList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar todos os produtos: " + e.getMessage(), e);
        }

    }


    protected String generatePreSignedImageUrl(String imageName) {
        if (imageName == null) return null;
        return bucketService.generatePreAssigendUrl(imageName);
    }


}
