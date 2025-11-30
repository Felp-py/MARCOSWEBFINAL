package com.example.demo.repository;

import com.example.demo.model.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecomendacionRepository extends JpaRepository<Recomendacion, Integer> {

    // Método para buscar recomendaciones por nombre de cliente, ignorando mayúsculas/minúsculas
    List<Recomendacion> findByClienteContainingIgnoreCase(String cliente);
}
