package com.enigmacamp.procurement.service.impl;

import com.enigmacamp.procurement.entity.Procurement;
import com.enigmacamp.procurement.entity.ProcurementDetail;
import com.enigmacamp.procurement.entity.VendorMaterial;
import com.enigmacamp.procurement.model.request.ProcurementRequest;
import com.enigmacamp.procurement.repository.ProcurementRepository;
import com.enigmacamp.procurement.service.ProcurementService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProcurementServiceImpl implements ProcurementService {

    private final ProcurementRepository procurementRepository;

    public ProcurementServiceImpl(ProcurementRepository procurementRepository) {
        this.procurementRepository = procurementRepository;
    }

    @Override
    public Procurement createProcurement(List<ProcurementRequest> procurementRequest) {
        Procurement procurement = new Procurement();
        procurement.setTransactionDate(new Date());

        for (ProcurementRequest request : procurementRequest) {
            VendorMaterial vendorMaterial = request.getVendorMaterial();
            ProcurementDetail detail = new ProcurementDetail();
            detail.setQty(request.getQty());
            detail.setVendorMaterial(vendorMaterial);
            procurement.addProcurementDetail(detail);
        }

        return procurementRepository.save(procurement);
    }
}
