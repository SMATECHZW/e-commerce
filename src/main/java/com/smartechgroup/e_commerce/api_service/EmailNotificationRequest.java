package com.smartechgroup.e_commerce.api_service;

import lombok.Data;

@Data
public class EmailNotificationRequest {
    private String to;
    private String subject;
    private String message;
}
