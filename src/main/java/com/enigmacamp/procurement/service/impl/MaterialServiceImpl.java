package com.enigmacamp.procurement.service.impl;

import com.enigmacamp.procurement.entity.Material;
import com.enigmacamp.procurement.repository.MaterialRepository;
import com.enigmacamp.procurement.service.MaterialService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Page<Material> getListMaterial(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        return materialRepository.findAll(pageable);
    }

    @Override
    public Material registerNewMaterial(Material material) {
        return materialRepository.save(material);
    }
}
