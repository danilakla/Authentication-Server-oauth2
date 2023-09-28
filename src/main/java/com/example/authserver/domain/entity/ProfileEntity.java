package com.example.authserver.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "profiles")
@NamedStoredProcedureQuery(name = "profile.insertUser",procedureName = "INSERT_PROFILE",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_about",type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_last_name",type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_name",type = String.class),

        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success",type = Integer.class),
})
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "about")
    private String about;

    @Lob
    private byte[] image;
    @Lob
    private byte[] bgimage;


}