package com.example.demo.service;

import com.example.demo.model.DniResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DniService {
    
    public DniResponse consultarDni(String dni) {
        System.out.println("=== USANDO API NUEVA: api.apis.net.pe ===");
        System.out.println("Consultando DNI: " + dni);
        
        try {
            // 1. Primero intentamos con la API correcta
            WebClient webClient = WebClient.builder()
                    .baseUrl("https://api.apis.net.pe")
                    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .build();
            
            DniResponse response = webClient.get()
                    .uri("/v1/dni?numero=" + dni)
                    .retrieve()
                    .bodyToMono(DniResponse.class)
                    .block();
            
            if (response != null && response.getNombres() != null) {
                System.out.println("✅ API funcionó: " + response.getNombres());
                return response;
            }
            
            // 2. Si falla, usar datos mock
            System.out.println("⚠️ API no respondió, usando datos mock");
            return generarDatosMock(dni);
            
        } catch (Exception e) {
            System.err.println("❌ Error en API: " + e.getMessage());
            // 3. Si hay error, usar datos mock
            return generarDatosMock(dni);
        }
    }
    
    private DniResponse generarDatosMock(String dni) {
        System.out.println("🔄 Generando datos mock para: " + dni);
        
        DniResponse mock = new DniResponse();
        
        // Datos de ejemplo según el último dígito
        int ultimoDigito = Integer.parseInt(dni.substring(dni.length() - 1));
        
        switch (ultimoDigito % 5) {
            case 0:
                mock.setNombres("JUAN CARLOS");
                mock.setApellidoPaterno("PEREZ");
                mock.setApellidoMaterno("GOMEZ");
                break;
            case 1:
                mock.setNombres("MARIA ISABEL");
                mock.setApellidoPaterno("RODRIGUEZ");
                mock.setApellidoMaterno("LOPEZ");
                break;
            case 2:
                mock.setNombres("CARLOS ALBERTO");
                mock.setApellidoPaterno("MARTINEZ");
                mock.setApellidoMaterno("SANCHEZ");
                break;
            case 3:
                mock.setNombres("ANA LUCIA");
                mock.setApellidoPaterno("GARCIA");
                mock.setApellidoMaterno("FERNANDEZ");
                break;
            case 4:
                mock.setNombres("LUIS MIGUEL");
                mock.setApellidoPaterno("DIAZ");
                mock.setApellidoMaterno("ROMERO");
                break;
        }
        
        mock.setTipoDocumento("DNI");
        mock.setNumeroDocumento(dni);
        
        System.out.println("✅ Mock generado: " + mock.getNombres() + " " + mock.getApellidoPaterno());
        return mock;
    }
}