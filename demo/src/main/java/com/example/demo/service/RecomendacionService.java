package com.example.demo.service;

import com.example.demo.model.Recomendacion;
import java.util.List;
import java.util.Optional;

public interface RecomendacionService {
    List<Recomendacion> findAll();
    Optional<Recomendacion> findById(Integer id);
    Recomendacion save(Recomendacion recomendacion);
    void deleteById(Integer id);

    List<Recomendacion> findByClienteContaining(String cliente);
}
