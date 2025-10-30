package com.teste.ecommerce_api.demo.service;

import com.teste.ecommerce_api.demo.controller.dto.front.ProductFrontDto;
import com.teste.ecommerce_api.demo.controller.dto.product.ProductDto;
import com.teste.ecommerce_api.demo.entity.ProductEntity;
import com.teste.ecommerce_api.demo.mapper.ProductMapper;
import com.teste.ecommerce_api.demo.repository.ProductRepository;
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

        log.info("Entity mapeada: {}", entity.getSku());

        productRepository.save(entity);
    }

    public ProductFrontDto getProductBySku(String sku) {

        ProductEntity entity = productRepository.findBySku(sku);

        return productMapper.toFrontDto(entity, generatePreSignedImageUrl(entity.getImageName()));


    }

    public List<ProductFrontDto> getAllProducts() {

        List<ProductEntity> entities = productRepository.findAll();

        List<ProductFrontDto> productsToFront = new ArrayList<>();

        for (ProductEntity product : entities) {
            ProductFrontDto productFrontDto = productMapper.toFrontDto(product, generatePreSignedImageUrl(product.getImageName()));
            productsToFront.add(productFrontDto);
        }

        return productsToFront;

    }


    public List<ProductFrontDto> deleteAllProducts() {
        try {
            productRepository.deleteAll();
            return Collections.emptyList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar todos os produtos: " + e.getMessage(), e);
        }

    }

    public List<ProductFrontDto> deleteProductBySku(String sku) {
        try {
            productRepository.deleteBySku(sku);
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
