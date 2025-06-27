package com.smartechgroup.e_commerce.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
   private int statusCode;
   private String message;
    private Object data;
}
