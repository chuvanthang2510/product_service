package com.savvycom.product_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient
@EnableFeignClients
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private BrandRepository brandRepository;
//
//    @Autowired
//    private SizeRepository sizeRepository;
//
//    @Autowired
//    private ColorRepository colorRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Category
//        if (categoryRepository.findByName("Nam") == null) {
//            categoryRepository.save(Category.builder().name("Nam").build());
//        }
//        if (categoryRepository.findByName("Nữ") == null) {
//            categoryRepository.save(Category.builder().name("Nữ").build());
//        }
//        if (categoryRepository.findByName("Trẻ em") == null) {
//            categoryRepository.save(Category.builder().name("Trẻ em").build());
//        }
//        // Brand
//        if (brandRepository.findByName("Brand 1") == null) {
//            brandRepository.save(Brand.builder().name("Brand 1").build());
//        }
//        if (brandRepository.findByName("Brand 2") == null) {
//            brandRepository.save(Brand.builder().name("Brand 2").build());
//        }
//        if (brandRepository.findByName("Brand 3") == null) {
//            brandRepository.save(Brand.builder().name("Brand 3").build());
//        }
//        // Size
//        if (sizeRepository.findByName("S") == null) {
//            sizeRepository.save(Size.builder().name("S").build());
//        }
//        if (sizeRepository.findByName("M") == null) {
//            sizeRepository.save(Size.builder().name("M").build());
//        }
//        if (sizeRepository.findByName("L") == null) {
//            sizeRepository.save(Size.builder().name("L").build());
//        }
//        // Color
//        if (colorRepository.findByName("Vàng") == null) {
//            colorRepository.save(Color.builder().name("Vàng").build());
//        }
//        if (colorRepository.findByName("Đỏ") == null) {
//            colorRepository.save(Color.builder().name("Đỏ").build());
//        }
//        if (colorRepository.findByName("Trắng") == null) {
//            colorRepository.save(Color.builder().name("Trắng").build());
//        }
//    }
}

