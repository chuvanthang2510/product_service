package com.savvycom.product_service.domain.dto.product_detail.create_product_detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductDetailRequest {

    private String size;

    private String color;

    private Integer quantity;

}
