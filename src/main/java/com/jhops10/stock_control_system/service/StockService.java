package com.jhops10.stock_control_system.service;

import com.jhops10.stock_control_system.domain.CsvStockItem;
import com.jhops10.stock_control_system.entity.PurchaseRequestEntity;
import com.jhops10.stock_control_system.repository.PurchaseRequestRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class StockService {

    private final ReportService reportService;
    private final PurchaseSectorService purchaseSectorService;
    private final PurchaseRequestRepository purchaseRequestRepository;

    public StockService(ReportService reportService, PurchaseSectorService purchaseSectorService, PurchaseRequestRepository purchaseRequestRepository) {
        this.reportService = reportService;
        this.purchaseSectorService = purchaseSectorService;
        this.purchaseRequestRepository = purchaseRequestRepository;
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
                    var purchasedWithSuccess = purchaseSectorService.sendPurchaseRequest(item, reorderQuantity);

                    //3. Salvar no mongodb os itens que foram recomprados
                    persist(item, reorderQuantity,purchasedWithSuccess);

                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void persist(CsvStockItem item, Integer reorderQuantity, boolean purchasedWithSuccess) {
        var entity = new PurchaseRequestEntity();
        entity.setItemId(item.getItemId());
        entity.setItemName(item.getItemName());
        entity.setQuantityOnStock(item.getQuantity());
        entity.setReorderThreshold(item.getReorderThreshold());
        entity.setSupplierName(item.getSupplierName());
        entity.setSupplierEmail(item.getSupplierEmail());
        entity.setLastStockUpdateTime(LocalDateTime.parse(item.getLastStockUpdateTime()));

        entity.setPurchaseDateTime(LocalDateTime.now());
        entity.setPurchaseQuantity(reorderQuantity);
        entity.setPurchasedWithSuccess(purchasedWithSuccess);

        purchaseRequestRepository.save(entity);
    }

    private Integer calculateReorderQuantity(CsvStockItem item) {
        return item.getReorderThreshold() + ((int)Math.ceil(item.getReorderThreshold() * 0.2));
    }
}
