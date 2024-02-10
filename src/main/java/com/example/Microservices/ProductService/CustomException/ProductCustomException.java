package com.example.Microservices.ProductService.CustomException;

import com.example.Microservices.ProductService.Model.ProductResponse;
import lombok.Data;

@Data
public class ProductCustomException extends RuntimeException {

    private String errorCode;

    public ProductCustomException(String message, String errorCode){
        super(message);
        this.errorCode = errorCode;

    }

}

