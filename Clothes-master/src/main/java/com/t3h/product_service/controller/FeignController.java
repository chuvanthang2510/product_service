package com.savvycom.product_service.controller;

import com.savvycom.product_service.domain.dto.product.get_product.ProductDTO;
import com.savvycom.product_service.domain.entity.ProductDetail;
import com.savvycom.product_service.service.ProductDetailService;
import com.savvycom.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign")
public class FeignController {

    private final ProductDetailService productDetailService;

    private final ProductService productService;

    @GetMapping("/check-stock")
    public ProductDetail getProductDetail(
                                    @Param("productId") Long productId,
                                    @Param("color") String color,
                                    @Param("size") String size){
        return this.productDetailService.getProductDetail(productId,color,size);
    }

    @GetMapping("/get-product-by-id/{id}")
    public ProductDTO getProduct(@PathVariable("id") Long id){
        return this.productService.getProduct(id);
    }

    @PostMapping("/sub-quantity")
    public ResponseEntity<?> subQuantity(
            @Param("productId") Long productId,
            @Param("color") String color,
            @Param("size") String size,
            @Param("quantity") Integer quantity){
        return this.productDetailService.subQuantity(productId,color,size,quantity);
    }
}
