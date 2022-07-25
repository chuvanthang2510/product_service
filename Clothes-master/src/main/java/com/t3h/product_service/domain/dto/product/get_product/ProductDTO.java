package com.savvycom.product_service.domain.dto.product.get_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long productId;

    private String name;

    private String description;

    private Integer numberOfRate;

    private BigDecimal price;

    private Double rate;

    private String type;

    private String brandName;



}
