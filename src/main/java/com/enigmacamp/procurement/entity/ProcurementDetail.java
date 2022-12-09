package com.enigmacamp.procurement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_procurement_detail")
@Getter
@Setter
@NoArgsConstructor
public class ProcurementDetail {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String transactionDetailId;

    @ManyToOne
    @JoinColumn(name = "procurement_id")
    Procurement procurement;

    @ManyToOne
    @JoinColumn(name = "vendor_material_id")
    VendorMaterial vendorMaterial;

    Double qty;

}
