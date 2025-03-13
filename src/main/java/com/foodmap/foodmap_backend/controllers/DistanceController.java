package com.foodmap.foodmap_backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api")
public class DistanceController {

    private final WebClient webClient;
    private static final String API_KEY = "2862ae7264ada70ab3e1d0af77062bbe";
    private static final String MAPMYINDIA_URL = "https://apis.mapmyindia.com/advancedmaps/v1/" + API_KEY + "/distance_matrix_eta/walking/{coordinates}";

    public DistanceController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://apis.mapmyindia.com").build();
    }

    @GetMapping("/distance")
    public Mono<Object> getDistance() {
        String coordinates = "12.9210,77.6201;12.9664,77.7488"; // 12.966485820176763, 77.74888329422765
        return webClient.get()
                .uri(MAPMYINDIA_URL, coordinates)
                .retrieve()
                .bodyToMono(Object.class) // Adjust based on actual response structure
                .onErrorResume(e -> Mono.just("Error fetching distance: " + e.getMessage()));
    }
}
