package com.smartechgroup.e_commerce.api_service;

import com.smartechgroup.e_commerce.enums.MobileMoneyMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApiPaymentRequest {
    private String invoiceNo;
    private String mobileNo;
    private String customerEmail;
    private String reference;
    private List<InvoiceItem> items = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private MobileMoneyMethod mobileMoneyMethod;
}
