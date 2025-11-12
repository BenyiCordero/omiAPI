package com.bcss.omiapp.service;

import com.bcss.omiapp.auth.TokenType;
import com.bcss.omiapp.domain.Persona;
import com.bcss.omiapp.domain.Token;
import com.bcss.omiapp.domain.Trabajador;
import com.bcss.omiapp.dto.request.TrabajadorAuthRequest;
import com.bcss.omiapp.dto.request.TrabajadorRegisterRequest;
import com.bcss.omiapp.dto.response.TokenResponse;
import com.bcss.omiapp.exception.CredencialesInvalidasException;
import com.bcss.omiapp.exception.TokenInvalidoException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final TrabajadorService trabajadorService;
    private final PersonaService personaService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(TokenService tokenService, TrabajadorService trabajadorService, PersonaService personaService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.trabajadorService = trabajadorService;
        this.personaService = personaService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public TokenResponse register(TrabajadorRegisterRequest trabajadorRegisterRequest) {
        Persona persona = Persona
                .builder()
                .nombre(trabajadorRegisterRequest.nombre())
                .primerApellido(trabajadorRegisterRequest.primerApellido())
                .segundoApellido(trabajadorRegisterRequest.segundoApellido())
                .numeroTelefono(trabajadorRegisterRequest.numeroTelefono())
                .rol(trabajadorRegisterRequest.rol())
                .build();
        Persona personaSaved = personaService.save(persona);
        Trabajador trabajador = Trabajador
                .builder()
                .email(trabajadorRegisterRequest.email())
                .password(passwordEncoder.encode(trabajadorRegisterRequest.password()))
                .horasSemana(trabajadorRegisterRequest.horasSemana())
                .salario(trabajadorRegisterRequest.salario())
                .persona(personaSaved)
                .build();
        trabajadorService.save(trabajador);
        String token = tokenService.generateToken(trabajador);
        String refreshToken = tokenService.generateRefreshToken(trabajador);

        saveUserToken(trabajador, token);
        return new TokenResponse(token, refreshToken);
    }

    @Transactional
    public TokenResponse login(TrabajadorAuthRequest trabajadorAuthRequest) {
        try{
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                trabajadorAuthRequest.email(),
                                trabajadorAuthRequest.password()
                            )
                    );
        } catch (CredencialesInvalidasException ex){
            throw new CredencialesInvalidasException();
        }

        Optional<Trabajador> trabajador = trabajadorService.findByEmail(trabajadorAuthRequest.email());
        String accessToken = tokenService.generateToken(trabajador.get());
        String refreshToken = tokenService.generateRefreshToken(trabajador.get());
        removeAllUserTokens(trabajador.get());
        saveUserToken(trabajador.get(), accessToken);
        return new TokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public void saveUserToken(Trabajador trabajador, String token) {
        Token tokenJwt = Token
                .builder()
                .token(token)
                .isExpired(false)
                .isRevoked(false)
                .type(TokenType.BEARER)
                .trabajador(trabajador)
                .build();
        tokenService.saveToken(tokenJwt);
    }

    @Transactional
    public void removeAllUserTokens(Trabajador trabajador) {
        List<Token> tokens = tokenService.findAllTokensByTrabajador(trabajador);
        if(!tokens.isEmpty()){
            tokenService.deleteTokens(tokens);
        }
    }

    @Transactional
    public TokenResponse refreshToken(String authentication) {
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new TokenInvalidoException();
        }
        String refreshToken = authentication.substring(7);
        String email = tokenService.extractUsername(refreshToken);
        if (email == null) {
            return null;
        }

        Trabajador trabajador = trabajadorService.findByEmail(email).orElseThrow();
        boolean isTokenValid = tokenService.isTokenValid(refreshToken, trabajador);
        if (!isTokenValid) {
            return null;
        }

        String accessToken = tokenService.generateRefreshToken(trabajador);
        removeAllUserTokens(trabajador);
        saveUserToken(trabajador, accessToken);

        return new TokenResponse(accessToken, refreshToken);
    }
}
