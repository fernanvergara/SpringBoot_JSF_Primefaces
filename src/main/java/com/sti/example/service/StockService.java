/**
 * Nota:
 * Estoy omitiendo el uso de la interfaz para los servicios
 */
package com.sti.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sti.example.exception.ObjetoNoEncontradoError;
import com.sti.example.model.Stock;
import com.sti.example.repository.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> todosLosStocks() {
        return stockRepository.findAll();
    }

    public Optional<Stock> stockPorId(Long id) {
        Optional<Stock> existe = stockRepository.findById(id);
        if (existe.isPresent()) {
            return existe;
        } else {
             throw new ObjetoNoEncontradoError("Stock no encontrado con id:" + id);
        }
    }

    public Stock crearStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock actualizarStock(Long id, Stock stock) {
        Optional<Stock> existe = stockRepository.findById(id);
        if (existe.isPresent()) {
            Stock actualizar = existe.get();
            actualizar.setProducto(stock.getProducto());
            actualizar.setCantidad(stock.getCantidad());
            actualizar.setUbicacion(stock.getUbicacion());
            return stockRepository.save(actualizar);
        } else {
             throw new ObjetoNoEncontradoError("Stock de '"+stock.getProducto().getNombre()+"' no encontrado con id:" + id);
        }
    }

    public boolean borrarStock(Long id) {
        Optional<Stock> stock = stockRepository.findById(id); 
        if (stock.isPresent()) {
            stockRepository.deleteById(id);
            return true;
        } else {
            throw new ObjetoNoEncontradoError("Stock no encontrado con id:" + id);
        }
    }

    public Optional<Stock> stockPorProductoUbicacion(Long productId, Long ubicacionId) {
        return stockRepository.findByProductoIdAndUbicacionId(productId, ubicacionId);
    }

    public List<Stock> stockPorIdProducto(Long idProducto) {
        return stockRepository.findByProductoId(idProducto);
    }

    @Transactional
    public void actualizarCantidad(Long idProducto, Long ubicacion, int cantidad) {
        Optional<Stock> stock = stockRepository.findByProductoIdAndUbicacionId(idProducto, ubicacion);
        if (stock.isPresent()) {
            Stock actualizar = stock.get();
            actualizar.setCantidad(cantidad);
            stockRepository.save(actualizar);
        } else {
            throw new ObjetoNoEncontradoError("Stock no encontrado por producto id:"+idProducto+" y ubicación:"+ubicacion);
        }
    }
}
