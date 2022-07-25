package com.savvycom.product_service.controller;

import com.savvycom.product_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController extends  BaseController{

    private final ReviewService reviewService;

    @GetMapping("/get-reviews-by-product/{id}")
    public ResponseEntity<?> getReviewsByProduct(@PathVariable("id") Long id){
        return  toSuccessResult(this.reviewService.getReviewsByProduct(id), "Successful");
    }


}
