package com.smartechgroup.e_commerce.api_service;

import com.smartechgroup.e_commerce.response.Response;

public interface ApiPaymentService {
    Response initiatePayment(ApiPaymentRequest request) throws Exception;
}
