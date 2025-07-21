package com.sti.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sti.example.exception.ObjetoNoEncontradoError;
import com.sti.example.exception.ObjetoYaExisteError;
import com.sti.example.model.Producto;
import com.sti.example.repository.ProductoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> todosLosProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> productoPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto crearProducto(Producto producto) {
        Optional<Producto> existe = productoRepository.findByNombre(producto.getNombre());
        if(existe.isPresent()){
            if(existe.get().getMarca().getId() == producto.getMarca().getId() && existe.get().getCategoria().getId()==producto.getCategoria().getId()){
                throw new ObjetoYaExisteError("Producto '"+producto.getNombre()+"' ya registrado con misma marca y categoria");
            }
        }
        return productoRepository.save(producto);
    }

    public Producto actualizaProducto(Long id, Producto producto) {
        Optional<Producto> existe = productoRepository.findById(id);
        if (existe.isPresent()) {
            Producto actualizar = existe.get();
            actualizar.setNombre(producto.getNombre());
            actualizar.setDescripcion(producto.getDescripcion());
            actualizar.setPrecio(producto.getPrecio());
            actualizar.setMarca(producto.getMarca());
            actualizar.setCategoria(producto.getCategoria());
            return productoRepository.save(actualizar);
        } else {
            throw new ObjetoNoEncontradoError("Producto '"+producto.getNombre()+"' no encontrado con id:" + id);
        }
    }

    public boolean borrarProducto(Long id) {
        Optional<Producto> producto = productoRepository.findById(id); 
        if (producto.isPresent()) {
            productoRepository.deleteById(id);
            return true;
        } else {
            throw new ObjetoNoEncontradoError("Producto no encontrado con id:" + id);
        }
    }

    public List<Producto> buscarProductos(String nombre, String nombreMarca, String nombreCategoria,BigDecimal minPrecio, BigDecimal maxPrecio) {
        if ((nombre == null || nombre.isEmpty()) && (nombreMarca == null || nombreMarca.isEmpty()) && (nombreCategoria == null || nombreCategoria.isEmpty()) && minPrecio == null && maxPrecio == null) {
            return productoRepository.findAll();
        }
        return productoRepository.findByFilters(nombre, nombreMarca, nombreCategoria, minPrecio, maxPrecio);
    }

    public List<Producto> buscarProductosPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> buscarrProductosPorMarca(String marca) {
        return productoRepository.findByMarcaNombreIgnoreCase(marca);
    }

    public List<Producto> buscaProductosPorCategoria(String categoria) {
        return productoRepository.findByCategoriaNombreIgnoreCase(categoria);
    }

    public List<Producto> buscaProductosPorPrecioMinimo(BigDecimal minPrecio) {
        return productoRepository.findByPrecioMinimun(minPrecio);
    }

    public List<Producto> buscaProductosPorPrecioMaximo(BigDecimal maxPrecio) {
        return productoRepository.findByPrecioMaximun(maxPrecio);
    }

    public List<Producto> buscarProductosRangoPrecios(BigDecimal minPrecio, BigDecimal maxPrecio) {
        return productoRepository.findByPrecioBetween(minPrecio, maxPrecio);
    }
}
