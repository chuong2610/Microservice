package com.cybersoft.bookshop_authentication.services.imp;

import com.cybersoft.bookshop_authentication.dto.UserDTO;
import com.cybersoft.bookshop_authentication.entity.Users;
import com.cybersoft.bookshop_authentication.enumable.StatusUser;
import com.cybersoft.bookshop_authentication.exception.DataNotFound;
import com.cybersoft.bookshop_authentication.payload.request.SignUpRequest;
import com.cybersoft.bookshop_authentication.repository.UsersRepository;
import com.cybersoft.bookshop_authentication.services.AuthenticationServices;
import com.cybersoft.bookshop_authentication.utils.CommonHelper;
import com.cybersoft.bookshop_authentication.utils.JwtHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class AuthenticationServicesImp implements AuthenticationServices {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private CommonHelper commonHelper;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String signIn(String email, String password) {
        Users users = usersRepository.findByEmail(email).orElseThrow(() -> new DataNotFound("User not found with email: " + email));
        if(users.getStatus() == StatusUser.BLOCK) {
            unblockUser(users);
        }
        if (!passwordEncoder.matches(password, users.getPassword())) {
            blockUser(users);
            throw new DataNotFound("Invalid password for user: " + email);
        }else{
            return jwtHelper.generateToken(users.getEmail());
        }
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        Optional<Users> exitingUser = usersRepository.findByEmail(signUpRequest.getEmail());
        if (exitingUser.isPresent()) {
            throw new DataNotFound("Email already exists: " + signUpRequest.getEmail());
        }
        String randomPassword = CommonHelper.generateRandomPassword(6);
        Users newUser = new Users();
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(randomPassword));
        newUser.setStatus(StatusUser.CHANGE_PASSWORD);
        usersRepository.save(newUser);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(newUser.getEmail());
        userDTO.setPassword(randomPassword);
        ObjectMapper mapper = new ObjectMapper();
        try{
            String userJson = objectMapper.writeValueAsString(userDTO);
            kafkaTemplate.send("email",userJson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void blockUser(Users user) {
        int count = user.getAttemp()+1;
        System.out.println("Attempt count: " + count);
        user.setAttemp(count);
        if(count>=3){
            user.setStatus(StatusUser.BLOCK);
            redisTemplate.opsForValue().set(user.getEmail(), user.getId(), Duration.ofMinutes(15));
        }
        usersRepository.save(user);
    }
    private void unblockUser(Users user) {
        Optional<String> data = Optional.ofNullable(redisTemplate.opsForValue().get(user.getEmail()));
        if(data.isEmpty()) {
            user.setStatus(StatusUser.ACTIVE);
            user.setAttemp(0);
            usersRepository.save(user);
        } else {
            throw new DataNotFound("user is Block, please wait 15 minutes to try again");
        }


    }
}
