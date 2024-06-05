package com.tdd.tddTest.controller;

import com.tdd.tddTest.domain.car.Car;
import com.tdd.tddTest.domain.car.CarRepository;
import com.tdd.tddTest.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    // GET Method for reading operation
    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // GET Method for Read operation
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarsById(@PathVariable(value = "id") Long carId)
            throws ResourceNotFoundException {

        Car car = carRepository
                .findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found on :: " + carId)); //이런식으로 에러 처리 하는구나
        return ResponseEntity.ok().body(car); //REST controller의 GET response는 이런식으로 보내는구나
    }

    // POST Method for Create operation
    @PostMapping("/cars")
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    // PUT Method for Update operation
    @PutMapping("/cars/{id}")
    @Transactional
    public ResponseEntity<Car> updateCar(
            @PathVariable(value = "id") Long carId, @RequestBody Car carDetails)
            throws ResourceNotFoundException {
        Car car = carRepository
                .findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car " + carId + " not found"));

        car.setCarName(carDetails.getCarName());
        car.setDoors(carDetails.getDoors());

        final Car updatedCar = carRepository.save(car); //goal of final: immutable. final은 엔티티를 한번만 할당함. 두번 이상 할당하면 컴파일에러. 클래스에 붙이면 상속 못함(=자식에서 오버라이드 제한).
        return ResponseEntity.ok(updatedCar);
    }

    // DELETE Method for Delete operation
    @DeleteMapping("/car/{id}")
    public Map<String, Boolean> deleteCar(@PathVariable(value = "id") Long carId) throws Exception {
        Car car = carRepository
                .findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car " + carId + " not found"));

        carRepository.delete(car);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
