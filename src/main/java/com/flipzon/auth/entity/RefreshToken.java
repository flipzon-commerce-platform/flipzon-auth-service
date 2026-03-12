package com.flipzon.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String token;

    private Instant expiryDate;

    @OneToOne
    private User user;
}
