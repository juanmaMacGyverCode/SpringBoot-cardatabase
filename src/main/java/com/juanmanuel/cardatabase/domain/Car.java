package com.juanmanuel.cardatabase.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    // Define relacion muchos a uno. La estrategía FetchType.LAZY,
    // significa que cuando el propietario recupera los datos de la base de datos,
    // todos los automoviles asociados con el propietario se recuperarán cuando sea necesario.
    // La estrategía FetchType.EAGER, significa ansioso,
    // consiste que los autos serán traídos inmediatamente con el propietario.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Owner owner;

    // Como hacer una relación ManyToMany
    @ManyToMany(mappedBy = "carsManyMany")
    private Set<Owner> owners;

    public Car(String brand, String model, String color,
               String registerNumber, int year, int price, Owner owner){
        super();
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
        this.owner = owner;
    }


}
