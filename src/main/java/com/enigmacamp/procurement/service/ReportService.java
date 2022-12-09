package com.enigmacamp.procurement.service;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Date;

public interface ReportService {
    Resource generateDailyReport(Date date) throws IOException;
}
