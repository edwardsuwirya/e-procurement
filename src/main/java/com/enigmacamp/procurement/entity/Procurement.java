package com.enigmacamp.procurement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_procurement")
@Getter
@Setter
@NoArgsConstructor
public class Procurement {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String transactionId;

    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    @OneToMany(mappedBy = "procurement", cascade = CascadeType.ALL)
    Set<ProcurementDetail> procurementDetailSet = new HashSet<>();

    public void addProcurementDetail(ProcurementDetail detail) {
        detail.setProcurement(this);
        procurementDetailSet.add(detail);
    }
}
