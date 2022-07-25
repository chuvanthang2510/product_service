package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getReviewsByProduct(Long productId);

}
