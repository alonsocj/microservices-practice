package com.alonsodev.user.feignclients;

import com.alonsodev.user.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="car-service",url = "http://localhost:8082/car")
public interface CarFeignClient {

    @PostMapping
    public Car save(@RequestBody Car car);

    @GetMapping("/user/{userId}")
    public List<Car> getCars(@PathVariable("userId") int userId);
}
