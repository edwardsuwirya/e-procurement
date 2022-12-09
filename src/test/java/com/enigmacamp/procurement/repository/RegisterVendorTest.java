package com.enigmacamp.procurement.repository;

import com.enigmacamp.procurement.entity.Material;
import com.enigmacamp.procurement.entity.Vendor;
import com.enigmacamp.procurement.entity.VendorMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegisterVendorTest {
    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    MaterialRepository materialRepository;

    @Test
    @Commit
    void itShould_CreateVendor() {
        Vendor vendor = new Vendor();
        vendor.setVendorName("PT.XYZ");
        vendorRepository.save(vendor);
    }

    @Test
//    @Sql({"classpath:material-type.sql", "classpath:material.sql", "classpath:vendor.sql"})
    @Commit
    void itShould_CreateVendorMaterial() {
        Optional<Vendor> oVendor = vendorRepository.findVendorByVendorName("PT.XYZ");
        Vendor vendor = oVendor.get();

        Optional<Material> oMaterial = materialRepository.findMaterialByMaterialName("Sapu");
        Material material = oMaterial.get();

        Set<VendorMaterial> materialSet = vendor.getVendorMaterialSet();
        VendorMaterial vendorMaterial = new VendorMaterial();
        vendorMaterial.setVendor(vendor);
        vendorMaterial.setMaterial(material);
        vendorMaterial.setPrice(35000.0);
        vendorMaterial.setIsActive(true);
        materialSet.add(vendorMaterial);

        vendorRepository.save(vendor);

    }

    @Test
//    @Sql({"classpath:material-type.sql", "classpath:material.sql", "classpath:vendor.sql", "classpath:vendor-material.sql"})
    @Commit
    void itShould_UpdateVendorMaterialPrice() {
        Optional<Vendor> oVendor = vendorRepository.findVendorByVendorName("PT.XYZ");
        Vendor vendor = oVendor.get();

        Optional<Material> oMaterial = materialRepository.findMaterialByMaterialName("Sapu");
        Material material = oMaterial.get();

        Set<VendorMaterial> materialSet = vendor.getVendorMaterialSet();
        List<VendorMaterial> result = materialSet
                .stream()
                .filter(vm -> vm.getMaterial().getMaterialId().equals(material.getMaterialId()) && vm.getIsActive())
                .collect(Collectors.toList());
        for (VendorMaterial vm : result) {
            vm.setIsActive(false);
        }

        VendorMaterial newVendorMaterial = new VendorMaterial();
        newVendorMaterial.setIsActive(true);
        newVendorMaterial.setPrice(36000.0);
        newVendorMaterial.setMaterial(material);
        newVendorMaterial.setVendor(vendor);
        materialSet.add(newVendorMaterial);

        vendorRepository.save(vendor);
    }

    @Test
//    @Sql({"classpath:material-type.sql", "classpath:material.sql", "classpath:vendor.sql", "classpath:vendor-material.sql"})
    void itShould_GetVendorMaterialActive() {
        Optional<Vendor> oVendor = vendorRepository.findVendorByVendorName("PT.XYZ");
        Vendor vendor = oVendor.get();

        Set<VendorMaterial> materialSet = vendor.getVendorMaterialSet();
        List<VendorMaterial> result = materialSet
                .stream()
                .filter(vm -> vm.getMaterial().getMaterialName().equals("Sapu") && vm.getIsActive())
                .collect(Collectors.toList());
        VendorMaterial vendorMaterial = result.get(0);
        System.out.println(vendorMaterial.getPrice());

    }
}