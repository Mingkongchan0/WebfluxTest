package com.demo.webfluxtest.webclient;

import com.demo.webfluxtest.model.Inventory;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
public class InventoryWebClient {
    public void consume() {
        WebClient client = WebClient.create("http://localhost:8080");

        Mono<Inventory> inventoryMono = client.get()
                .uri("/inventory/{id}", "1")
                .retrieve()
                .bodyToMono(Inventory.class);
        log.info(inventoryMono.subscribe());

        Flux<Inventory> inventoryFlux = client.get()
                .uri("/inventory")
                .retrieve()
                .bodyToFlux(Inventory.class);
        log.info(inventoryFlux.subscribe());

        Flux<Inventory> getAllByArtistFlux = client.get()
                .uri("/inventory/artist")
                .retrieve()
                .bodyToFlux(Inventory.class);
        log.info(getAllByArtistFlux.subscribe());
    }

}