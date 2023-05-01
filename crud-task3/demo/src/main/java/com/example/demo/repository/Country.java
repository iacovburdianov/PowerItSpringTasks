package com.example.demo.repository;


import jakarta.persistence.*;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^(MD|PL|PT)$", message = "Code must be MD, PL, or PT")
    private String code;
}


