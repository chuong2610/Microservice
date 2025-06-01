package com.cybersoft.bookshop_authentication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Users {

    @Id
    private String id;

    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
