package com.alonsodev.carro.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;
    private String model;
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id")
    private int userId;

    public Car() {
    }

    public Car(int id, String brand, String model, int userId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
