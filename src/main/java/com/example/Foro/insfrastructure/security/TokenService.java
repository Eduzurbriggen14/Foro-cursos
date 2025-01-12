package com.example.Foro.insfrastructure.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Foro.domain.models.usuario.Usuario;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String API_SECRET;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(API_SECRET);
            return JWT.create()
                    .withIssuer("Foro")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getIdUsuario())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al crear el token JWT", exception);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new ValidationException("El token no puede ser nulo");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(API_SECRET);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("Foro")
                    .build()
                    .verify(token);

            String subject = decodedJWT.getSubject();
            if (subject == null) {
                throw new RuntimeException("No se pudo encontrar el subject del token");
            }

            return subject;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error al verificar el token JWT: " + exception.getMessage(), exception);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);
    }
}
