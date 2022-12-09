package com.enigmacamp.procurement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "m_material")
@Getter
@Setter
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String materialId;

    private String materialName;

    @ManyToOne
    @JoinColumn(name = "material_type_code", nullable = false)
    private MaterialType materialType;

    @OneToMany(mappedBy = "material")
    Set<VendorMaterial> vendorMaterialSet;
}
