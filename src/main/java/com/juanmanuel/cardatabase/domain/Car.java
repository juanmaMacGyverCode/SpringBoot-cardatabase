package com.juanmanuel.cardatabase.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity // Esta es la tabla SQL
public class Car {
    @Id // Clave primaria de la aplicación
    @GeneratedValue(strategy = GenerationType.AUTO)     //El valor AUTO_INCREMENT de SQL
    private long id;
    private String brand;
    private String model;
    private String color;
    private String registerNumber;
    private int year;
    private int price;

    public Car(String brand, String model, String color,
               String registerNumber, int year, int price){
        super();
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
    }


}
