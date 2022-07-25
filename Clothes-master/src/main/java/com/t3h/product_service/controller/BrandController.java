package com.savvycom.product_service.controller;

import com.savvycom.product_service.domain.dto.brand.create.CreateBrand;
import com.savvycom.product_service.domain.dto.brand.update.UpdateBrand;
import com.savvycom.product_service.domain.entity.Brand;
import com.savvycom.product_service.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController extends  BaseController{

    private final BrandService brandService;

    @GetMapping("/create")
    public ResponseEntity<?> createBrand(@RequestBody CreateBrand createBrand){
        Brand brand = this.brandService.createBrand(createBrand);
        if (brand == null) return toExceptionResult("Them brand that bai", 400);
        return toSuccessResult(brand, "Them brand thanh cong");
    }

    @PostMapping("/create")
    public ResponseEntity<?> updateBrand(@RequestBody UpdateBrand updateBrand){
        Brand brand = this.brandService.updateBrand(updateBrand);
        if (brand == null) return toExceptionResult("Update brand that bai", 400);
        return toSuccessResult(brand, "Update brand thanh cong");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") Long id){
        Brand brand = this.brandService.deleteBrand(id);
        if (brand == null) return toExceptionResult("Xoa brand that bai", 400);
        return toSuccessResult(brand, "Xoa brand thanh cong");
    }



}
