package com.lgda.backend.OneOrder;


import com.lgda.backend.OrderProduct.OrderProduct;
import com.lgda.backend.address.Address;
import com.lgda.backend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

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
    private OrderStatus orderStatus;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "billing_address_id")
    private Address billingAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oneOrder")
    private List<OrderProduct> orderProducts;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdAt;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;
}
