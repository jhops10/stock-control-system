package com.jhops10.stock_control_system.service;

import com.jhops10.stock_control_system.domain.CsvStockItem;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ReportService {

    public List<CsvStockItem> readStockReport(String path) throws IOException {

        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {

            var builder = new CsvToBeanBuilder<CsvStockItem>(reader)
                    .withType(CsvStockItem.class)
                    .build();

            return builder.parse();
        }
    }


}
