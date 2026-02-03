package com.jhops10.stock_control_system.service;

import com.jhops10.stock_control_system.client.AuthClient;
import com.jhops10.stock_control_system.client.dto.AuthRequest;
import com.jhops10.stock_control_system.config.AppConfig;
import com.jhops10.stock_control_system.exception.StockControlException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private static String GRANT_TYPE = "client_credentials";
    private static String token;
    private static LocalDateTime expiresIn;

    private final AuthClient authClient;
    private final AppConfig appConfig;

    public AuthService(AuthClient authClient, AppConfig appConfig) {
        this.authClient = authClient;
        this.appConfig = appConfig;
    }

    public String getToken() {
        if (token == null) {
            generateToken();
        } else if (expiresIn.isBefore(LocalDateTime.now())) {
            generateToken();
        }

        return token;
    }

    private void generateToken() {

        var request = new AuthRequest(
                GRANT_TYPE,
                appConfig.getClientId(),
                appConfig.getClientSecret()
        );

        var response = authClient.authenticate(request);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new StockControlException("Cannot generate token, " +
                    "token: " + response.getStatusCode() +
                    "body: " + response.getBody());
        }


        token = response.getBody().accessToken();
        expiresIn = LocalDateTime.now().plusSeconds(response.getBody().expiresIn());
    }
}
