package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Admin on 5/2/2023
 *
 * @author : Admin
 * @date : 5/2/2023
 * @project : demo
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "people_count")
    private String peopleCount;

    @ManyToOne
    @JoinColumn(name = "countries_id", referencedColumnName = "id")
    private Country country;
}
