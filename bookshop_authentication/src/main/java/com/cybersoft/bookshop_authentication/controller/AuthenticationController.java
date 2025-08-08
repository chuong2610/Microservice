package com.cybersoft.bookshop_authentication.controller;

import com.cybersoft.bookshop_authentication.payload.request.AuthenticationRequest;
import com.cybersoft.bookshop_authentication.payload.request.SignUpRequest;
import com.cybersoft.bookshop_authentication.payload.response.BaseResponse;
import com.cybersoft.bookshop_authentication.services.AuthenticationServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServices authenticationServices;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        // Logic for user login
        String token = authenticationServices.signIn(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setMessage("Login successful");
        response.setData(token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        // Logic for user registration
        authenticationServices.signUp(signUpRequest);
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setMessage("Registration successful");

        return ResponseEntity.ok(response);
    }
}
