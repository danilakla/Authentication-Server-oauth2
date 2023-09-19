package com.example.authserver.authentication.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tokens")

@NamedStoredProcedureQuery(name = "token.addToken",procedureName = "sp_insert_token",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_userid",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_token",type = String.class),

        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_is_add",type = Integer.class),
})
@NamedStoredProcedureQuery(name = "token.revokeToken",procedureName = "sp_revoke_user_token",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id",type = Long.class),

        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_is_add",type = Integer.class),
})
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
