package com.savvycom.product_service.domain.dto.product.create_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private String type;

    private Long brandId;

}
