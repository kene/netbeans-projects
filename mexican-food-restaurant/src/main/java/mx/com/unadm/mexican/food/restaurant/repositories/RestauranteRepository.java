/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant.repositories;

import java.util.List;
import mx.com.unadm.mexican.food.restaurant.models.Restaurante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que define e implementa los metodos para persistencia de datos de las entidades Restaurante
 * @author Josue Palemon Perez Betanzos
 * @version 1.0
 */
@Repository
public interface RestauranteRepository extends CrudRepository<Restaurante, String>{
    
    @Query("SELECT r FROM Restaurante r WHERE r.nombre LIKE :nombre%")
    List<Restaurante> findRestauranteByNombre(@Param("nombre") String nombre);
    
//    List<Restaurante> findByRestauranteStartsWith(String nombre);
    
}
