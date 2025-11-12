package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Trabajador;
import com.bcss.omiapp.dto.request.TrabajadorAuthRequest;
import com.bcss.omiapp.dto.request.TrabajadorRegisterRequest;
import com.bcss.omiapp.dto.response.TokenResponse;

public interface AuthService {
    TokenResponse register(TrabajadorRegisterRequest trabajadorRegisterRequest);
    TokenResponse login(TrabajadorAuthRequest trabajadorAuthRequest);
    void saveUserToken(Trabajador trabajador, String token);
    void removeAllUserTokens(Trabajador trabajador);
    TokenResponse refreshToken(String authentication);
}
