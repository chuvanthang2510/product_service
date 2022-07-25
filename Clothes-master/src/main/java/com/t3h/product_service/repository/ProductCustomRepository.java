package com.savvycom.product_service.repository;

import com.savvycom.product_service.domain.dto.product.get_product.ProductDTO;

import java.util.List;

public interface ProductCustomRepository {

    List<ProductDTO> getAllProduct();

    List<ProductDTO> getAllProductByPage(Integer pageNum, Integer pageSize);

    ProductDTO getProduct(Long id);

}
