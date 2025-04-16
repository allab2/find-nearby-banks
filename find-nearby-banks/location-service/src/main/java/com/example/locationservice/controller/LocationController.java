package com.example.locationservice.controller;

import com.example.locationservice.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/banks")
    public List<Map<String, Object>> getNearbyBanks(@RequestParam String zipcode) {
        return locationService.findNearbyBanks(zipcode);
    }
}