package com.example.demo.controller;

import com.example.demo.model.Autor;
import com.example.demo.model.Categoria;
import com.example.demo.model.Editorial;
import com.example.demo.model.Libro;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.EditorialRepository;
import com.example.demo.repository.LibroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/libros")
public class LibroController {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;
    private final EditorialRepository editorialRepository;

    public LibroController(
            LibroRepository libroRepository,
            AutorRepository autorRepository,
            CategoriaRepository categoriaRepository,
            EditorialRepository editorialRepository
    ) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
        this.editorialRepository = editorialRepository;
    }

    @GetMapping("/nuevo")
    public String nuevoLibroForm(Model model) {
        model.addAttribute("autores", autorRepository.findAll());
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("editoriales", editorialRepository.findAll());
        return "nuevo-libro";
    }

    @PostMapping("/guardar")
    public String guardarLibro(
            @RequestParam String titulo,
            @RequestParam Integer idAutor,
            @RequestParam Integer idCategoria,
            @RequestParam Integer idEditorial,
            @RequestParam BigDecimal precio,
            @RequestParam Integer stock,
            @RequestParam String descripcion,
            @RequestParam(required = false) String fechaPublicacion
    ) {
        Autor autor = autorRepository.findById(idAutor).orElseThrow();
        Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow();
        Editorial editorial = editorialRepository.findById(idEditorial).orElseThrow();

        Libro nuevo = new Libro();
        nuevo.setTitulo(titulo);
        nuevo.setAutor(autor);
        nuevo.setCategoria(categoria);
        nuevo.setEditorial(editorial);
        nuevo.setPrecio(precio);
        nuevo.setStock(stock);
        nuevo.setDescripcion(descripcion);

        if (fechaPublicacion != null && !fechaPublicacion.isEmpty()) {
            nuevo.setFechaPublicacion(LocalDate.parse(fechaPublicacion));
        }

        libroRepository.save(nuevo);
        return "redirect:/libros/lista";
    }

    @GetMapping("/lista")
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroRepository.findAll());
        return "lista-libros";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Integer id) {
        libroRepository.deleteById(id);
        return "redirect:/libros/lista";
    }
}
