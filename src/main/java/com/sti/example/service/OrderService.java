package com.sti.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sti.example.dto.ItemDTO;
import com.sti.example.enums.EstadoFactura;
import com.sti.example.exception.ObjetoNoEncontradoError;
import com.sti.example.model.Factura;
import com.sti.example.model.ItemFactura;
import com.sti.example.model.Stock;
import com.sti.example.model.Usuario;
import com.sti.example.repository.FacturaRepository;
import com.sti.example.repository.ItemsFacturaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final FacturaRepository facturaRepository;
    private final ItemsFacturaRepository itemsFacturaRepository;
    private final UsuarioService usuarioService;
    private final StockService stockService;

    @Autowired
    public OrderService(FacturaRepository facturaRepository, ItemsFacturaRepository itemsFacturaRepository, UsuarioService usuarioService, StockService stockService) {
        this.facturaRepository = facturaRepository;
        this.itemsFacturaRepository = itemsFacturaRepository;
        this.usuarioService = usuarioService;
        this.stockService = stockService;
    }

    @Transactional
    public Factura crearFactura(String nombreUsuario, List<ItemDTO> items) {
        Usuario usuario = usuarioService.findByUsername(nombreUsuario)
                .orElseThrow(() -> new ObjetoNoEncontradoError("Usuario '"+nombreUsuario+"' no encontrado"));

        Factura factura = new Factura();
        factura.setUsuario(usuario);
        factura.setFecha(LocalDateTime.now());
        factura.setEstado(EstadoFactura.PENDIENTE); // Estado inicial

        BigDecimal totalFactura = BigDecimal.ZERO;
        List<ItemFactura> itemsFactura = new ArrayList<>();
        for (ItemDTO itemDTO : items) {
            Stock stock = stockService.stockPorId(itemDTO.getIdStock())
//            Producto producto = productoService.productoPorId(detalle.get)
                    .orElseThrow(() -> new ObjetoNoEncontradoError("Producto no encontrado"));
            ItemFactura itemFactura = new ItemFactura();
            itemFactura.setProducto(stock.getProducto());
            itemFactura.setPrecio(stock.getPrecio()); // Guarda el precio del producto en el momento de la compra
            itemFactura.setFactura(factura); // Establece la relación con la orden
            totalFactura = totalFactura.add(stock.getProducto().getPrecio().multiply(BigDecimal.valueOf(itemDTO.getCantidad())));

             // Verificar y actualizar el stock
             int cantidadActual = stock.getCantidad();
             stockService.actualizarCantidad(stock.getProducto().getId(), stock.getUbicacion().getId(), cantidadActual-itemDTO.getCantidad());
/*             List<Stock> stocks = stockService.stockPorIdProducto(producto.getId());
            int totalStock = 0;
            for(Stock stock : stocks){
                totalStock += stock.getQuantity();
            }
            if (totalStock < orderDetail.getQuantity()) {
                throw new GeneralError("Insufficient stock for product '" + product.getName()+"'");
            } 
*/
            // Actualizar el stock (aquí se asume que hay una única ubicación de stock)
/*             for(Stock stock : stocks){
                 if(stock.getQuantity() >= orderDetail.getQuantity()){
                    stockService.updateStockQuantity(product.getId(), stock.getLocation(), stock.getQuantity() - orderDetail.getQuantity());
                    break;
                 }
                 else{
                    orderDetail.setQuantity(orderDetail.getQuantity() - stock.getQuantity());
                    stockService.updateStockQuantity(product.getId(), stock.getLocation(), 0);
                 }

            }
 */            
            itemsFactura.add(itemFactura);
        }

        factura.setTotal(totalFactura);
        factura.setItems(itemsFactura); // Asigna la lista de orderItems a la orden
        factura = facturaRepository.save(factura); // Guarda la orden para obtener el ID
        itemsFacturaRepository.saveAll(itemsFactura); // Guarda los orderItems

        return factura;
    }

    public List<Factura> facturasPorUsuario(String username) {
        Usuario usuario = usuarioService.findByUsername(username)
                .orElseThrow(() -> new ObjetoNoEncontradoError("Usuario '"+username+"' no encontrado"));
        return facturaRepository.findByUsuario(usuario);
    }

    public Optional<Factura> getOrderById(Long id) {
        return facturaRepository.findById(id);
    }
}
