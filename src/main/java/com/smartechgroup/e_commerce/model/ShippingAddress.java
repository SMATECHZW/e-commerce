package com.smartechgroup.e_commerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipping_details")
public class ShippingAddress extends BaseEntity{
    @OneToOne(mappedBy = "shippingAddress")
    private Payment payment;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String province;
    @OneToOne(mappedBy = "shippingAddress")
    private Order order;
}
