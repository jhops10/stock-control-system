package com.jhops10.stock_control_system.service;

import com.jhops10.stock_control_system.domain.CsvStockItem;
import org.springframework.stereotype.Service;

@Service
public class PurchaseSectorService {

    private final AuthService authService;

    public PurchaseSectorService(AuthService authService) {
        this.authService = authService;
    }

    public boolean sendPurchaseRequest(CsvStockItem item, Integer purchaseQuantity) {

        //1. Autenticação na Api para recuperar token
        var token = authService.getToken();

        //2. Enviar solicitação de compra

        //3. Validar resposta com sucesso
        return false;
    }
}
