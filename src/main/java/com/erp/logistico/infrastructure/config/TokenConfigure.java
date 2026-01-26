package com.erp.logistico.infrastructure.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenConfigure {

    public Map<String, Claim> validaTokenAuth(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("colocarChaveSecreta");
            return JWT.require(algorithm)
                    .withIssuer("Portaria")
                    .build()
                    .verify(token).getClaims();
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Sessão expirada");
            // Invalid signature/claims
        }
    }
}
