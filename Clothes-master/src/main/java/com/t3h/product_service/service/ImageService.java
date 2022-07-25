package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.dto.brand.create.CreateBrand;
import com.savvycom.product_service.domain.dto.brand.update.UpdateBrand;
import com.savvycom.product_service.domain.dto.image.create.CreateImage;
import com.savvycom.product_service.domain.dto.image.update.UpdateImage;
import com.savvycom.product_service.domain.entity.Brand;
import com.savvycom.product_service.domain.entity.Image;

public interface ImageService {

    Image createImage(CreateImage createImage);

    Image updateImage(UpdateImage updateImage);

    Image deleteImage(Long id);

}
