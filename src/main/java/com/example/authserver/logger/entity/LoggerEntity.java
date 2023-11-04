package com.example.authserver.logger.entity;

import com.example.authserver.domain.entity.ProfileEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "logger")
@NamedStoredProcedureQuery(name = "logger.addLog", procedureName = "ADD_LOG", parameters = {

        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_profile_id", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_max_size", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_success", type = Long.class),
})

public class LoggerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;
    @Column(name = "QRCOUNT")
    private Long qrcount;





    @Column(name = "logtime")
    private LocalDateTime logTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    public ProfileEntity profile;
}