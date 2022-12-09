package com.enigmacamp.procurement.repository;

import com.enigmacamp.procurement.entity.*;
import com.enigmacamp.procurement.model.response.Report;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProcurementTest {
    @Autowired
    ProcurementRepository procurementRepository;

    @Autowired
    VendorMaterialRepository vendorMaterialRepository;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    MaterialRepository materialRepository;

    @Test
//    @Sql({"classpath:material-type.sql", "classpath:material.sql", "classpath:vendor.sql", "classpath:vendor-material.sql"})
    @Commit
    void itShould_CreateProcurementTransaction() {
        Optional<Vendor> oVendor = vendorRepository.findVendorByVendorName("PT.XYZ");
        Vendor vendor = oVendor.get();

        Optional<Material> oMaterial = materialRepository.findMaterialByMaterialName("Sapu");
        Material material = oMaterial.get();

        Optional<VendorMaterial> oVendorMaterial = vendorMaterialRepository.findLatestVendorMaterial(vendor.getVendorId(), material.getMaterialId());
        VendorMaterial vendorMaterial1 = oVendorMaterial.get();

        ProcurementDetail detail1 = new ProcurementDetail();
        detail1.setQty(1.0);
        detail1.setVendorMaterial(vendorMaterial1);

        Procurement procurement = new Procurement();
        procurement.setTransactionDate(new Date());
        procurement.addProcurementDetail(detail1);

        procurementRepository.save(procurement);
    }

    @Test
    void itShould_GenerateDailyReport() {
        List<Procurement> procurementList = procurementRepository.findByTransactionDateBetween(new Date(), new Date());
        System.out.println("======Daily Report======");
        for (Procurement p : procurementList) {
            System.out.println(p.getTransactionId());
            Set<ProcurementDetail> set = p.getProcurementDetailSet();
            System.out.println("======Daily Report Detail======");
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

            reportItems.forEach(s -> System.out.println(s));
        }
    }
}