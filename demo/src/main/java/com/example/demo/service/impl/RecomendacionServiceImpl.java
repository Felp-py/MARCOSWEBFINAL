package com.example.demo.service.impl;

import com.example.demo.model.Recomendacion;
import com.example.demo.repository.RecomendacionRepository;
import com.example.demo.service.RecomendacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecomendacionServiceImpl implements RecomendacionService {

    @Autowired
    private RecomendacionRepository recomendacionRepository;

    @Override
    public List<Recomendacion> findAll() {
        return recomendacionRepository.findAll();
    }

    @Override
    public Optional<Recomendacion> findById(Integer id) {
        return recomendacionRepository.findById(id);
    }

    @Override
    public Recomendacion save(Recomendacion recomendacion) {
        return recomendacionRepository.save(recomendacion);
    }

    @Override
    public void deleteById(Integer id) {
        recomendacionRepository.deleteById(id);
    }

    @Override
    public List<Recomendacion> findByClienteContaining(String cliente) {
        return recomendacionRepository.findByClienteContainingIgnoreCase(cliente);
    }
}
