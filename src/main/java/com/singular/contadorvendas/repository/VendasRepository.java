package com.singular.contadorvendas.repository;

import com.singular.contadorvendas.entity.VendasEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendasRepository extends JpaRepository<VendasEntity, Long> {
    List<VendasEntity> findByStatus(String status);
}

