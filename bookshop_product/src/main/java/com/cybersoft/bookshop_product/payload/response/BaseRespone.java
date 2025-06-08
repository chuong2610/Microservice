package com.cybersoft.bookshop_product.payload.response;

import lombok.Data;

@Data
public class BaseRespone {
    private int code;
    private String message;
    private Object data;
}
