package com.demo.webfluxtest.repository;

import com.demo.webfluxtest.model.Inventory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Repository
public interface InventoryRepository extends ReactiveMongoRepository<Inventory, String> {
    public Mono<Inventory> findByAlbum(String album);
    public Mono<Inventory> findByArtist(String artist);
    public Flux<Inventory> findAllByArtist(String artist);
    public Mono<Inventory> findById(int id);
    public Mono<Inventory> deleteById(int id);
    public Mono<Inventory> readById(int id);
}
