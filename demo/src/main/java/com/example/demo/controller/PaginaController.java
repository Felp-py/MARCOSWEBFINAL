package com.example.demo.controller;

import com.example.demo.model.Libro;
import com.example.demo.repository.LibroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PaginaController {

    private final LibroRepository libroRepository;

    public PaginaController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/nosotros")
    public String nosotros() {
        return "nosotros";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/catalogo")
    public String verCatalogo(Model model) {
        // Trae solo libros con stock disponible
        List<Libro> libros = libroRepository.findByStockGreaterThan(0);
        model.addAttribute("libros", libros);
        return "catalogo"; // apunta a catalogo.html
    }

    @GetMapping("/compras")
    public String compras(Model model) {
        // Inicializa carrito vacío
        model.addAttribute("carrito", new ArrayList<Libro>());
        model.addAttribute("total", 0.0);
        return "compras";
    }
}
