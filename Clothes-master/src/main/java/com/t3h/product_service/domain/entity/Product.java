package com.savvycom.product_service.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product  extends BaseTimeModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    private Integer numberOfRate;

    private BigDecimal price;

    private Double rate;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Image> images;

    private String type;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "brand_id")
    @JsonIgnore
    private Brand brand;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductDetail> productDetails;
}
