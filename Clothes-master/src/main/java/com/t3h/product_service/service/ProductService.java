package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.dto.product.get_product.ProductDTO;
import com.savvycom.product_service.domain.dto.product.product_review.ProductReviewRequest;
import com.savvycom.product_service.domain.entity.Product;
import org.springframework.http.ResponseEntity;

import com.savvycom.product_service.domain.dto.product.create_product.CreateProductRequest;

public interface ProductService {

    ResponseEntity<?> createProduct(CreateProductRequest createProductRequest);

    ProductDTO getProduct(Long id);

    ResponseEntity<?> getAllProduct();

    ResponseEntity<?> getAllProductByPage(Integer pageNum, Integer pageSize);

//    Product findById(Long productId) throws NotFoundException;
//
//    Product create(ProductDTO productDTO) throws NotFoundException, IOException;
//
//    Product update(Long productId, ProductDTO productDTO) throws NotFoundException, IOException;
//
//    void delete(Long productId) throws NotFoundException;
//
//    List<Product> searchProduct(Integer page, Integer size, FilterDTO filterDTO) throws NotFoundException;
//
//    List<Product> getAllProduct(Integer page, Integer size);
//
    Product productReview(ProductReviewRequest productReviewRequest);
}
