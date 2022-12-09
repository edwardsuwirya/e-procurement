package com.enigmacamp.procurement.service.impl;

import com.enigmacamp.procurement.entity.Material;
import com.enigmacamp.procurement.entity.Vendor;
import com.enigmacamp.procurement.entity.VendorMaterial;
import com.enigmacamp.procurement.model.request.NewVendorRequest;
import com.enigmacamp.procurement.repository.VendorRepository;
import com.enigmacamp.procurement.service.VendorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Page<Vendor> getListVendor(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        return vendorRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Vendor registerNewVendor(NewVendorRequest vendorRequest) {
        Set<VendorMaterial> vendorMaterials = new HashSet<>();
        Vendor vendor = vendorRequest.getVendor();
        for (VendorMaterial material : vendorRequest.getMaterialList()) {
            material.setVendor(vendor);
            material.setIsActive(true);
            vendorMaterials.add(material);
        }
        vendor.setVendorMaterialSet(vendorMaterials);
        return vendorRepository.save(vendor);
    }

    @Override
    @Transactional
    public Vendor updateVendorMaterialPrice(String vendorId, String materialId, Double newPrice) {
        Optional<Vendor> oVendor = vendorRepository.findById(vendorId);
        if (oVendor.isPresent()) {
            Vendor vendor = oVendor.get();


            Material material = new Material();
            material.setMaterialId(materialId);
            Set<VendorMaterial> materialSet = vendor.getVendorMaterialSet();
            List<VendorMaterial> result = materialSet
                    .stream()
                    .filter(vm -> vm.getMaterial().getMaterialId().equals(materialId) && vm.getIsActive())
                    .collect(Collectors.toList());
            for (VendorMaterial vm : result) {
                vm.setIsActive(false);
            }
            VendorMaterial newVendorMaterial = new VendorMaterial();
            newVendorMaterial.setIsActive(true);
            newVendorMaterial.setPrice(newPrice);
            newVendorMaterial.setMaterial(material);
            newVendorMaterial.setVendor(vendor);
            materialSet.add(newVendorMaterial);

            return vendorRepository.save(vendor);
        } else {
            return null;
        }

    }
}
