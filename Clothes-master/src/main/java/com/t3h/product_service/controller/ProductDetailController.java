package com.savvycom.product_service.controller;

import com.savvycom.product_service.domain.dto.product_detail.create_product_detail.AddProductDetailRequest;
import com.savvycom.product_service.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-detail")
public class ProductDetailController extends  BaseController {

    private final ProductDetailService productDetailService;

    @GetMapping("/get-by-product-id/{id}")
    public ResponseEntity<?> getProductDetailByProductId(@PathVariable("id") Long id){
        return this.productDetailService.getProductDetail(id);
    }

    @PostMapping("/add-product-detail/{id}")
    public ResponseEntity<?>  addProductDeatail(@RequestBody AddProductDetailRequest addProductDetailRequest, @PathVariable Long id) {
        return this.productDetailService.addProductDetail(addProductDetailRequest, id);
    }
}
