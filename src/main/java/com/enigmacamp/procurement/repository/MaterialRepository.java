package com.enigmacamp.procurement.repository;

import com.enigmacamp.procurement.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, String> {
    Optional<Material> findMaterialByMaterialName(String name);
}
