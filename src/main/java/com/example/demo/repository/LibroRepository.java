package com.example.demo.repository;

import com.example.demo.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

    Optional<Libro> findByTitulo(String titulo);
    List<Libro> findByStockGreaterThan(Integer cantidad);
}
