package com.enigmacamp.procurement.service;

import com.enigmacamp.procurement.entity.Procurement;
import com.enigmacamp.procurement.model.request.ProcurementRequest;

import java.util.List;

public interface ProcurementService {

    Procurement createProcurement(List<ProcurementRequest> procurementRequest);
}
