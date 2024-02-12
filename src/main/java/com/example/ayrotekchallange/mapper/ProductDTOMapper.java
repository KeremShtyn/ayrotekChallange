package com.example.ayrotekchallange.mapper;

import com.example.ayrotekchallange.domain.Product;
import com.example.ayrotekchallange.dto.ProductDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductDTOMapper extends BaseDTOMapper<Product, ProductDTO>{
}
