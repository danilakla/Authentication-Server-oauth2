package com.example.authserver.authentication.security;

import com.example.authserver.authentication.entity.UserEntity;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JwtService  {

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        super();
        this.jwtEncoder = jwtEncoder;
    }

    public String generateAccessToken(CustomUsrDetails usrDetails, Long profileId) {
        Instant now = Instant.now();
        String scope = usrDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(2, ChronoUnit.MINUTES))
                .subject(usrDetails.getUsername())
                .claim("profile_id", profileId)
                .claim("scope", scope)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public String generateRefreshToken(CustomUsrDetails usrDetails, Long profileId) {
        Instant now = Instant.now();
        String scope = usrDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .subject(usrDetails.getUsername())
                .claim("profile_id", profileId)

                .claim("scope", scope)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String parseToken(String token) {
        try {
            SignedJWT decodedJWT = SignedJWT.parse(token);
            String subject = decodedJWT.getJWTClaimsSet().getSubject();
            return subject;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isTokenValid(String token, UserEntity userDetails) {
        final String username = parseToken(token);
        return (username.equals(userDetails.getEmail())) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        try {
            SignedJWT decodedJWT = SignedJWT.parse(token);
            Claims subject = (Claims) decodedJWT.getJWTClaimsSet().getClaims();
            return subject;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}