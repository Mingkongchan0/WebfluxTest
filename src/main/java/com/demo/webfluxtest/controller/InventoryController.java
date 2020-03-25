package com.demo.webfluxtest.controller;

import com.demo.webfluxtest.model.Inventory;
import com.demo.webfluxtest.repository.InventoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/inventory")
@Log4j2
public class InventoryController {
    @Autowired
    private InventoryRepository invRepo;

    @GetMapping("/{id}")
    public Mono<Inventory> getById(@PathVariable Integer id) {
        log.info("ID utilized for request = {}", id);
        return invRepo.findById(id);
    }

    @GetMapping
    public Flux<Inventory> findAll() {
        return invRepo.findAll();
    }

    @GetMapping("/artist/{artist}")
    public Flux<Inventory> findAllByArtist(@PathVariable String artist) {
        log.info("Artist utilized for request = {}", artist);
        return invRepo.findAllByArtist(artist);
    }

    @GetMapping("/album/{album}")
    public Mono<Inventory> findByAlbum(@PathVariable String album) {
        log.info("Album utilized for request = {}", album);
        return invRepo.findByAlbum(album);
    }

    @PostMapping("/set")
    public Mono<Inventory> setInventory(@RequestBody Inventory inventory) {
        log.info("{} has been inserted into the database", inventory);
        return invRepo.insert(inventory);
    }

    @DeleteMapping("/del/{id}")
    public Mono<Inventory> delInventory(@PathVariable Integer id) {
        log.info("ID utilized for request = {}", id);
        return invRepo.deleteById(id);
    }

    @PutMapping("/update")
    public Mono<Inventory> updateInventory(@RequestBody Inventory inventory) {
        log.info("{} has updated an entry in the database", inventory);
        return invRepo.save(inventory);
    }
}