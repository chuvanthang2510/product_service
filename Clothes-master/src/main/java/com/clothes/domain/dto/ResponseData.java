package com.clothes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseData implements Serializable {
    private String status;
    private String message;
    private Object data;
}
