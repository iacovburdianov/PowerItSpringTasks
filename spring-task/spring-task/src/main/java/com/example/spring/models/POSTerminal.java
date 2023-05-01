package com.example.spring.models;

import jakarta.persistence.*;

/**
 * Created by Admin on 5/1/2023
 *
 * @author : Admin
 * @date : 5/1/2023
 * @project : spring-task
 */
@Entity
@Table(name = "pos_terminals")
public class POSTerminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "cash_in_for_day", nullable = false)
    private Double cashInForDay;
    public POSTerminal() {

    }

    public POSTerminal(Integer number, String storeName, Double cashInForDay) {
        this.number = number;
        this.storeName = storeName;
        this.cashInForDay = cashInForDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Double getCashInForDay() {
        return cashInForDay;
    }

    public void setCashInForDay(Double cashInForDay) {
        this.cashInForDay = cashInForDay;
    }
}
