package com.packt.cardatabase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository <Car, Long> {
	
	//Fetch cars by brand
	List<Car> findByBrand(String brand);
	
	//Fetch cars by color
	List<Car> findByColor(String Color);
	
	//Fetch cars by model year
	List<Car> findByModelYear(int modelYear);
	
	
}
