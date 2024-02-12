package com.example.ayrotekchallange.service;


import com.example.ayrotekchallange.domain.Product;
import com.example.ayrotekchallange.entity.ClientEntity;
import com.example.ayrotekchallange.entity.ProductEntity;
import com.example.ayrotekchallange.error.ErrorCodes;
import com.example.ayrotekchallange.exception.AyrotekException;
import com.example.ayrotekchallange.mapper.ClientMapper;
import com.example.ayrotekchallange.mapper.ProductMapper;
import com.example.ayrotekchallange.repository.ClientRepository;
import com.example.ayrotekchallange.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ProductService {


    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ClientRepository clientRepository, ClientMapper clientMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<Product> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return productMapper.toListDomainObject(products);
    }

    public Product createProduct(String clientId, Product product) {
        this.validateProduct(product);
        return save(clientId, product);

    }

    private Product save(String clientId, Product product) {

        ClientEntity client = clientRepository.findById(clientId).orElseThrow(() -> new AyrotekException(ErrorCodes.DATA_NOT_FOUND));
        client.getProducts().add(productMapper.toEntity(product));
        product.setClientName(client.getName());
        product.setClientId(clientId);
        return productMapper.toDomainObject(productRepository.save(productMapper.toEntity(product)));
    }

    public void deleteProduct(String clientId, String productId) {

        ClientEntity client = clientRepository.findById(clientId).orElseThrow(() -> new AyrotekException(ErrorCodes.DATA_NOT_FOUND));
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new AyrotekException(ErrorCodes.DATA_NOT_FOUND));

        if (!client.getProducts().contains(product)) {
            throw new AyrotekException(ErrorCodes.PRODUCT_NOT_BELONG_TO_CLIENT);
        }

        client.getProducts().remove(product);

        productRepository.deleteById(productId);

        clientRepository.save(client);
    }

    public double calculateTax(String productId) {
        Product product = productMapper.toDomainObject(productRepository.findById(productId).orElseThrow(() -> new AyrotekException(ErrorCodes.DATA_NOT_FOUND)));

        double productPrice = product.getPrice();

        return productPrice / 6;
    }

    private void validateProduct(Product domainObject) {
        if (Objects.isNull(domainObject)) {
            throw new AyrotekException(ErrorCodes.PRODUCT_DATA_CAN_NOT_BE_EMPTY);
        }
        if (!StringUtils.hasText(domainObject.getName())) {
            throw new AyrotekException(ErrorCodes.PRODUCT_NAME_CAN_NOT_BE_EMPTY);
        }
        if (!StringUtils.hasLength(String.valueOf(domainObject.getPrice()))) {
            throw new AyrotekException(ErrorCodes.PRODUCT_PRICE_CAN_NOT_BE_EMPTY);
        }
        if (!StringUtils.hasText(domainObject.getDescription())) {
            throw new AyrotekException(ErrorCodes.PRODUCT_DESCRIPTION_CAN_NOT_BE_EMPTY);
        }

    }

    public Product findByName(String productName) {
        return productRepository.findByName(productName).map(productMapper::toDomainObject).orElseThrow(() -> new AyrotekException(ErrorCodes.DATA_NOT_FOUND));
    }


    public Product findById(String id) {
        return productRepository.findById(id).map(productMapper::toDomainObject).orElseThrow(() -> new AyrotekException(ErrorCodes.DATA_NOT_FOUND));
    }
}
