package com.enigmacamp.tokonyadia.controller;

import com.enigmacamp.tokonyadia.dto.request.ProductRequest;
import com.enigmacamp.tokonyadia.dto.request.ProductSearch;
import com.enigmacamp.tokonyadia.dto.response.PageResponseWrapper;
import com.enigmacamp.tokonyadia.dto.response.ProductResponse;
import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.service.ProductService;
import com.enigmacamp.tokonyadia.utils.constant.ApiUrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT)
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<PageResponseWrapper<ProductResponse>> getAllProduct(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                                                              @RequestParam(name = "size", defaultValue = "3") Integer size,
                                                                              @RequestParam(name = "sortBy", defaultValue = "productNMame")String sortBy,
                                                                              @RequestParam(name = "direction", defaultValue = "asc")String direction,
                                                                              @ModelAttribute ProductSearch productSearch){
        
//        direction.equalsIgnoreCase("asc") ? Sort sorting = Sort.by(sortBy).ascending() : Sort sorting = Sort.by(sortBy).descending();
//        Sort sorting = Sort.by(sortBy).ascending();
        int validPage = page > 0 ? page-1 : 0;
        Sort sorting = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(validPage, size ,sorting);
        return ResponseEntity.status(HttpStatus.OK).body(new PageResponseWrapper<>(productService.getAllProduct(pageable,productSearch)));
    }


    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable UUID id){
        return productService.getProductById(id).toResponse();
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest payload){
        Product product = productService.saveProduct(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(product.toResponse());
    }

    @PutMapping("{id}")
    public ProductResponse createProduct(@RequestBody ProductRequest payload, @PathVariable UUID id){
        Product product = productService.updateProduct(payload, id);
        return product.toResponse();
    }
}
