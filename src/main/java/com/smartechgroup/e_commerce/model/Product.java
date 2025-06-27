package com.smartechgroup.e_commerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {
    private String name;

    @Column(length = 2000)
    private String description;

    private Double price;

    private Double originalPrice;

    private String image; // Primary image

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> images; // Additional images

    @ManyToOne
    private Category category;

    private String brand;

    private Double rating;

    private Integer reviewCount;

    private Boolean inStock;

    private Integer stockQuantity;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_specifications", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "spec_key")
    @Column(name = "spec_value")
    private Map<String, String> specifications;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags;
}
