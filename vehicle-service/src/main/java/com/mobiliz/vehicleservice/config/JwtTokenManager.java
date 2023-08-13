package com.mobiliz.vehicleservice.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {


    @Value("${jwt.secret}")
    private String secret;

private static final long timeToExpire = 1*24*60*60*1000; // 1 gün

    public Optional<String> createToken(Long id, String role, Long companyId) {
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withAudience()
                    .withClaim("id", id)
                    .withIssuer("mobiliz.com")
                    .withClaim("role",role)
                    .withClaim("companyid", companyId)
                    .withExpiresAt(new Date(System.currentTimeMillis() + timeToExpire))
                    .withIssuedAt(new Date())
                    .sign(algorithm);
            return Optional.of(token);
        }catch (Exception e){
            return Optional.empty();
        }
    }
    public boolean validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier =  JWT.require(algorithm)
                    .withIssuer("mobiliz.com")
                    .build();
            DecodedJWT decode = jwtVerifier.verify(token);
            if (decode==null) {
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Optional<Long> getUserId(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier =  JWT.require(algorithm)
                    .withIssuer("mobiliz.com")
                    .build();
            DecodedJWT decode = jwtVerifier.verify(token);
            if (decode==null) return Optional.empty();
            Long id = decode.getClaim("id").asLong();
            return Optional.of(id);
        }catch (Exception e){
            return Optional.empty();
        }
    }
    public Optional<Long> getCompanyId(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier =  JWT.require(algorithm)
                    .withIssuer("mobiliz.com")
                    .build();
            DecodedJWT decode = jwtVerifier.verify(token);
            if (decode==null) return Optional.empty();
            Long id = decode.getClaim("companyid").asLong();
            return Optional.of(id);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public Optional<String> getUserRole(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer("mobiliz.com")
                    .build();
            DecodedJWT decode = jwtVerifier.verify(token);
            if (decode == null) return Optional.empty();
            String role = decode.getClaim("role").asString();
            return Optional.of(role);
        } catch (Exception e) {
            return Optional.empty();
        }
    }



}

