package com.alonsodev.user.service;


import com.alonsodev.user.entity.User;
import com.alonsodev.user.feignclients.BikeFeignClient;
import com.alonsodev.user.feignclients.CarFeignClient;
import com.alonsodev.user.models.Bike;
import com.alonsodev.user.models.Car;
import com.alonsodev.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarFeignClient carFeignClient;

    @Autowired
    private BikeFeignClient bikeFeignClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public List<Car> getCars(int userId) {
        List<Car> cars = restTemplate.getForObject("http://localhost:8082/car/user/" + userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId) {
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8083/bike/user/" + userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        Car newCar = carFeignClient.save(car);
        return newCar;
    }

    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        Bike newBike = bikeFeignClient.save(bike);
        return newBike;
    }

    public Map<String, Object> getVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            result.put("Message:", "The user doesn't exist");
            return result;
        }
        result.put("User:", user);
        List<Car> cars = carFeignClient.getCars(userId);
        if (cars == null) {
            result.put("Cars:", "The user don't have cars");
        } else {
            result.put("Cars:", cars);
        }

        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if (bikes == null) {
            result.put("Bike:", "The user don't have bikes");
        } else {
            result.put("Bike:", bikes);
        }

        return result;
    }
}
