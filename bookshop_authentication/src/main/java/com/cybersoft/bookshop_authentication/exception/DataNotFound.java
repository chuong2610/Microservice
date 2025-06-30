package com.cybersoft.bookshop_authentication.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataNotFound extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;

    public DataNotFound(String message) {
        super(message);
        this.message = message;
    }

    public DataNotFound(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
