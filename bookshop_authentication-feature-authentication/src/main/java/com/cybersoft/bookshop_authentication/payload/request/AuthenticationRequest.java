package com.cybersoft.bookshop_authentication.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationRequest {
    private String email;
    private String password;
}
