package com.cybersoft.bookshop_authentication.services.imp;

import com.cybersoft.bookshop_authentication.entity.Users;
import com.cybersoft.bookshop_authentication.exception.DataNotFound;
import com.cybersoft.bookshop_authentication.repository.UsersRepository;
import com.cybersoft.bookshop_authentication.services.AuthenticationServices;
import com.cybersoft.bookshop_authentication.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServicesImp implements AuthenticationServices {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String signIn(String email, String password) {
        Users users = usersRepository.findByEmail(email).orElseThrow(() -> new DataNotFound("User not found with email: " + email));
        if (!passwordEncoder.matches(password, users.getPassword())) {
            throw new DataNotFound("Invalid password for user: " + email);
        }else{
            return jwtHelper.generateToken(users.getEmail());
        }
    }
}
