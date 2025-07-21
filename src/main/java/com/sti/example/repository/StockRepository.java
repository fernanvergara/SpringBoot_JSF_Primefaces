package com.sti.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sti.example.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByProductoIdAndUbicacionId(Long idProducto, Long idUbicacion);
    List<Stock> findByProductoId(Long idProducto);
}
