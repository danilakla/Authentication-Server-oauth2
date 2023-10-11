package com.example.authserver;

import com.example.authserver.authentication.configs.RsaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({RsaProperties.class})
public class AuthServerApplication {
//TODO USER CAN ADD MORE THEN ONE REACTION CHECK IT
	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

}
