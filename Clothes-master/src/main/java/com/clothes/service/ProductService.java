package com.clothes.service;

import com.clothes.domain.dto.FilterDTO;
import com.clothes.domain.dto.ProductDTO;
import com.clothes.domain.entity.Product;
import javassist.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    Product findById(Long productId) throws NotFoundException;

    Product create(ProductDTO productDTO) throws NotFoundException, IOException;

    Product update(Long productId, ProductDTO productDTO) throws NotFoundException, IOException;

    void delete(Long productId) throws NotFoundException;

    List<Product> searchProduct(Integer page, Integer size, FilterDTO filterDTO) throws NotFoundException;

    List<Product> getAllProduct(Integer page, Integer size);
}
