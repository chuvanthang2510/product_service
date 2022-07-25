package com.clothes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;
    private String shortDescription;
    private String longDescription;

    @NotNull
    private Double importPrice;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long brandId;

    @NotNull
    private Long[] sizeIds;

    @NotNull
    private Long[] colorIds;
    private Boolean status;
    private Date createdDate;
    private Date modifiedDate;
    private MultipartFile uploadAvatar;
    private List<MultipartFile> uploadImages;
}
