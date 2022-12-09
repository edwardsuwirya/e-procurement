package com.enigmacamp.procurement.repository;

import com.enigmacamp.procurement.entity.VendorMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VendorMaterialRepository extends JpaRepository<VendorMaterial, String> {
    @Query("SELECT vm from VendorMaterial vm WHERE vm.vendor.vendorId=?1 AND vm.material.materialId=?2 AND vm.isActive=true ")
    Optional<VendorMaterial> findLatestVendorMaterial(String vendorId, String materialId);
}
