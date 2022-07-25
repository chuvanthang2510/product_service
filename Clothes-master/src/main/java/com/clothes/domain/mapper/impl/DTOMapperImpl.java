package com.clothes.domain.mapper.impl;

import com.clothes.domain.dto.ProductDTO;
import com.clothes.domain.entity.Color;
import com.clothes.domain.entity.Product;
import com.clothes.domain.entity.Size;
import com.clothes.domain.mapper.DTOMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOMapperImpl implements DTOMapper {
    @Override
    public ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        if (product != null) {
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setShortDescription(productDTO.getShortDescription());
            productDTO.setLongDescription(product.getLongDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setImportPrice(product.getImportPrice());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setStatus(product.getStatus());
            productDTO.setCreatedDate(product.getCreatedDate());
            productDTO.setModifiedDate(product.getModifiedDate());
            if (product.getCategory() != null) {
                productDTO.setCategoryId(product.getCategory().getId());
            }
            if (product.getBrand() != null) {
                productDTO.setBrandId(product.getBrand().getId());
            }
            if (!CollectionUtils.isEmpty(product.getColors())) {
                List<Long> colorIds = product.getColors().stream().map(Color::getId).collect(Collectors.toList());
                productDTO.setColorIds(colorIds.toArray(new Long[colorIds.size()]));
            }
            if (!CollectionUtils.isEmpty(product.getSizes())) {
                List<Long> sizeIds = product.getSizes().stream().map(Size::getId).collect(Collectors.toList());
                productDTO.setSizeIds(sizeIds.toArray(new Long[sizeIds.size()]));
            }
        }
        return productDTO;
    }

    @Override
    public List<ProductDTO> toProductDTO(List<Product> products) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(products)) {
            for (Product product : products) {
                productDTOS.add(this.toProductDTO(product));
            }
        }
        return productDTOS;
    }
}
