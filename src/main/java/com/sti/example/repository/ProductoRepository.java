package com.sti.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sti.example.model.Producto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByMarcaNombreIgnoreCase(String nombreCategoria);
    List<Producto> findByCategoriaNombreIgnoreCase(String nombreCategoria);

    @Query("SELECT p FROM Producto p WHERE p.precio >= :minPrecio")
    List<Producto> findByPrecioMinimun(@Param("minPrecio") BigDecimal minPrecio);

    @Query("SELECT p FROM Producto p WHERE p.precio <= :maxPrecio")
    List<Producto> findByPrecioMaximun(@Param("maxPrecio") BigDecimal maxPrecio);

    @Query("SELECT p FROM Producto p WHERE p.precio BETWEEN :minPrecio AND :maxPrecio")
    List<Producto> findByPrecioBetween(@Param("minPrecio") BigDecimal minPrecio, @Param("maxPrecio") BigDecimal maxPrecio);

    @Query("SELECT p FROM Producto p WHERE " +
           "(:nombre is null or lower(p.nombre) like lower(concat('%', :nombre, '%'))) and " +
           "(:nombreMarca is null or lower(p.marca.nombre) = lower(:nombreMarca)) and " +
           "(:nombreCategoria is null or lower(p.categoria.nombre) = lower(:nombreCategoria)) and " +
           "(:minPrecio is null or p.precio >= :minPrecio) and " +
           "(:maxPrecio is null or p.precio <= :maxPrecio) ORDER BY p.nombre ASC")
    List<Producto> findByFilters(
            @Param("nombre") String nombre,
            @Param("nombreMarca") String nombreMarca,
            @Param("nombreCategoria") String nombreCategoria,
            @Param("minPrecio") BigDecimal minPrecio,
            @Param("maxPrecio") BigDecimal maxPrecio);
}
