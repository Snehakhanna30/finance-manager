package com.snehakhanna.financemanager.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity // tells Spring that this class is a JPA entity mapped to a database table
public class Expense {

    @Id // this field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incremented
    private Long id;

    private String description;
    private double amount;
    private LocalDate date;
    private String type; // "income" or "expense"

    // ✅ Default constructor (required by JPA)
    public Expense() {
    }

    // ✅ Constructor with all fields (except ID since it's auto-generated)
    public Expense(String description, double amount, LocalDate date, String type) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
