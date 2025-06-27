package com.smartechgroup.e_commerce.api_service;

import com.smartechgroup.e_commerce.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiPaymentServiceImpl implements ApiPaymentService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${payment.url}")
    private String paymentUrl;
    @Override
    public Response initiatePayment(ApiPaymentRequest request) throws Exception {
        Response apiResponse = new Response();
        try {
            ResponseEntity<Response> response = restTemplate.postForEntity(
                    paymentUrl,
                    request,
                    Response.class
            );
            apiResponse = response.getBody();
        } catch (RestClientException e) {
            System.err.println("Failed to process payment: " + e.getMessage());
        }
        return apiResponse;
    }
}
