package com.example.demo.controller;

import com.example.demo.model.ItemCarrito;
import com.example.demo.repository.LibroRepository;
import com.example.demo.model.Libro;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("carrito")
@RequestMapping("/carrito")
public class CarritoController {

    private final LibroRepository libroRepository;

    public CarritoController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @ModelAttribute("carrito")
    public List<ItemCarrito> inicializarCarrito() {
        return new ArrayList<>();
    }

    @GetMapping
    public String verCarrito(@ModelAttribute("carrito") List<ItemCarrito> carrito,
                             org.springframework.ui.Model model) {
        BigDecimal total = carrito.stream()
                .map(ItemCarrito::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("total", total);
        return "compras"; 
    }

    @PostMapping("/agregar")
    public String agregarAlCarrito(@RequestParam Integer idLibro,
                                   @RequestParam(defaultValue = "1") int cantidad,
                                   @ModelAttribute("carrito") List<ItemCarrito> carrito) {

        Optional<Libro> libroOpt = libroRepository.findById(idLibro);

        if (libroOpt.isPresent()) {
            Libro libro = libroOpt.get();
            boolean existe = false;

            for (ItemCarrito item : carrito) {
                if (item.getIdLibro().equals(idLibro)) {
                    item.setCantidad(item.getCantidad() + cantidad);
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                ItemCarrito nuevoItem = new ItemCarrito(
                        libro.getIdLibro(),
                        libro.getTitulo(),
                        libro.getPrecio(),
                        cantidad
                );
                carrito.add(nuevoItem);
            }
        }

        return "redirect:/carrito";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarItem(@PathVariable Long id,
                               @ModelAttribute("carrito") List<ItemCarrito> carrito) {
        carrito.removeIf(item -> item.getIdLibro().equals(id));
        return "redirect:/carrito";
    }
}
