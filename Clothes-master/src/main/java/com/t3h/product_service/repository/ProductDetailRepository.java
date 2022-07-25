package com.savvycom.product_service.repository;

import com.savvycom.product_service.domain.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {


}
