package com.enigmacamp.procurement.service;

import com.enigmacamp.procurement.entity.Material;
import org.springframework.data.domain.Page;

public interface MaterialService {
    Page<Material> getListMaterial(Integer page, Integer size, String direction, String sortBy);

    Material registerNewMaterial(Material material);
}
