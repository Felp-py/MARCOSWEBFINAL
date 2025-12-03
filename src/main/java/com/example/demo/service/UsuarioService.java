package com.example.demo.service;

import com.example.demo.model.Usuario;
import java.util.List;

public interface UsuarioService {

    Usuario registrar(Usuario usuario);

    Usuario obtenerPorId(Integer id);

    Usuario obtenerPorEmail(String email);

    Usuario obtenerPorNombreUsuario(String nombreUsuario);

    List<Usuario> listarTodos();
}
