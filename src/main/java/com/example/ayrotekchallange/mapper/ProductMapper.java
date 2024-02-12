package com.example.ayrotekchallange.mapper;

import com.example.ayrotekchallange.domain.Product;
import com.example.ayrotekchallange.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductEntity, Product> {

    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "clientId", source = "client.id")
    Product toDomainObject(ProductEntity entity);

    @Mapping(target = "client.name", source = "clientName")
    @Mapping(target = "client.id", source = "clientId")
    ProductEntity toEntity(Product product);
}
