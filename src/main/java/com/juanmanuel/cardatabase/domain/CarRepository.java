package com.juanmanuel.cardatabase.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

// CrudRepository<Clase, literal del id>: Para hacer una consulta escribes "findBy" y luego de sufijo el nombre
// de la propiedad de la clase en cuestión a buscar en base de datos.
// También puedes extender a PaginationAndSortingRepository<Car, Long>:
// Esto extiende de CrudRepository. Oferta metodos para fetchear entidades usando
// paginación y ordenación.
// @RepositoryRestResource para dirigir Spring MVC para crear endpoints RESTful en /search (por defecto).
// Si quieres cambiar el endpoint usa por ejemplo: @RepositoryRestResource(collectionResourceRel = "people", path = "people")
// y pon http://localhost:8081/api/people para ver los coches. Y para ver las busquedas http://localhost:8081/api/people/search
@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Long> {
    // Fetch cars by brand
    List<Car> findByBrand(String brand);
    // Fetch cars by color
    List<Car> findByColor(String color);
    // Fetch cars by year
    List<Car> findByYear(int year);
    // Fetch cars by brand and model
    List<Car> findByBrandAndModel(String brand, String model);
    // Fetch cars by brand or color
    List<Car> findByBrandOrColor(String brand, String color);
    // Fetch cars by brand and sort by year
    List<Car> findByBrandOrderByYearAsc(String brand);
    // Fetch cars by brand using SQL
    @Query("select c from Car c where c.brand = ?1")
    List<Car> findByBrandQuery(String brand);
    // Fetch cars by brand using SQL
    @Query("select c from Car c where c.brand like %?1")
    List<Car> findByBrandEndsWith(String brand);
}
