package com.jhops10.stock_control_system.client;

import com.jhops10.stock_control_system.client.dto.PurchaseRequest;
import com.jhops10.stock_control_system.client.dto.PurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "PurchaseSectorClient", url = "${api.purchase-url}")
public interface PurchaseSectorClient {

    @PostMapping("/api/purchases")
    ResponseEntity<PurchaseResponse> sendPurchaseRequest(@RequestHeader("Authorization") String token,
                                                         @RequestBody PurchaseRequest request);
}
