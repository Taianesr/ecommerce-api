package com.teste.ecommerce_api.demo.controller;

import com.teste.ecommerce_api.demo.controller.dto.front.ProductFrontDto;
import com.teste.ecommerce_api.demo.controller.dto.product.ProductDto;
import com.teste.ecommerce_api.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<String> saveProduct(@RequestBody ProductDto productDto) {

        log.info("Received request to create a product. Request body: {}", productDto );

        try {
            productService.createProduct(productDto);
            return ResponseEntity.ok("Produto salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar o produto: " + e.getMessage());
        }
    }

    @GetMapping("/product")
    public ResponseEntity<?> getProduct(@RequestParam String sku) {

        log.info("Received request to retrieve a product by sku: {}", sku);

        try {
           ProductFrontDto productDto= productService.getProductBySku(sku);
            return ResponseEntity.ok(productDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao retornar produto: " + e.getMessage());
        }
    }

    @GetMapping("/allProducts")
    public ResponseEntity<?> getAllProduct() {

        log.info("Received request to retrieve all products");

        try {
           List<ProductFrontDto> productsDto= productService.getAllProducts();
            return ResponseEntity.ok(productsDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao retornar produto: " + e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteBySku(@RequestParam String sku) {

        log.info("Received request to delete a product by sku {}", sku);

        try {
            return (ResponseEntity<?>) productService.deleteProductBySku(sku);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao retornar produto: " + e.getMessage());
        }
    }

}


