package com.enigmacamp.procurement.repository;

import com.enigmacamp.procurement.entity.MaterialType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegisterMaterialTypeTest {
    @Autowired
    MaterialTypeRepository materialTypeRepository;

    @Test
    @Commit
    void itShould_CreateMaterialType() {
        MaterialType materialType = new MaterialType();
        materialType.setMaterialCode("PRT");
        materialType.setMaterialTypeName("Peralatan Rumah Tangga");
        materialTypeRepository.save(materialType);

        MaterialType materialType2 = new MaterialType();
        materialType2.setMaterialCode("PRS");
        materialType2.setMaterialTypeName("Peralatan Sekolah");
        materialTypeRepository.save(materialType2);
    }
}