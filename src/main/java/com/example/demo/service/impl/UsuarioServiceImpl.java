package com.example.demo.service.impl;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioServiceImpl(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        return repo.save(usuario);
    }

    @Override
    public Usuario obtenerPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Usuario obtenerPorEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    @Override
    public Usuario obtenerPorNombreUsuario(String nombreUsuario) {
        return repo.findByNombreUsuario(nombreUsuario).orElse(null);
    }

    @Override
    public List<Usuario> listarTodos() {
        return repo.findAll();
    }
}
