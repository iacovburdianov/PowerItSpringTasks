package com.example.spring.repositories;

import com.example.spring.models.POSTerminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface POSTerminalRepository extends JpaRepository<POSTerminal, Long> {
}

