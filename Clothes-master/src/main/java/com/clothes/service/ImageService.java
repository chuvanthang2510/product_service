package com.clothes.service;

import com.clothes.domain.entity.Image;
import javassist.NotFoundException;

public interface ImageService {

    void delete(Long imageId) throws NotFoundException;
}
