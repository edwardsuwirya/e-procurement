package com.enigmacamp.procurement.service.impl;

import com.enigmacamp.procurement.entity.Material;
import com.enigmacamp.procurement.entity.Vendor;
import com.enigmacamp.procurement.entity.VendorMaterial;
import com.enigmacamp.procurement.model.request.NewVendorRequest;
import com.enigmacamp.procurement.service.VendorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VendorServiceImplTest {
    @Autowired
    VendorService vendorService;

    @Test
    void itShould_RegisterVendorWithMaterial() {
        Vendor vendor = new Vendor();
        vendor.setVendorName("PT.Karunia Sentosa");

        Material material = new Material();
        material.setMaterialId("59906814-e318-4ddf-a535-a02724ccb810");
        List<VendorMaterial> vendorMaterialList = new ArrayList<>();
        VendorMaterial vendorMaterial = new VendorMaterial();
        vendorMaterial.setPrice(10000.0);
        vendorMaterial.setMaterial(material);
        vendorMaterialList.add(vendorMaterial);
        NewVendorRequest vendorRequest = new NewVendorRequest();
        vendorRequest.setVendor(vendor);
        vendorRequest.setMaterialList(vendorMaterialList);
        vendorService.registerNewVendor(vendorRequest);
    }

    @Test
    void itShould_UpdateVendorPrice() {
        vendorService.updateVendorMaterialPrice("d1fd36b9-af89-45ef-a21f-3b80720b43d6", "59906814-e318-4ddf-a535-a02724ccb810", 10500.0);
    }
}