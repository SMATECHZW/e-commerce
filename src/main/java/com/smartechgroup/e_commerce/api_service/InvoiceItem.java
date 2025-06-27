package com.smartechgroup.e_commerce.api_service;

import lombok.Data;

@Data
public class InvoiceItem {
    private String product;
    private double amount;
}
