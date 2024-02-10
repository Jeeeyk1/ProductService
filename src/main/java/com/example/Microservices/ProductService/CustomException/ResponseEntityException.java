package com.example.Microservices.ProductService.CustomException;


import com.example.Microservices.ProductService.Model.ErrorMessageModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseEntityException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductCustomException.class)
    public ResponseEntity<ErrorMessageModel> handleProductException(ProductCustomException exception) {
        String errorBasis = exception.getErrorCode();

        switch (errorBasis) {
            case "NOT_FOUND":
                return new ResponseEntity<>(new ErrorMessageModel().
                        builder().
                        errorMessage(exception.getMessage()).
                        errorCode(exception.getErrorCode()).
                        build(), HttpStatus.NOT_FOUND);
            case "INSUFICIENT_QUANTITY":
                return new ResponseEntity<>(new ErrorMessageModel()
                        .builder()
                        .errorMessage(exception.getErrorCode())
                        .errorCode(exception.getErrorCode())
                        .build(), HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(new ErrorMessageModel().
                builder().
                errorMessage(exception.getMessage()).
                errorCode(exception.getErrorCode()).
                build(), HttpStatus.NOT_FOUND);

    }

}
