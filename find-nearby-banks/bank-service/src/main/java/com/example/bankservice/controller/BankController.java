package com.example.bankservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/nearby")
    public List<Map<String, Object>> getNearbyBanks(@RequestParam String zipcode) {
        String locationServiceUrl = "http://localhost:8081/api/location/banks?zipcode=" + zipcode;
        return restTemplate.getForObject(locationServiceUrl, List.class);
    }
}