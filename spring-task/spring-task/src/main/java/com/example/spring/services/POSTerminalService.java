package com.example.spring.services;

import com.example.spring.models.POSTerminal;
import com.example.spring.repositories.POSTerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 5/1/2023
 *
 * @author : Admin
 * @date : 5/1/2023
 * @project : spring-task
 */
@Service
public class POSTerminalService {
    @Autowired
    private POSTerminalRepository posTerminalRepository;

    public POSTerminal getPOSTerminalById(Long id) {
        Optional<POSTerminal> optionalPOSTerminal = posTerminalRepository.findById(id);
        return optionalPOSTerminal.orElse(null);
    }

    public List<POSTerminal> getAllPOSTerminals() {
        return posTerminalRepository.findAll();
    }

    public POSTerminal createPOSTerminal(POSTerminal posTerminal) {
        return posTerminalRepository.save(posTerminal);
    }

    public POSTerminal updatePOSTerminal(Long id, POSTerminal posTerminal) {
        Optional<POSTerminal> optionalPOSTerminal = posTerminalRepository.findById(id);
        if (optionalPOSTerminal.isPresent()) {
            POSTerminal existingPOSTerminal = optionalPOSTerminal.get();
            existingPOSTerminal.setNumber(posTerminal.getNumber());
            existingPOSTerminal.setStoreName(posTerminal.getStoreName());
            existingPOSTerminal.setCashInForDay(posTerminal.getCashInForDay());
            return posTerminalRepository.save(existingPOSTerminal);
        } else {
            return null;
        }
    }

    public boolean deletePOSTerminal(Long id) {
        Optional<POSTerminal> optionalPOSTerminal = posTerminalRepository.findById(id);
        if (optionalPOSTerminal.isPresent()) {
            posTerminalRepository.delete(optionalPOSTerminal.get());
            return true;
        } else {
            return false;
        }
    }
}

