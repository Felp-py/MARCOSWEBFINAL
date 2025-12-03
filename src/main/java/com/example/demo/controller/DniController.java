package com.example.demo.controller;

import com.example.demo.model.DniResponse;
import com.example.demo.service.DniService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dni")
public class DniController {

    @Autowired
    private DniService dniService;

    @GetMapping("/{dni}")
    public DniResponse buscar(@PathVariable String dni) {
        return dniService.buscar(dni);
    }
}
