package com.example.ayrotekchallange.controller;

import com.example.ayrotekchallange.api.ProductAPI;
import com.example.ayrotekchallange.domain.Product;
import com.example.ayrotekchallange.dto.ProductDTO;
import com.example.ayrotekchallange.mapper.ProductDTOMapper;
import com.example.ayrotekchallange.service.ProductService;

import java.util.List;

public class ProductController implements ProductAPI {

    private final ProductService productService;
    private ProductDTOMapper productDTOMapper;


    public ProductController(ProductService productService, ProductDTOMapper productDTOMapper) {
        this.productDTOMapper = productDTOMapper;
        this.productService = productService;
    }

    @Override
    public ProductDTO getById(String id) {
        Product product = productService.findById(id);
        return productDTOMapper.toDTO(product);
    }

    @Override
    public ProductDTO save(String clientId,ProductDTO productDTO) {
        Product product = productService.createProduct(clientId, productDTOMapper.toDomain(productDTO));
        return productDTOMapper.toDTO(product);
    }

    @Override
    public ProductDTO update(String clientId, ProductDTO productDTO) {
        return save(clientId, productDTO);
    }

    @Override
    public List<ProductDTO> getAll() {
        return productDTOMapper.toListDTO(productService.getAllProducts());
    }

    @Override
    public void delete(String clientId, String id) {
        productService.deleteProduct(clientId, id);
    }
}
