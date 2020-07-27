package com.juanmanuel.cardatabase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity // Esta es la tabla SQL
// Ignora las propiedades lógicas especificadas en la serialización y deserialización JSON. Está anotado a nivel de clase
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ownerid;
    private String firstname;
    private String lastname;

    // El cascade define como afecta el efecto cascada a las entidades.
    // El atributo ALL quiere decir que si el propietario es borrado, el coche
    // también lo hará.
    // El mappedBy="owner", nos dice que el CAR class tiene un campo de propietario,
    // que a su vez esa es su foreign key de la relación
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    // se utiliza para ignorar la propiedad lógica utilizada en la serialización y deserialización.
    @JsonIgnore
    private List<Car> cars;

    // Como hacer una relación ManyToMany
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="car_owner", joinColumns = { @JoinColumn(name = "ownerid")},
            inverseJoinColumns = {@JoinColumn(name = "carid")})
    private Set<Car> carsManyMany = new HashSet<Car>(0);

    public Owner(String firstname, String lastname){
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
