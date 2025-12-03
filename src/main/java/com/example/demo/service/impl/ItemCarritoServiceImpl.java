package com.example.demo.service.impl;

import com.example.demo.model.ItemCarrito;
import com.example.demo.repository.ItemCarritoRepository;
import com.example.demo.service.ItemCarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCarritoServiceImpl implements ItemCarritoService {

    @Autowired
    private ItemCarritoRepository itemCarritoRepository;

    @Override
    public List<ItemCarrito> findAll() {
        return itemCarritoRepository.findAll();
    }

    @Override
    public Optional<ItemCarrito> findById(Integer id) {
        return itemCarritoRepository.findById(id);
    }

    @Override
    public ItemCarrito save(ItemCarrito item) {
        return itemCarritoRepository.save(item);
    }

    @Override
    public void deleteById(Integer id) {
        itemCarritoRepository.deleteById(id);
    }
}
