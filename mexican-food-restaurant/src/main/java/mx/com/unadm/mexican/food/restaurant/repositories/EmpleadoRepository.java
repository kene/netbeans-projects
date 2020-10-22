/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant.repositories;

import java.util.List;
import mx.com.unadm.mexican.food.restaurant.models.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
/**
 * Interfaz que define e implementa los metodos para persistencia de datos de las entidades Empleado
 * @author Josue Palemon Perez
 * @version 1.0
 */
@Repository
public interface EmpleadoRepository  extends CrudRepository<Empleado, Integer>{
    
    @Query("SELECT e FROM Empleado e WHERE e.nombre LIKE :nombre%")
    List<Empleado> findEmpleadoByNombre(@Param("nombre") String nombre);
//    List<Empleado> findByEmpleadoStartsWith(String nombre);
    
}
