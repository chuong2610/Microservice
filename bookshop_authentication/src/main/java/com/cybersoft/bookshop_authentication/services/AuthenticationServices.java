package com.cybersoft.bookshop_authentication.services;

import com.cybersoft.bookshop_authentication.payload.request.SignUpRequest;

public interface AuthenticationServices {
    String signIn(String email, String password);
    void signUp(SignUpRequest signUpRequest);
}
