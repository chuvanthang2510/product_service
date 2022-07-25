package com.savvycom.product_service.domain.dto.product_detail.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {

    private Long productDetailId;

    private String size;

    private String color;

    private Integer quantity;

}
