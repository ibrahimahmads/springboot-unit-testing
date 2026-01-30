package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.dto.request.ProductRequest;
import com.enigmacamp.tokonyadia.dto.response.ProductResponse;
import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.repository.ProductRepository;
import com.enigmacamp.tokonyadia.utils.constant.ResponseMessage;
import com.enigmacamp.tokonyadia.utils.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(ProductRequest payload) {
        Product product = Product.builder()
                .productName(payload.name())
                .productPrice(payload.price())
                .stock(payload.stock())
                .build();
        return productRepository.save(product);
    }

    @Override
    public Page<ProductResponse> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable).map(Product::toResponse);
    }

    @Override
    public Product getProductById(UUID id) {
        if (productRepository.findById(id).isPresent()){
            return productRepository.findById(id).get();
        } else {
            throw new DataNotFoundException(String.format(ResponseMessage.NOT_FOUND_MESSAGE, ResponseMessage.PRODUCT, id));
        }
    }

    @Override
    public void deleteProduct(UUID id) {

    }

    @Override
    public Product updateProduct(ProductRequest payload, UUID id) {
        Product product = Product.builder()
                .productName(payload.name())
                .productPrice(payload.price())
                .stock(payload.stock())
                .id(id)
                .build();
        return productRepository.save(product);
    }
}
