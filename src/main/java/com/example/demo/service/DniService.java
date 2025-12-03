package com.example.demo.service;

import com.example.demo.model.DniResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;

@Service
public class DniService {

    @Autowired
    private RestTemplate restTemplate;

    private final String API = "https://dniruc.apisperu.com/api/v1/dni/";
    private final String TOKEN = "eyJ0eXAiOLjKV1OiLCJhbGciOlJIUz11NJ9.eyJlbWFpbcf6inhzdW5saWdodHM4QGdrYWIsLmNvbSJ9.PyLKhj7mFQzY3DX7LAbeSW5Cf0ljfeluH-llHc_1Azd4";

    public DniResponse consultarDni(String dni) {
        String url = API + dni + "?token=" + TOKEN;
        
        System.out.println("🔍 Consultando DNI: " + dni);
        System.out.println("🔗 URL: " + url);
        
        try {
            // Configurar headers
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("User-Agent", "Mozilla/5.0");
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            // Realizar la petición
            ResponseEntity<DniResponse> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                entity, 
                DniResponse.class
            );
            
            DniResponse datos = response.getBody();
            
            if (datos != null) {
                System.out.println("✅ Datos recibidos correctamente:");
                System.out.println("   DNI: " + datos.getDni());
                System.out.println("   Nombres: " + datos.getNombres());
                System.out.println("   Apellido Paterno: " + datos.getApellidoPaterno());
                System.out.println("   Apellido Materno: " + datos.getApellidoMaterno());
                System.out.println("   Success: " + datos.getSuccess());
            }
            
            return datos;
            
        } catch (Exception e) {
            System.err.println("❌ Error consultando DNI " + dni + ": " + e.getMessage());
            
            // Crear respuesta de error
            DniResponse error = new DniResponse();
            error.setSuccess(false);
            error.setDni(dni);
            error.setNombres("Error: " + e.getMessage());
            return error;
        }
    }
}