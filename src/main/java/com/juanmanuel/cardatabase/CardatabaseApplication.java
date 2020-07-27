package com.juanmanuel.cardatabase;

import com.juanmanuel.cardatabase.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CardatabaseApplication {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private OwnerRepository ownerRepository;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			// Add owner objects and save these to db
			Owner owner1 = Owner.builder().firstname("John").lastname("Johnson").build();
			Owner owner2 = Owner.builder().firstname("Mary").lastname("Robinson").build();
			ownerRepository.save(owner1);
			ownerRepository.save(owner2);

			// Save demo data to database
			carRepository.save(
					Car.builder().brand("Ford")
							.model("Mustang")
							.color("Red")
							.registerNumber("ADF-1121")
							.year(2017)
							.price(59000)
							.owner(owner1)
							.build()
			);

			carRepository.save(
					Car.builder().brand("Nissan")
							.model("Leaf")
							.color("White")
							.registerNumber("SSJ-3002")
							.year(2014)
							.price(29000)
							.owner(owner2)
							.build()
			);

			carRepository.save(
					Car.builder().brand("Toyota")
							.model("Prius")
							.color("Silver")
							.registerNumber("KKO-0212")
							.year(2018)
							.price(39000)
							.owner(owner2)
							.build()
			);

			// username: user #### password: user
			userRepository.save(new User("user", "$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi", "USER"));
			// username: admin #### password: admin
			userRepository.save(new User("admin", "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG", "ADMIN"));
		};
	}


}
