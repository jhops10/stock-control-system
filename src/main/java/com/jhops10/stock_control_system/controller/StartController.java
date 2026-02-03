package com.jhops10.stock_control_system.controller;

import com.jhops10.stock_control_system.controller.dto.StartDto;
import com.jhops10.stock_control_system.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class StartController {

    private final StockService stockService;

    public StartController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/start")
    public ResponseEntity<Void> start(@RequestBody StartDto dto) {

        CompletableFuture.runAsync(() -> {
            stockService.start(dto.path());
        });


        return ResponseEntity.accepted().build();
    }
}
