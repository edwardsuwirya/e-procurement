package com.enigmacamp.procurement.repository;

import com.enigmacamp.procurement.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, String> {
    Optional<Vendor> findVendorByVendorName(String name);
}
