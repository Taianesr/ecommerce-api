package com.teste.ecommerce_api.demo.mapper;

import com.teste.ecommerce_api.demo.controller.dto.front.ProductFrontDto;
import com.teste.ecommerce_api.demo.controller.dto.product.ProductDto;
import com.teste.ecommerce_api.demo.controller.dto.product.UnitAmountProductDto;
import com.teste.ecommerce_api.demo.controller.dto.product.UpcProductDto;
import com.teste.ecommerce_api.demo.entity.ProductEntity;
import com.teste.ecommerce_api.demo.entity.UnitAmountProduct;
import com.teste.ecommerce_api.demo.entity.UpcProduct;
import com.teste.ecommerce_api.demo.service.BucketService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    protected BucketService bucketService;

    public abstract ProductFrontDto toFrontDto(ProductEntity productEntity);

    public ProductFrontDto toFrontDto(ProductEntity entity, String imageUrl) {
        ProductFrontDto dto = toFrontDto(entity);
        return new ProductFrontDto(
                dto.id(),
                dto.name(),
                dto.description(),
                dto.unitAmountProduct(),
                dto.category(),
                dto.sku(),
                imageUrl,
                dto.url(),
                dto.upc()
        );
    }

    public abstract ProductEntity toEntity(ProductDto dto);

    public abstract ProductDto toDto(ProductEntity productEntity);

    public abstract UpcProduct toEntity(UpcProductDto dto);

    public abstract UpcProductDto toDto(UpcProduct entity);

    public abstract UnitAmountProductDto toDto(UnitAmountProduct entity);

    public abstract UnitAmountProduct toEntity(UnitAmountProductDto dto);

}
