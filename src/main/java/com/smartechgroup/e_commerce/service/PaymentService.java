package com.smartechgroup.e_commerce.service;

import com.smartechgroup.e_commerce.model.Payment;
import com.smartechgroup.e_commerce.request.PaymentRequest;
import com.smartechgroup.e_commerce.response.Response;

public interface PaymentService {
    Response processPayment(PaymentRequest request) throws Exception;
}
