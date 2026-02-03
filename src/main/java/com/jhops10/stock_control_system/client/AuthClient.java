package com.jhops10.stock_control_system.client;

import com.jhops10.stock_control_system.client.dto.AuthRequest;
import com.jhops10.stock_control_system.client.dto.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AuthClient", url = "${api.auth-url}")
public interface AuthClient {

    @PostMapping("/api/token")
    ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request);
}
