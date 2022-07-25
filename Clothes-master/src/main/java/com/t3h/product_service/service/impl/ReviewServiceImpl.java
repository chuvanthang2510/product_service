package com.savvycom.product_service.service.impl;

import com.savvycom.product_service.domain.entity.Review;
import com.savvycom.product_service.repository.ReviewRepository;
import com.savvycom.product_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> getReviewsByProduct(Long productId) {
        List<Review> reviews = this.reviewRepository.findByProductId(productId);
        return reviews;
    }
}
