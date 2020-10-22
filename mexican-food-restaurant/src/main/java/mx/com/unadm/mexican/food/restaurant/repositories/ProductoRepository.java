/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant.repositories;

import java.util.List;
import mx.com.unadm.mexican.food.restaurant.models.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que define e implementa los metodos para persistencia de datos de las entidades Producto
 * @author Josue Palemon Perez
 * @version 1.0
 */
@Repository
public interface ProductoRepository extends CrudRepository<Producto, String>{
 
    @Query("SELECT p FROM Producto p WHERE p.codigoProducto LIKE :codigo%")
    List<Producto> findProductoByNombre(@Param("codigo") String codigo);
//    List<Producto> findByProductoStartsWith(String nombre);
    
}
