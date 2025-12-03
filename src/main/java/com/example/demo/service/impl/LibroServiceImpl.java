package com.example.demo.service.impl;

import com.example.demo.model.Libro;
import com.example.demo.repository.LibroRepository;
import com.example.demo.service.LibroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository repo;

    public LibroServiceImpl(LibroRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Libro> findAll() {
        return (List<Libro>) repo.findAll();
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Libro save(Libro libro) {
        return repo.save(libro);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
