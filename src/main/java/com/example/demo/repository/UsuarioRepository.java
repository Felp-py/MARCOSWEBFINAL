package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    // ELIMINA temporalmente findByNombreUsuario y usa solo estos:
    
    // Método 1: Buscar por el campo que realmente existe
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    Optional<Usuario> findByNombre(@Param("nombre") String nombre);
    
    // Método 2: Buscar por email
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Optional<Usuario> findByEmail(@Param("email") String email);
    
    // Método 3: Buscar por nombre O email (útil para login)
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :texto OR u.email = :texto")
    Optional<Usuario> findByNombreOrEmail(@Param("texto") String texto);
}