package com.clothes.repository;

import com.clothes.domain.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByName(String name);
}
