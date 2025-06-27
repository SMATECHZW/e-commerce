package com.smartechgroup.e_commerce.request;

import lombok.Data;

@Data
public class ShippingAddressRequest {
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String province;
}
