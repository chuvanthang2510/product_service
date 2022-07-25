package com.clothes.domain.mapper;

import com.clothes.domain.dto.ProductDTO;
import com.clothes.domain.entity.Product;

import java.util.List;

public interface DTOMapper {

    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toProductDTO(List<Product> products);
}
