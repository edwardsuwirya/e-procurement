package com.enigmacamp.procurement.service.impl;

import com.enigmacamp.procurement.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReportServiceImplTest {

    @Autowired
    ReportService reportService;

    @Test
    void itShould_GenerateDailyReport(){
        try {
            reportService.generateDailyReport(new Date());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}