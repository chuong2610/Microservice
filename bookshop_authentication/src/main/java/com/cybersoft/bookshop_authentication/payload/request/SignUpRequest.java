package com.cybersoft.bookshop_authentication.payload.request;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String fullName;
    private String phoneNumber;
    private String address;

}
