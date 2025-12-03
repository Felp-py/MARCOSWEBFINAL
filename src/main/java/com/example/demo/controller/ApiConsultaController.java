package com.example.demo.controller;

import com.example.demo.model.DniResponse;
import com.example.demo.service.DniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consulta")
@CrossOrigin(origins = "*") 
public class ApiConsultaController {

    @Autowired
    private DniService dniService;

    @GetMapping("/dni/{dni}")
    public DniResponse buscarDni(@PathVariable String dni) {
        return dniService.consultarDni(dni);
    }
}