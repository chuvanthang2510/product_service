package com.savvycom.product_service.service.impl;

import com.savvycom.product_service.controller.BaseController;
import com.savvycom.product_service.domain.dto.product_detail.create_product_detail.AddProductDetailRequest;
import com.savvycom.product_service.domain.entity.Product;
import com.savvycom.product_service.domain.entity.ProductDetail;
import com.savvycom.product_service.repository.ProductDetailCustomRepository;
import com.savvycom.product_service.repository.ProductDetailRepository;
import com.savvycom.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDetailService extends BaseController implements com.savvycom.product_service.service.ProductDetailService {

    private final ProductDetailCustomRepository productDetailCustomRepository;

    private final ProductRepository productRepository;

    private final ProductDetailRepository productDetailRepository;

    @Override
    public ResponseEntity<?> getProductDetail(Long id) {
        return toSuccessResult(this.productDetailCustomRepository.getProductDetail(id), "Successful");
    }

    @Override
    public ResponseEntity<?> addProductDetail(AddProductDetailRequest addProductDetailRequest, Long productId) {
        Optional<Product> product = this.productRepository.findById(productId);
        if (product.isEmpty()) return toExceptionResult("Khong tim thay san pham", 400);
        try {
            ProductDetail productDetail = new ProductDetail();
            productDetail.setColor(addProductDetailRequest.getColor());
            productDetail.setSize(addProductDetailRequest.getSize());
            productDetail.setQuantity(addProductDetailRequest.getQuantity());
            productDetail.setProduct(product.get());
            productDetailRepository.save(productDetail);
            return toSuccessResult(productDetail, "Them chi tiet san pham thanh cong");
        } catch (Exception e){
            return toExceptionResult("Loi them chi tiet san pham", 500);
        }
    }

    @Override
    public ProductDetail getProductDetail(Long productId, String color, String size) {
        ProductDetail productDetail = this.productDetailCustomRepository.getProductDetail(productId,color,size);
        return productDetail;
    }

    @Override
    public ResponseEntity<?> subQuantity(Long productId, String color, String size, Integer quantity) {
        ProductDetail productDetail = this.productDetailCustomRepository.getProductDetail(productId,color,size);
        if (productDetail == null) return toExceptionResult("Không tìm thấy sản phẩm", 400);
        int productQuantity = productDetail.getQuantity();
        productDetail.setQuantity(productQuantity -quantity);
        productDetailRepository.save(productDetail);
        return toSuccessResult(productDetail, "Successful");
    }
}
