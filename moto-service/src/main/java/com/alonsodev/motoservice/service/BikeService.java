package com.alonsodev.motoservice.service;

import com.alonsodev.motoservice.entity.Bike;
import com.alonsodev.motoservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;
    public List<Bike> getAll(){
        return bikeRepository.findAll();
    }
    public Bike getById(int id){
        return bikeRepository.findById(id).orElse(null);
    }
    @Transactional
    public Bike save(Bike bike){
        return bikeRepository.save(bike);
    }

    public List<Bike> getByUser(int userId){
        return bikeRepository.findByUserId(userId);
    }

}
