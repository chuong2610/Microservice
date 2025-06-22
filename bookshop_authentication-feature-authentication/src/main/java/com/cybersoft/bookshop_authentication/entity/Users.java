package com.cybersoft.bookshop_authentication.entity;

import com.cybersoft.bookshop_authentication.enumable.StatusUser;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private int attemp;
    @Enumerated(EnumType.STRING)
    private StatusUser status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
