package com.example.demo.service;

import com.example.demo.model.ItemCarrito;
import java.util.List;
import java.util.Optional;

public interface ItemCarritoService {
    List<ItemCarrito> findAll();
    Optional<ItemCarrito> findById(Integer id);
    ItemCarrito save(ItemCarrito item);
    void deleteById(Integer id);
}
