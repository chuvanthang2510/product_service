package com.savvycom.product_service.repository;

import com.savvycom.product_service.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.productId = :id")
    List<Review> findByProductId(Long id);
}
