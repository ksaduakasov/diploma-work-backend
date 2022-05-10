package com.example.diplomawork.controller;

import com.example.api.AuthApi;
import com.example.diplomawork.service.AuthService;
import com.example.diplomawork.service.RefreshTokenService;
import com.example.models.*;
import org.springframework.http.ResponseEntity;


import static org.springframework.http.HttpStatus.OK;

public class AuthController implements AuthApi {
    private AuthService authService;
    private RefreshTokenService refreshTokenService;

    @Override
    public ResponseEntity<AuthenticationResponse> login(LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @Override
    public ResponseEntity<LogoutResponse> logout(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getToken());
        return ResponseEntity.status(OK).body(new LogoutResponse().message("Refresh Token Deleted Successfully!!"));
    }

    @Override
    public ResponseEntity<AuthenticationResponse> refresh(RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    @Override
    public ResponseEntity<Void> signup(RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity(OK);
    }

    @Override
    public ResponseEntity<TokenVerifyResponse> verify(String token) {
        authService.verifyAccount(token);
        return ResponseEntity.status(OK).body(new TokenVerifyResponse().message("Account activated successfully"));
    }
}
