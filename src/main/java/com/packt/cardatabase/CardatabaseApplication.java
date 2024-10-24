package com.packt.cardatabase;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.packt.cardatabase.domain.AppUser;
import com.packt.cardatabase.domain.AppUserRepository;
import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
	private static final Logger logger =LoggerFactory.getLogger(CardatabaseApplication.class);
	
	private final CarRepository repository;
	private final OwnerRepository orepository;
	private final AppUserRepository urepository;
	//Car repository is injected into the main class
	public CardatabaseApplication(CarRepository repository,OwnerRepository orepository, AppUserRepository urepository) {
		this.repository = repository;
		this.orepository = orepository;
		this.urepository = urepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Application has started again");
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		
		//add owner objects to save to the database
		Owner owner1 = new Owner("Nick", "Johnson");
		Owner owner2 = new Owner("Solid", "Snake");
		orepository.saveAll(Arrays.asList(owner1, owner2));
		
		repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2023, 59000, owner1));
		repository.save(new Car("Nissan", "Leaf", "White", "SS3-3002", 2020, 29000, owner2));
		repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2022, 39000, owner2));
		
		//Fetch all of the cars and log the info
		for(Car car : repository.findAll()) {
			logger.info("brand: {}), model: {}",
					car.getBrand(), car.getModel());		
			}
		
		urepository.save(new AppUser("admin","$2a$12$Y/LYTiVEpUnr362jMMLshuT7j0NgwDbwoE2VE8jmIx1BRcuAGwFkq", "ADMIN"));
		urepository.save(new AppUser("user","$2a$12$n88VXrpU7EvJAsk/FqB.zO4lHiRXEpjvhQ4HKTKxQgKI7m4iubc0q", "USER"));
	}
	
	

}
