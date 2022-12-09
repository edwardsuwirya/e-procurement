package com.enigmacamp.procurement.service;

import com.enigmacamp.procurement.entity.Vendor;
import com.enigmacamp.procurement.model.request.NewVendorRequest;
import org.springframework.data.domain.Page;

public interface VendorService {
    Page<Vendor> getListVendor(Integer page, Integer size, String direction, String sortBy);

    Vendor registerNewVendor(NewVendorRequest vendorRequest);

    Vendor updateVendorMaterialPrice(String vendorId, String materialId, Double newPrice);

}
