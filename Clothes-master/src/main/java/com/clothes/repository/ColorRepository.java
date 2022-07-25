package com.clothes.repository;

import com.clothes.domain.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
    Color findByName(String name);
}
