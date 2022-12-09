package com.enigmacamp.procurement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "m_vendor")
@Getter
@Setter
@NoArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String vendorId;

    private String vendorName;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    Set<VendorMaterial> vendorMaterialSet;

}
