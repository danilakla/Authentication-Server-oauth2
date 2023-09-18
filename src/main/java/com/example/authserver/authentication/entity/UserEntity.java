package com.example.authserver.authentication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "users")
@NamedStoredProcedureQuery(name = "user.insetUser",procedureName = "pr_inser_user",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email",type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password",type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_roleid",type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_is_add",type = Integer.class),
})
public class
UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;
}
