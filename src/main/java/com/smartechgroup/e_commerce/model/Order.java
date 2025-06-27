package com.smartechgroup.e_commerce.model;

import com.smartechgroup.e_commerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
/*@ManyToOne
    private User user;*/

    private Long userId;
    private String paymentMethod;
    private String paymentStatus;
    private double subtotal;
    private double tax;
    private double shipping;
    private double total;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shipping_address_id")
    private ShippingAddress shippingAddress;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> items = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    private Boolean customerNotified =Boolean.FALSE;


}
