package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.dto.product_detail.create_product_detail.AddProductDetailRequest;
import com.savvycom.product_service.domain.entity.ProductDetail;
import org.springframework.http.ResponseEntity;

public interface ProductDetailService {

    ResponseEntity<?> getProductDetail(Long id);

    ResponseEntity<?> addProductDetail(AddProductDetailRequest addProductDetailRequest, Long productId);

    ProductDetail getProductDetail(Long productId, String color, String size);

    ResponseEntity<?> subQuantity(Long productId, String color, String size, Integer quantity);

}
