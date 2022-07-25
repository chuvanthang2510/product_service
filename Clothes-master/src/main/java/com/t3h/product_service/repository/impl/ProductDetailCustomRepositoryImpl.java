package com.savvycom.product_service.repository.impl;

import com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailDTO;
import com.savvycom.product_service.domain.entity.ProductDetail;
import com.savvycom.product_service.repository.ProductDetailCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDetailCustomRepositoryImpl implements ProductDetailCustomRepository {

    private final EntityManager entityManager;

    @Override
    public ProductDetail getProductDetail(Long productId, String color, String size) {
        TypedQuery<ProductDetail> query =
                entityManager.createQuery("select p from ProductDetail p where p.product.productId = :productId " +
                        "and p.color = :color and p.size = :size" , ProductDetail.class);
        query.setParameter("productId",productId);
        query.setParameter("color",color);
        query.setParameter("size",size);
        return query.getSingleResult();
    }

    @Override
    public List<ProductDetailDTO> getProductDetail(Long id) {
        TypedQuery<ProductDetailDTO> query =
                entityManager.createQuery("select new com.savvycom.product_service.domain.dto.product_detail.get.ProductDetailDTO"
                        + "(p.productDetailId, p.size, p.color, p.quantity)"
                        + " from ProductDetail p where p.product.productId = :id", ProductDetailDTO.class);
        query.setParameter("id",id);
        return query.getResultList();
    }

}
