package com.enigmacamp.procurement.service.impl;

import com.enigmacamp.procurement.entity.VendorMaterial;
import com.enigmacamp.procurement.model.request.ProcurementRequest;
import com.enigmacamp.procurement.service.ProcurementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProcurementServiceImplTest {

    @Autowired
    ProcurementService procurementService;

    @Test
    void itShould_CreateProcurement() {
        VendorMaterial vendorMaterial = new VendorMaterial();
        vendorMaterial.setId("3c6400d2-5490-41d1-8130-eb9e8dee1ca0");
        List<ProcurementRequest> procurementRequest = new ArrayList<>();
        ProcurementRequest request = new ProcurementRequest();
        request.setQty(11.0);
        request.setVendorMaterial(vendorMaterial);
        procurementRequest.add(request);

        procurementService.createProcurement(procurementRequest);
    }
}