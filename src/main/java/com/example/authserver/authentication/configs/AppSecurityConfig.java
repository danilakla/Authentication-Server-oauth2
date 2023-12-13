package com.example.authserver.authentication.configs;

import com.example.authserver.authentication.repository.UserRepository;
import com.example.authserver.authentication.security.CustomUsrDetailsService;
import com.example.authserver.authentication.security.JwtService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor

public class AppSecurityConfig {

    private final RsaProperties rsaKeys;
    private final LogoutHandler logoutHandler;
    private final UserRepository userRepo;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService customUserDetailsService() {
        return new CustomUsrDetailsService(userRepo);
    }

    @Bean
    public AuthenticationManager authManager() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    @Bean
    JwtService jwtService() {
        return new JwtService(jwtEncoder());
    }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("*"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
            configuration.setAllowedHeaders(Arrays.asList("*"));

            // Set other CORS configurations if necessary, e.g.:
            // configuration.setExposedHeaders(Arrays.asList("header1", "header2"));
            // configuration.setAllowCredentials(true);

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);

            return source;
        }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                        .requestMatchers("/test12").permitAll()
                        .requestMatchers("/image").permitAll()
                        .requestMatchers("/upload").permitAll()


                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/user").permitAll()
                        .requestMatchers("/user").authenticated()
                        .requestMatchers("/getQrCodes").authenticated()

                        .requestMatchers("/createQRCode").authenticated()
                        .requestMatchers("/deleteQRCode").authenticated()
                        .requestMatchers("/addContentToQR").authenticated()
                                .requestMatchers("/updateQrCode").authenticated()
                                .requestMatchers("/getRole").authenticated()

                        .requestMatchers("/getUsersPublicPosts").authenticated()

                        .requestMatchers("/getPostById").authenticated()

                        .requestMatchers("/createPost").authenticated()
                        .requestMatchers("/putReaction").authenticated()
                        .requestMatchers("/deletePost").authenticated()
                        .requestMatchers("/getPosts").authenticated()
                        .requestMatchers("/updateDescriptionPost").authenticated()
                        .requestMatchers("/updateAccessPost").authenticated()



                        .requestMatchers("/createPost").authenticated()
                        .requestMatchers("/putReaction").authenticated()
                        .requestMatchers("/deletePost").authenticated()
                        .requestMatchers("/deleteContent").authenticated()
                        .requestMatchers("/getContents").authenticated()
                        .requestMatchers("/updateDescriptionPost").authenticated()
                        .requestMatchers("/updateAccessPost").authenticated()


                        .requestMatchers("/test").authenticated()
                        .requestMatchers("/testUser").hasAnyAuthority("SCOPE_user","SCOPE_admin" )
                        .requestMatchers("/testAdmin").hasAuthority("SCOPE_admin")
                        .requestMatchers("/authenticate").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/token/refresh").permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2->oauth2.jwt(Customizer.withDefaults()))
                .logout(logout->
                        logout.logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()))
                .build();
    }




}