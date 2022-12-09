package com.enigmacamp.procurement.model.request;

import com.enigmacamp.procurement.entity.VendorMaterial;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProcurementRequest {
    private VendorMaterial vendorMaterial;
    private Double qty;
}
