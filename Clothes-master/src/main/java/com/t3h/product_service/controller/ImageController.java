package com.savvycom.product_service.controller;

import com.savvycom.product_service.domain.dto.brand.create.CreateBrand;
import com.savvycom.product_service.domain.dto.brand.update.UpdateBrand;
import com.savvycom.product_service.domain.dto.image.create.CreateImage;
import com.savvycom.product_service.domain.dto.image.update.UpdateImage;
import com.savvycom.product_service.domain.entity.Brand;
import com.savvycom.product_service.domain.entity.Image;
import com.savvycom.product_service.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController extends  BaseController{

    private final ImageService imageService;

    @GetMapping("/create")
    public ResponseEntity<?> createImage(@RequestBody CreateImage createImage){
        Image image = this.imageService.createImage(createImage);
        if (image == null) return toExceptionResult("Them image that bai", 400);
        return toSuccessResult(image, "Them image thanh cong");
    }

    @PostMapping("/create")
    public ResponseEntity<?> updateImage(@RequestBody UpdateImage updateImage){
        Image image = this.imageService.updateImage(updateImage);
        if (image == null) return toExceptionResult("Update image that bai", 400);
        return toSuccessResult(image, "Update image thanh cong");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long id){
        Image image = this.imageService.deleteImage(id);
        if (image == null) return toExceptionResult("Xoa image that bai", 400);
        return toSuccessResult(image, "Xoa image thanh cong");
    }

}
