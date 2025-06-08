package com.cybersoft.bookshop_product.exception;

import com.cybersoft.bookshop_product.payload.response.BaseRespone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CentralException {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseRespone> handleProductNotFound(){
        BaseRespone response = new BaseRespone();
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Product not found");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
    }
}
