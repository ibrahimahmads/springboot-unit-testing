package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.dto.request.ProductRequest;
import com.enigmacamp.tokonyadia.dto.request.ProductSearch;
import com.enigmacamp.tokonyadia.dto.response.ProductResponse;
import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.repository.ProductRepository;
import com.enigmacamp.tokonyadia.utils.exceptions.DataNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void saveProduct_shouldReturnSavedProduct() {
        // Given
        ProductRequest request = new ProductRequest(
                "Laptop",
                2000000.00,
                10
        ) ;

        Product savedProduct = Product.builder()
                .id(UUID.randomUUID())
                .productName("Laptop")
                .productPrice(20_000_000.00)
                .stock(10)
                .build();

        // When
        when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(savedProduct);
        Product result = productService.saveProduct(request);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getProductName()).isEqualTo("Laptop");
        assertThat(result.getProductPrice()).isEqualTo(20_000_000.00);
        assertThat(result.getStock()).isEqualTo(10);
        verify(productRepository, times(1)).save(Mockito.any(Product.class));
    }

    @Test
    void getAllProduct_shouldReturnPageOfProductResponse() {
        Pageable pageable = PageRequest.of(0, 10);
        ProductSearch productSearch = new ProductSearch();
        Product product = Product.builder()
                .id(UUID.randomUUID())
                .productName("Laptop")
                .productPrice(20_000_000.00)
                .stock(10)
                .build();

        Page<Product> productPage = new PageImpl<>(List.of(product),pageable,1);

        when(productRepository.findAll(any(Specification.class),eq(pageable))).thenReturn(productPage);

        Page<ProductResponse> result = productService.getAllProduct(pageable,productSearch);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Laptop");

        verify(productRepository, times(1)).findAll(any(Specification.class),eq(pageable));
    }

    @Test
    void getProductById_shouldReturnProducte() {
        UUID id = UUID.randomUUID();
        Product product = Product.builder()
                .id(id)
                .productName("Laptop")
                .productPrice(20_000_000.00)
                .stock(10)
                .build();

        when (productRepository.findById(any(UUID.class))).thenReturn(Optional.of(product));
        Product result = productService.getProductById(id);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getProductName()).isEqualTo("Laptop");
        verify(productRepository, times(2)).findById(id);
    }

    @Test
    void getProductById_shouldThrowDataNotFoundException() {
        UUID id = UUID.randomUUID();

        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.getProductById(id))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("Data product");
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void updateProduct_shouldReturnUpdatedProduct() {
        ProductRequest request = new ProductRequest(
                "Laptop",
                2000000.00,
                10
        );
        UUID id = UUID.randomUUID();
        Product updatedProduct = Product.builder()
                .id(id)
                .productName("Laptop")
                .productPrice(20_000_000.00)
                .stock(10)
                .build();

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.updateProduct(request, id);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getProductName()).isEqualTo("Laptop");
        assertThat(result.getProductPrice()).isEqualTo(20_000_000.00);
        assertThat(result.getStock()).isEqualTo(10);
        verify(productRepository, times(1)).save(Mockito.any(Product.class));
    }
}