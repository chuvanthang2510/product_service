package com.clothes.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilterDTO implements Serializable {

    private String name;
    private Double fromPrice;
    private Double toPrice;
    private List<Long> categoryIds;
    private List<Long> brandIds;
    private List<Long> sizeIds;
    private List<Long> colorIds;
}

