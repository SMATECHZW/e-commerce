package com.smartechgroup.e_commerce.model;

import com.smartechgroup.e_commerce.request.PaymentItem;
import com.smartechgroup.e_commerce.request.ShippingAddressRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_details")
public class Payment extends BaseEntity {
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shipping_address_id")
    private ShippingAddress shippingAddress;
    private String paymentMethod;
    private double subtotal;
    private double tax;
    private double shipping;
    private double total;
    private String ecocashNumber;
    private String paymentReference;
    private String paymentStatus;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Order order;
}
