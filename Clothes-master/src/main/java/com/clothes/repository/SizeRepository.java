package com.clothes.repository;

import com.clothes.domain.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Long> {
    Size findByName(String name);
}
