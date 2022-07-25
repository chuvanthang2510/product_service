package com.savvycom.product_service.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_detail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail extends BaseTimeModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productDetailId;

    private String size;

    private String color;

    private Integer quantity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
}
