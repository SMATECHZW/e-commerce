package com.smartechgroup.e_commerce.api_service;

import lombok.Data;

@Data
public class PaymentResponse {
    private Long paymentId;
    private Long orderId;
    private boolean success;
}
