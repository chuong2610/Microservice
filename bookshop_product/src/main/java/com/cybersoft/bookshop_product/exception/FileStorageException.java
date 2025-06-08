package com.cybersoft.bookshop_product.exception;

public class FileStorageException extends RuntimeException {
    private String message;
    public FileStorageException(String message) {
        super(message);
        this.message = message;
    }

//    public FileStorageException(String message, Throwable cause) {
//        super(message, cause);
//    }
}
