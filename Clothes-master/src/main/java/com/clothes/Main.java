package com.clothes;

import com.clothes.domain.entity.Brand;
import com.clothes.domain.entity.Category;
import com.clothes.domain.entity.Color;
import com.clothes.domain.entity.Size;
import com.clothes.repository.BrandRepository;
import com.clothes.repository.CategoryRepository;
import com.clothes.repository.ColorRepository;
import com.clothes.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public void run(String... args) throws Exception {
        // Category
        if (categoryRepository.findByName("Nam") == null) {
            categoryRepository.save(Category.builder().name("Nam").build());
        }
        if (categoryRepository.findByName("Nữ") == null) {
            categoryRepository.save(Category.builder().name("Nữ").build());
        }
        if (categoryRepository.findByName("Trẻ em") == null) {
            categoryRepository.save(Category.builder().name("Trẻ em").build());
        }
        // Brand
        if (brandRepository.findByName("Brand 1") == null) {
            brandRepository.save(Brand.builder().name("Brand 1").build());
        }
        if (brandRepository.findByName("Brand 2") == null) {
            brandRepository.save(Brand.builder().name("Brand 2").build());
        }
        if (brandRepository.findByName("Brand 3") == null) {
            brandRepository.save(Brand.builder().name("Brand 3").build());
        }
        // Size
        if (sizeRepository.findByName("S") == null) {
            sizeRepository.save(Size.builder().name("S").build());
        }
        if (sizeRepository.findByName("M") == null) {
            sizeRepository.save(Size.builder().name("M").build());
        }
        if (sizeRepository.findByName("L") == null) {
            sizeRepository.save(Size.builder().name("L").build());
        }
        // Color
        if (colorRepository.findByName("Vàng") == null) {
            colorRepository.save(Color.builder().name("Vàng").build());
        }
        if (colorRepository.findByName("Đỏ") == null) {
            colorRepository.save(Color.builder().name("Đỏ").build());
        }
        if (colorRepository.findByName("Trắng") == null) {
            colorRepository.save(Color.builder().name("Trắng").build());
        }
    }
}
