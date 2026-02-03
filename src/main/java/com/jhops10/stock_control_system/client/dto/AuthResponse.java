package com.jhops10.stock_control_system.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponse(@JsonProperty("access_token") String accessToken,
                           @JsonProperty("expires_in") Integer expiresIn) {
}
