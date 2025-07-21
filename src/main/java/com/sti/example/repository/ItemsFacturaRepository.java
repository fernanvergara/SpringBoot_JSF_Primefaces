package com.sti.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sti.example.model.ItemFactura;

@Repository
public interface ItemsFacturaRepository extends JpaRepository<ItemFactura, Long> {
    // Agregar los que se consideren necesarios
}