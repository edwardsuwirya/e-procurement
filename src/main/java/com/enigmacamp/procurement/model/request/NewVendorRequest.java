package com.enigmacamp.procurement.model.request;

import com.enigmacamp.procurement.entity.Vendor;
import com.enigmacamp.procurement.entity.VendorMaterial;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NewVendorRequest {
    private Vendor vendor;
    private List<VendorMaterial> materialList;

}
