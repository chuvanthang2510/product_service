package com.savvycom.product_service.controller;


import com.savvycom.product_service.domain.dto.product.create_product.CreateProductRequest;
import com.savvycom.product_service.domain.dto.product.product_review.ProductReviewRequest;
import com.savvycom.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController extends BaseController{

    private final ProductService productService;


    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest createProductRequest) {
    	return this.productService.createProduct(createProductRequest);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?>  getAllProduct() {
        return this.productService.getAllProduct();
    }

    @GetMapping("/get-all-by-page")
    public ResponseEntity<?>  getAllProductByPage(@RequestParam(value = "pageNum", required = false) Optional<Integer> pageNum,
                                                  @RequestParam(value = "pageSize", required = false) Optional<Integer> pageSize) {
        return this.productService.getAllProductByPage(pageNum.orElse(0), pageSize.orElse(10));
    }


//    @Autowired
//    private DTOMapper dtoMapper;

//    @PreAuthorize("hasRole('ROLE_USER')")
//    @GetMapping("/test-user")
//    public String testUser() {
//    	return "user";
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping("/test-admin")
//    public String testUser1() {
//    	return "admin";
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createProduct(@Validated ProductDTO productDTO) {
//        try {
//            return new ResponseEntity<>(ResponseMessage.builder()
//                    .code(OK.value())
//                    .success(Boolean.TRUE)
//                    .message("Create product successful")
//                    .data(dtoMapper.toProductDTO(productService.create(productDTO))).build(), OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(ResponseMessage.builder()
//                    .code(BAD_REQUEST.value())
//                    .success(Boolean.TRUE)
//                    .message(e.getMessage()).build(), BAD_REQUEST);
//        }
//    }
//
//    @PatchMapping(value = "/{id}")
//    public ResponseEntity<?> updateProduct(@PathVariable(name = "id") Long productId,
//                                           @Validated ProductDTO productDTO) {
//        try {
//            return new ResponseEntity<>(ResponseMessage.builder()
//                    .code(OK.value())
//                    .success(Boolean.TRUE)
//                    .message("Update product successful")
//                    .data(dtoMapper.toProductDTO(productService.update(productId, productDTO))).build(), OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(ResponseMessage.builder()
//                    .code(BAD_REQUEST.value())
//                    .success(Boolean.TRUE)
//                    .message(e.getMessage()).build(), BAD_REQUEST);
//        }
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long productId) {
//        try {
//            productService.delete(productId);
//            return new ResponseEntity<>(ResponseMessage.builder()
//                    .code(OK.value())
//                    .success(Boolean.TRUE)
//                    .message("Delete product successful").build(), OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(ResponseMessage.builder()
//                    .code(BAD_REQUEST.value())
//                    .success(Boolean.TRUE)
//                    .message(e.getMessage()).build(), BAD_REQUEST);
//        }
//    }
//
//    @GetMapping(value = "/search")
//    public ResponseEntity<?> searchProduct(@RequestBody FilterDTO filterDTO,
//                                           @RequestParam("page") Integer page,
//                                           @RequestParam("size") Integer size) {
//        try {
//            return new ResponseEntity<>(ResponseMessage.builder()
//                    .code(OK.value())
//                    .success(Boolean.TRUE)
//                    .message("Search product successful")
//                    .data(dtoMapper.toProductDTO(productService.searchProduct(page, size, filterDTO))).build(), OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(ResponseMessage.builder()
//                    .code(BAD_REQUEST.value())
//                    .success(Boolean.TRUE)
//                    .message(e.getMessage()).build(), BAD_REQUEST);
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<?> getAllProduct(@RequestParam("page") Integer page,
//                                           @RequestParam("size") Integer size) {
//        try {
//            return new ResponseEntity<>(ResponseMessage.builder()
//                    .code(OK.value())
//                    .success(Boolean.TRUE)
//                    .message("Get all product successful")
//                    .data(dtoMapper.toProductDTO(productService.getAllProduct(page, size))).build(), OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(ResponseMessage.builder()
//                    .code(BAD_REQUEST.value())
//                    .success(Boolean.TRUE)
//                    .message(e.getMessage()).build(), BAD_REQUEST);
//        }
//    }
//    @GetMapping("/feign/product/{id}")
//    public Product getProduct(@PathVariable("id") Long id){
//        return productRepository.findById(id).get() ;
//    }
//
    @PostMapping("/product-review")
    public ResponseEntity<?> productReview(@RequestBody ProductReviewRequest reviewRequest){

        return toSuccessResult(productService.productReview(reviewRequest), "Danh gia san pham thanh cong");

    }
}
