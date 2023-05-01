package com.example.demo.repository;

/**
 * Created by Admin on 5/1/2023
 *
 * @author : Admin
 * @date : 5/1/2023
 * @project : demo
 */




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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

    @Pattern(regexp = "^(MD|PL|PT)$", message = "Invalid country code. Valid values are: MD, PL, PT")
    private String code;
}

