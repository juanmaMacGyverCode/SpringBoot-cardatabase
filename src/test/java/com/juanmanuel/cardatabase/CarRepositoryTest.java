package com.juanmanuel.cardatabase;

import com.juanmanuel.cardatabase.domain.Car;
import com.juanmanuel.cardatabase.domain.CarRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

// Tests centrados solo en la capa JPA, Hibernate y Spring Data
@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void saveCar() {
        Car car = new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000);
        entityManager.persistAndFlush(car);
        assertThat(car.getId()).isNotNull();
    }

    @Test
    public void deleteCars() {
        entityManager.persistAndFlush(Car.builder()
                .brand("Tesla")
                .model("Model X")
                .color("White")
                .registerNumber("ABC-1234")
                .year(2017)
                .price(86000)
                .build());
        entityManager.persistAndFlush(Car.builder()
                .brand("Mini")
                .model("Cooper")
                .color("Yellow")
                .registerNumber("BWS-3007")
                .year(2015)
                .price(24500)
                .build());
        carRepository.deleteAll();
        assertThat(carRepository.findAll()).isEmpty();
    }
}
