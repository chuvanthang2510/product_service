package com.savvycom.product_service.service.impl;

import com.savvycom.product_service.domain.dto.brand.create.CreateBrand;
import com.savvycom.product_service.domain.dto.brand.update.UpdateBrand;
import com.savvycom.product_service.domain.entity.Brand;
import com.savvycom.product_service.repository.BrandRepository;
import com.savvycom.product_service.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Brand createBrand(CreateBrand createBrand) {
        Brand brand = new Brand();
        brand.setName(createBrand.getName());
        brandRepository.save(brand);
        return brand;
    }

    @Override
    public Brand updateBrand(UpdateBrand updateBrand) {
        Brand brand = this.brandRepository.findById(updateBrand.getBrandId()).orElse(null);
        brand.setName(updateBrand.getName());
        brandRepository.save(brand);
        return brand;
    }

    @Override
    public Brand deleteBrand(Long id) {
        Brand brand = this.brandRepository.findById(id).orElse(null);
        brandRepository.delete(brand);
        return brand;
    }


}
