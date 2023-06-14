package com.lgda.backend.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lgda.backend.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String thumbnail;
    private Boolean isDiscounted;
    private Boolean isAvailable;
    private Float price;
    private Float discountPercent;

    @ManyToOne
    @JsonIgnoreProperties("productList")
    private Category category;
}
