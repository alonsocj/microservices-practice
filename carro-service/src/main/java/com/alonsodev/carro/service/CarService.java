package com.alonsodev.carro.service;

import com.alonsodev.carro.entity.Car;
import com.alonsodev.carro.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }
    public Car getById(int id){
        return carRepository.findById(id).orElse(null);
    }
    @Transactional
    public Car save(Car car){
        return carRepository.save(car);
    }

    public List<Car> getByUser(int userId){
        return carRepository.findByUserId(userId);
    }
}
