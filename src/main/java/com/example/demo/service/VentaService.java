package com.example.demo.service;

import com.example.demo.model.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface VentaService {

    List<Venta> findAll();

    Optional<Venta> findById(Long id);   // ← CORREGIDO

    Venta save(Venta venta);

    void deleteById(Long id);            // ← CORREGIDO

    Venta procesarCompra(
            List<ItemCarrito> carrito,
            Cliente cliente,
            MetodoPago metodoPago,
            TipoEntrega tipoEntrega,
            BigDecimal totalCalculado
    );
}
