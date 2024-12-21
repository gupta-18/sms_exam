package com.mkipts.bank.repository;

import com.mkipts.bank.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
    public  City findCityByName(String name);
}
