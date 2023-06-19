package com.lgda.backend.OneOrder;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lgda.backend.OrderProduct.OrderProduct;
import com.lgda.backend.address.Address;
import com.lgda.backend.product.Product;
import com.lgda.backend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OneOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalCost;
    private String orderStatus;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="one_order_id", referencedColumnName = "id")
    private Set<OrderProduct> orderProductList;

    @Column(name = "created_date")
    private Date createdAt = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("orderList")
    private User user;
}
