package com.savvycom.product_service.repository;

import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailDTO;
import com.savvycom.product_service.domain.entity.ProductDetail;

import java.util.List;

public interface ProductDetailCustomRepository {

    ProductDetail getProductDetail(Long productId, String color, String size);

    List<ProductDetailDTO> getProductDetail(Long id);
}
