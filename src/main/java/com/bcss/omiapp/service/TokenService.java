package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Token;
import com.bcss.omiapp.domain.Trabajador;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TokenService {
    String extractUsername(String token);
    String generateToken(Trabajador trabajador);
    String generateRefreshToken(Trabajador trabajador);
    String buildToken(Trabajador trabajador, Long expiration);
    boolean isTokenValid(String token, Trabajador trabajador);
    boolean isTokenExpired(String token);
    Date extractExpiration(String token);
    SecretKey getSignInKey();
    Optional<Token> findByToken(String token);
    void saveToken(Token token);
    List<Token> findAllTokensByTrabajador(Trabajador trabajador);
    void deleteTokens(List<Token> tokens);
}
