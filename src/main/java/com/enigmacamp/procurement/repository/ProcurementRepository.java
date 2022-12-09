package com.enigmacamp.procurement.repository;

import com.enigmacamp.procurement.entity.Procurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProcurementRepository extends JpaRepository<Procurement, String> {
    List<Procurement> findByTransactionDateBetween(Date startDate, Date endDate);
}
