package com.cybersoft.bookshop_consumer_email_service.service;

import com.cybersoft.bookshop_consumer_email_service.dto.UserDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {
    @Autowired
    private JavaMailSender mailSender;
    @KafkaListener(topics = "email-topic", groupId = "bookshop-consumer-email-service")
    public void sendEmail(String userDTO) {
        Gson gson = new Gson();
        UserDTO user = gson.fromJson(userDTO, UserDTO.class);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Welcome to Bookshop");
        message.setText("Welcome to Bookshop. Your account has been created successfully. " +
                "Please use the following credentials to log in:\n" +
                "Email: " + user.getEmail() + "\n" +
                "Password: " + user.getPassword());
        try {
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
