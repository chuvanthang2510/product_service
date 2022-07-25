package com.clothes.controller;

import com.clothes.domain.dto.FilterDTO;
import com.clothes.domain.dto.ProductDTO;
import com.clothes.domain.dto.ResponseData;
import com.clothes.domain.mapper.DTOMapper;
import com.clothes.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.clothes.domain.enums.ResponseStatus.ERROR;
import static com.clothes.domain.enums.ResponseStatus.SUCCESS;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping(value = "/api/product")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DTOMapper dtoMapper;

    @PostMapping
    public ResponseEntity<ResponseData> createProduct(@Validated ProductDTO productDTO) {
        try {
            return new ResponseEntity<>(ResponseData.builder()
                    .status(SUCCESS.toString())
                    .message("Create product successful")
                    .data(dtoMapper.toProductDTO(productService.create(productDTO))).build(), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseData.builder()
                    .status(ERROR.toString())
                    .message(e.getMessage()).build(), BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ResponseData> updateProduct(@PathVariable(name = "id") Long productId,
                                                      @Validated ProductDTO productDTO) {
        try {
            return new ResponseEntity<>(ResponseData.builder()
                    .status(SUCCESS.toString())
                    .message("Update product successful")
                    .data(dtoMapper.toProductDTO(productService.update(productId, productDTO))).build(), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseData.builder()
                    .status(ERROR.toString())
                    .message(e.getMessage()).build(), BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseData> deleteProduct(@PathVariable("id") Long productId) {
        try {
            productService.delete(productId);
            return new ResponseEntity<>(ResponseData.builder()
                    .status(SUCCESS.toString())
                    .message("Delete product successful").build(), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseData.builder()
                    .status(ERROR.toString())
                    .message(e.getMessage()).build(), BAD_REQUEST);
        }
    }

    @GetMapping(value = "/search")
    public ResponseEntity<ResponseData> searchProduct(@RequestBody FilterDTO filterDTO,
                                                      @RequestParam("page") Integer page,
                                                      @RequestParam("size") Integer size) {
        try {
            return new ResponseEntity<>(ResponseData.builder()
                    .status(SUCCESS.toString())
                    .message("Search product successful")
                    .data(dtoMapper.toProductDTO(productService.searchProduct(page, size, filterDTO))).build(), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseData.builder()
                    .status(ERROR.toString())
                    .message(e.getMessage()).build(), BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseData> getAllProduct(@RequestParam("page") Integer page,
                                                      @RequestParam("size") Integer size) {
        try {
            return new ResponseEntity<>(ResponseData.builder()
                    .status(SUCCESS.toString())
                    .message("Get all product successful")
                    .data(dtoMapper.toProductDTO(productService.getAllProduct(page, size))).build(), OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseData.builder()
                    .status(ERROR.toString())
                    .message(e.getMessage()).build(), BAD_REQUEST);
        }
    }
}
