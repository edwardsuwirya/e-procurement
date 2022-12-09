package com.enigmacamp.procurement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_material_type")
@Getter
@Setter
@NoArgsConstructor
public class MaterialType {

    @Id
    private String materialCode;

    private String materialTypeName;

}
