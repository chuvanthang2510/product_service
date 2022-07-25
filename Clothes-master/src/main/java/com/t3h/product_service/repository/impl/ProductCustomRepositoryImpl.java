package com.savvycom.product_service.repository.impl;

import com.savvycom.product_service.domain.dto.product.get_product.ProductDTO;
import com.savvycom.product_service.repository.ProductCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<ProductDTO> getAllProduct() {
        TypedQuery<ProductDTO> query =
                entityManager.createQuery("select new com.savvycom.product_service.domain.dto.product.get_product.ProductDTO"
                + "(p.productId, p.name, p.description, p.numberOfRate , p.price, p.rate , p.type, p.brand.name)"
                + " from Product p ", ProductDTO.class);
        return query.getResultList();
    }

    @Override
    public List<ProductDTO> getAllProductByPage(Integer pageNum, Integer pageSize) {
        TypedQuery<ProductDTO> query =
                entityManager.createQuery("select new com.savvycom.product_service.domain.dto.product.get_product.ProductDTO"
                        + "(p.productId, p.name, p.description, p.numberOfRate , p.price, p.rate , p.type, p.brand.name)"
                        + " from Product p ", ProductDTO.class);
        int position = pageNum*pageSize;
        query.setFirstResult(position);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public ProductDTO getProduct(Long id) {
        TypedQuery<ProductDTO> query =
                entityManager.createQuery("select new com.savvycom.product_service.domain.dto.product.get_product.ProductDTO"
                        + "(p.productId, p.name, p.description, p.numberOfRate , p.price, p.rate , p.type, p.brand.name)"
                        + " from Product p where p.productId = :id", ProductDTO.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }


}
