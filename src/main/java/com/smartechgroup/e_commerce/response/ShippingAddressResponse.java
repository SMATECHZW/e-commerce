package com.smartechgroup.e_commerce.response;

import lombok.Data;

@Data
public class ShippingAddressResponse {
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String province;
}