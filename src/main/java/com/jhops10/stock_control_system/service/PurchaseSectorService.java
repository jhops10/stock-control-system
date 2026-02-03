package com.jhops10.stock_control_system.service;

import com.jhops10.stock_control_system.client.PurchaseSectorClient;
import com.jhops10.stock_control_system.client.dto.PurchaseRequest;
import com.jhops10.stock_control_system.domain.CsvStockItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PurchaseSectorService {

    private static final Logger  logger = LoggerFactory.getLogger(PurchaseSectorService.class);

    private final AuthService authService;
    private final PurchaseSectorClient purchaseSectorClient;

    public PurchaseSectorService(AuthService authService, PurchaseSectorClient purchaseSectorClient) {
        this.authService = authService;
        this.purchaseSectorClient = purchaseSectorClient;
    }

    public boolean sendPurchaseRequest(CsvStockItem item, Integer purchaseQuantity) {

        //1. Autenticação na Api para recuperar token
        var token = authService.getToken();

        //2. Enviar solicitação de compra
        var request = new PurchaseRequest(
                item.getItemId(),
                item.getItemName(),
                item.getSupplierName(),
                item.getSupplierEmail(),
                purchaseQuantity
        );

        var response = purchaseSectorClient.sendPurchaseRequest(token, request);

        //3. Validar resposta com sucesso
        if (response.getStatusCode().value() != HttpStatus.ACCEPTED.value()) {
            logger.error("Error while sending purchase request, status: {}, response: {}", response.getStatusCode(), response.getBody());

            return false;
        }

        return true;
    }
}
