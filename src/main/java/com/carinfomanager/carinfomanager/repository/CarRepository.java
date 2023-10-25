package com.carinfomanager.carinfomanager.repository;

import com.carinfomanager.carinfomanager.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
