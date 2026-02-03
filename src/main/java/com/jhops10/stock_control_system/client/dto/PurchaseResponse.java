package com.jhops10.stock_control_system.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PurchaseResponse(@JsonProperty("message") String message) {
}
