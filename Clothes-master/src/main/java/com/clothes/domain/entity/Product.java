package com.clothes.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "long_description", columnDefinition = "TEXT")
    private String longDescription;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    private Double price;
    private Integer quantity;
    private Boolean status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image avatar;

    @Column(name = "import_price")
    private Double importPrice;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<Image> images;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_size",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "size_id")}
    )
    private List<Size> sizes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_color",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "color_id")}
    )
    private List<Color> colors;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;
}
