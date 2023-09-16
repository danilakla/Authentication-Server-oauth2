package com.example.authserver;

import com.example.authserver.authentication.configs.RsaProperties;
import com.example.authserver.authentication.security.LogoutService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({RsaProperties.class, LogoutService.class})
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

}
