package com.example.demo.controller;

import com.example.demo.model.Recomendacion;
import com.example.demo.service.RecomendacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recomendaciones")
public class RecomendacionController {

    private final RecomendacionService service;

    public RecomendacionController(RecomendacionService service) {
        this.service = service;
    }

@GetMapping
public String verRecomendaciones(Model model) {
    model.addAttribute("nuevaRecomendacion", new Recomendacion());
    model.addAttribute("recomendaciones", service.findAll());
    return "recomendaciones";
}

@PostMapping("/agregar")
public String agregar(@ModelAttribute Recomendacion recomendacion) {
    service.save(recomendacion);
    return "redirect:/recomendaciones";
}

@GetMapping("/eliminar/{id}")
public String eliminar(@PathVariable int id) {
    service.deleteById(id);
    return "redirect:/recomendaciones";
}

}
