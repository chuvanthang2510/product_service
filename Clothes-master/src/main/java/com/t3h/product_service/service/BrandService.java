package com.savvycom.product_service.service;

import com.savvycom.product_service.domain.dto.brand.create.CreateBrand;
import com.savvycom.product_service.domain.dto.brand.update.UpdateBrand;
import com.savvycom.product_service.domain.entity.Brand;

public interface BrandService {

    Brand createBrand(CreateBrand createBrand);

    Brand updateBrand(UpdateBrand updateBrand);

    Brand deleteBrand(Long id);

}
