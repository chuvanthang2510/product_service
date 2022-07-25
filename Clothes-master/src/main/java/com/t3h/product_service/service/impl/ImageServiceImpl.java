package com.savvycom.product_service.service.impl;

import com.savvycom.product_service.domain.dto.image.create.CreateImage;
import com.savvycom.product_service.domain.dto.image.update.UpdateImage;
import com.savvycom.product_service.domain.entity.Brand;
import com.savvycom.product_service.domain.entity.Image;
import com.savvycom.product_service.domain.entity.Product;
import com.savvycom.product_service.repository.ImageRepository;
import com.savvycom.product_service.repository.ProductRepository;
import com.savvycom.product_service.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final ProductRepository productRepository;

    @Override
    public Image createImage(CreateImage createImage) {
        Optional<Product> product = this.productRepository.findById(createImage.getProductId());
        if (product.isEmpty()) return null;
        Image image = new Image();
        image.setImage(createImage.getImage());
        image.setProduct(product.get());
        return image;
    }

    @Override
    public Image updateImage(UpdateImage updateImage) {
        Optional<Product> product = this.productRepository.findById(updateImage.getProductId());
        if (product.isEmpty()) return null;
        Optional<Image> imageOpt = this.imageRepository.findById(updateImage.getImageId());
        if (imageOpt.isEmpty()) return null;
        Image image = imageOpt.get();
        image.setImage(updateImage.getImage());
        image.setProduct(product.get());
        return image;
    }

    @Override
    public Image deleteImage(Long id) {
        Optional<Image> image = this.imageRepository.findById(id);
        if (image.isEmpty()) return null;
        imageRepository.delete(image.get());
        return image.get();
    }
}
