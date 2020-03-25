package com.demo.webfluxtest.repository;

import com.demo.webfluxtest.model.Inventory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Repository
public interface InventoryRepository extends ReactiveMongoRepository<Inventory, String> {
    Mono<Inventory> findByAlbum(String album);
    Flux<Inventory> findAllByArtist(String artist);
    Mono<Inventory> findById(int id);
    Mono<Inventory> deleteById(int id);
}
