package com.example.locationservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

@Service
public class LocationService {

    @Value("${google.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Map<String, Object>> findNearbyBanks(String zipcode) {
        String geoUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + zipcode + "&key=" + apiKey;
        Map<?, ?> geoResponse = restTemplate.getForObject(geoUrl, Map.class);
        if (geoResponse == null) return List.of();

        List<Map<String, Object>> results = (List<Map<String, Object>>) geoResponse.get("results");
        if (results.isEmpty()) return List.of();

        Map<String, Object> location = (Map<String, Object>) ((Map<String, Object>) results.get(0).get("geometry")).get("location");
        double lat = (Double) location.get("lat");
        double lng = (Double) location.get("lng");

        String placesUrl = String.format(
            "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=16093&type=bank&key=%s",
            lat, lng, apiKey);

        Map<?, ?> placeResponse = restTemplate.getForObject(placesUrl, Map.class);
        if (placeResponse == null) return List.of();

        return (List<Map<String, Object>>) placeResponse.get("results");
    }
}