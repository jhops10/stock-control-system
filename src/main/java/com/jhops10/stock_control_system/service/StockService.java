package com.jhops10.stock_control_system.service;

import com.jhops10.stock_control_system.domain.CsvStockItem;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StockService {

    private final ReportService reportService;
    private final PurchaseSectorService purchaseSectorService;

    public StockService(ReportService reportService, PurchaseSectorService purchaseSectorService) {
        this.reportService = reportService;
        this.purchaseSectorService = purchaseSectorService;
    }


    public void start(String path) {
        //1. Ler o arquivo csv

        try {
            var items = reportService.readStockReport(path);

            items.forEach(item -> {
                if (item.getQuantity() < item.getReorderThreshold()) {

                    //1. Calcular a quantidade a ser recomprada
                    var reorderQuantity = calculateReorderQuantity(item);

                    //2. Para cada item do csv, chamar a api do setor de compras
                    purchaseSectorService.sendPurchaseRequest(item, reorderQuantity);
                    //3. Salvar no mongodb os itens que foram recomprados
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Integer calculateReorderQuantity(CsvStockItem item) {
        return item.getReorderThreshold() + ((int)Math.ceil(item.getReorderThreshold() * 0.2));
    }
}
