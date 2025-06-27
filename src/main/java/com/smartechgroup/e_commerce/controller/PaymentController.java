package com.smartechgroup.e_commerce.controller;

import com.smartechgroup.e_commerce.request.PaymentRequest;
import com.smartechgroup.e_commerce.response.Response;
import com.smartechgroup.e_commerce.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process-payment")
    public Response save(@RequestBody PaymentRequest request) throws Exception {
        return paymentService.processPayment(request);
    }
}
