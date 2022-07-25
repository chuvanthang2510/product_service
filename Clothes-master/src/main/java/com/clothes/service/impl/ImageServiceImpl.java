package com.clothes.service.impl;

import com.clothes.domain.entity.Image;
import com.clothes.repository.ImageRepository;
import com.clothes.service.FileService;
import com.clothes.service.ImageService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileService fileService;

    @Override
    public void delete(Long imageId) throws NotFoundException {
        Optional<Image> imageOptional = imageRepository.findById(imageId);
        if (!imageOptional.isPresent()) {
            throw new NotFoundException("Image not foud");
        }
        fileService.deleteFile(imageOptional.get().getLink());
        imageRepository.delete(imageOptional.get());
    }
}
