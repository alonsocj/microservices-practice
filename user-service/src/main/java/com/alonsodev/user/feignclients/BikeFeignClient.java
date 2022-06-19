package com.alonsodev.user.feignclients;

import com.alonsodev.user.models.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name ="bike-service",url = "http://localhost:8083/bike")
public interface BikeFeignClient {

    @PostMapping
    public Bike save(@RequestBody Bike bike);

    @GetMapping("/user/{userId}")
    public List<Bike> getBikes(@PathVariable("userId") int userId);
}
