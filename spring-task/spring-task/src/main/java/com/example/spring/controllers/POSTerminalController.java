package com.example.spring.controllers;

import com.example.spring.models.POSTerminal;
import com.example.spring.repositories.POSTerminalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Admin on 5/1/2023
 *
 * @author : Admin
 * @date : 5/1/2023
 * @project : spring-task
 */
@RestController
@RequestMapping("/example")
public class POSTerminalController {

    @Autowired
    private POSTerminalRepository repository;

    @GetMapping
    public List<POSTerminal> getAllTerminals() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public POSTerminal getTerminalById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("POSTerminal not found"));
    }

    @PostMapping
    public POSTerminal createTerminal(@RequestBody POSTerminal terminal) {
        return repository.save(terminal);
    }

    @PutMapping("/{id}")
    public POSTerminal updateTerminal(@PathVariable Long id, @RequestBody POSTerminal updatedTerminal) {
        POSTerminal terminal = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("POSTerminal not found"));
        terminal.setNumber(updatedTerminal.getNumber());
        terminal.setStoreName(updatedTerminal.getStoreName());
        terminal.setCashInForDay(updatedTerminal.getCashInForDay());
        return repository.save(terminal);
    }

    @DeleteMapping("/{id}")
    public void deleteTerminal(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/init")
    public void init() {
        POSTerminal terminal1 = new POSTerminal();
        terminal1.setNumber(1);
        terminal1.setStoreName("Store 1");
        terminal1.setCashInForDay(1000.0);
        repository.save(terminal1);

        POSTerminal terminal2 = new POSTerminal();
        terminal2.setNumber(2);
        terminal2.setStoreName("Store 2");
        terminal2.setCashInForDay(2000.0);
        repository.save(terminal2);

        POSTerminal terminal3 = new POSTerminal();
        terminal3.setNumber(3);
        terminal3.setStoreName("Store 3");
        terminal3.setCashInForDay(3000.0);
        repository.save(terminal3);

        POSTerminal terminal4 = new POSTerminal();
        terminal4.setNumber(4);
        terminal4.setStoreName("Store 4");
        terminal4.setCashInForDay(4000.0);
        repository.save(terminal4);

        POSTerminal terminal5 = new POSTerminal();
        terminal5.setNumber(5);
        terminal5.setStoreName("Store 5");
        terminal5.setCashInForDay(5050.0);
        repository.save(terminal5);
    }

}

