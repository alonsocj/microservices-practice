package com.alonsodev.carro.controller;

import com.alonsodev.carro.entity.Car;
import com.alonsodev.carro.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> listCars() {
        List<Car> cars = carService.getAll();
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") int id){
        Car car = carService.getById(id);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        Car newCar = carService.save(car);
        return ResponseEntity.ok(newCar);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> getCarsByUser(@PathVariable("userId") int userId){
        List<Car> cars = carService.getByUser(userId);
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

}
