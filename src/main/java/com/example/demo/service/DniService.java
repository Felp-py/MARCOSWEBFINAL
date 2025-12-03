package com.example.demo.service;

import com.example.demo.model.DniResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DniService {

    @Autowired
    private RestTemplate restTemplate;

    private final String API = "https://dniruc.apisperu.com/api/v1/dni/";
    private final String TOKEN = "demo";

    public DniResponse buscar(String dni) {
        String url = API + dni + "?token=" + TOKEN;
        return restTemplate.getForObject(url, DniResponse.class);
    }
}
