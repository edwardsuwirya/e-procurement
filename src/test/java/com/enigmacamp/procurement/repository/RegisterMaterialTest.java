package com.enigmacamp.procurement.repository;

import com.enigmacamp.procurement.entity.Material;
import com.enigmacamp.procurement.entity.MaterialType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegisterMaterialTest {
    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    MaterialTypeRepository materialTypeRepository;

    @Test
//    @Sql(value = "classpath:material-type.sql")
    @Commit
    void itShould_CreateMaterial() {
        Optional<MaterialType> materialType = materialTypeRepository.findById("PRT");
        Material material = new Material();
        material.setMaterialName("Sapu");
        material.setMaterialType(materialType.get());
        materialRepository.save(material);

        Material material1 = new Material();
        material1.setMaterialName("Pel");
        material1.setMaterialType(materialType.get());
        materialRepository.save(material1);

//        Optional<Material> actualMaterial = materialRepository.findById(material.getMaterialId());
//        assertEquals("DPR", actualMaterial.get().getMaterialType().getMaterialCode());
    }
}