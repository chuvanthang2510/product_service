package com.savvycom.product_service.repository;

import com.savvycom.product_service.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE name LIKE %:name%", nativeQuery = true)
    List<Product> getByName(String name);

}
