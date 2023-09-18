package com.example.authserver.authentication.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tokens")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Integer id;

    @Column(unique = true)
    public String token;

    @JoinColumn(name = "revoked")

    public boolean revoked;
    @JoinColumn(name = "expired ")

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserEntity user;
}
