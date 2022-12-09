package com.enigmacamp.procurement.service.impl;

import com.enigmacamp.procurement.entity.Procurement;
import com.enigmacamp.procurement.entity.ProcurementDetail;
import com.enigmacamp.procurement.model.response.Report;
import com.enigmacamp.procurement.repository.ProcurementRepository;
import com.enigmacamp.procurement.service.ReportService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {

    private final ProcurementRepository procurementRepository;

    public ReportServiceImpl(ProcurementRepository procurementRepository) {
        this.procurementRepository = procurementRepository;
    }

    @Override
    @Transactional
    public Resource generateDailyReport(Date date) throws IOException {
        String uuid = UUID.randomUUID().toString();
        Path tempCsv = Files.createTempFile(uuid, ".csv");

        List<Procurement> procurementList = procurementRepository.findByTransactionDateBetween(date, date);
        for (Procurement p : procurementList) {
            Set<ProcurementDetail> set = p.getProcurementDetailSet();
            List<String> reportItems = new ArrayList<>();
            for (ProcurementDetail pd : set) {
                Report report = new Report(
                        pd.getVendorMaterial().getMaterial().getMaterialId(),
                        pd.getProcurement().getTransactionDate(),
                        pd.getVendorMaterial().getVendor().getVendorName(),
                        pd.getVendorMaterial().getMaterial().getMaterialName(),
                        pd.getVendorMaterial().getMaterial().getMaterialType().getMaterialTypeName(),
                        pd.getVendorMaterial().getPrice(),
                        pd.getQty(),
                        pd.getVendorMaterial().getPrice() * pd.getQty()
                );
                reportItems.add(report.toCsv());
            }
            Files.write(tempCsv, reportItems, StandardOpenOption.APPEND);
        }
        return new UrlResource(tempCsv.toUri());
    }
}
