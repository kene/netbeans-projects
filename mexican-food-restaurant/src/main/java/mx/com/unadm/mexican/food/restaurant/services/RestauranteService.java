/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mx.com.unadm.mexican.food.restaurant.exceptions.NonexistentEntityException;
import mx.com.unadm.mexican.food.restaurant.models.Restaurante;
import mx.com.unadm.mexican.food.restaurant.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Clase RestauranteDao que se encarga de manejar las solicitudes para 
 * insertar, eliminar, recuperar  y actualizar una entidad Restaurante 
 * @author Josue Palemon Perez
 * @version 1.0
 */
@Service
public class RestauranteService {
    
    
    @Autowired
    private RestauranteRepository repository;
    private static final Logger LOGGER = LogManager.getLogger(RestauranteService.class);
    private String mensaje = "";
    
    /**
     * Metodo que crea un nuevo restaurante en la base de datos
     * @param restaurante entidad que se va a registrar en la base de datos
     * @return cada con el mensaje si se creo corretamente o no la entidad 
     */
    public String createRestaurante(Restaurante restaurante){
        try {
            repository.save(restaurante);
            mensaje = "Guardado correctamente";
        } catch (Exception e) {
            LOGGER.info("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se guardo la informacion";
        }

        return mensaje;
    }
    
    /**
     * Metodo que busca un restaurante por su codigo
     * @param codigoRestaurante codigo del restaurante
     * @return Restaurante entidad encontrada en la base de datos
     * @throws NonexistentEntityException Si no se encuentra la entidad Restaurante 
     *         en la base de datos, lanza una exeception
     */
    public Restaurante findRestauranteByCodigo(String codigoRestaurante) throws NonexistentEntityException{
        Optional<Restaurante> value = repository.findById(codigoRestaurante);
        
        if(!value.isPresent()){
            throw new NonexistentEntityException("No se encontro el restaurante con codigo: " + codigoRestaurante);
        }
        
        return value.get();
        
    }
    
    /**
     * Metodo que elimina una entidad Restaurante de la base de datos
     * @param restaurante entidad que sera eliminada en la base de datos
     * @return String cadena que indica si se elimino correctamente la entidad o no.
     */
    public String deleteRestaurante(Restaurante restaurante){
        
        try {
            repository.delete(restaurante);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            LOGGER.error("Mensaje eliminar: " + e.getMessage());
            mensaje = "No se elimino la informacion \n" +  e.getMessage();
        }
        
        return mensaje;
    }
    
    /**
     * Metodo que elimina una entidad Restaurante por sus codigo
     * @param codigoRestaurante codigo del restaurante 
     * @return String cadena que indica si se elimino correctamente 
     */
    public String  deleteRestauranteByCodigoRestaurante(String codigoRestaurante){
        
        try {
            repository.deleteById(codigoRestaurante);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            LOGGER.error("Mensaje eliminar: " + e.getMessage());
            mensaje = "No se elimino la informacion del restaurante con el codigo: " + codigoRestaurante  + ", error: " +  e.getMessage();
        }
        
        return mensaje;
    }
    
    
    /**
     * Metodo que actualiza los datos de una entidad Restaurante
     * @param restaurante entidad que se actualizara 
     * @return String caden que indica si los datos fueron actualizados correctamente o no
     */
    public String updateRestaurante(Restaurante restaurante){
        
        try {
            repository.save(restaurante);
            mensaje = "Actualizado correctamente";
        } catch (Exception e) {
            LOGGER.error("Mensaje en actualizar: " + e.getMessage());
            mensaje = "No se actualizo la informacion \n" + e.getMessage();
        }
        
        return mensaje;
        
    }
    
    
    /**
     * Metodo que recupera todas las entidades restaurante
     * @return List lista con todas las entidades Restaurante
     */
    public List<Restaurante> getAllRestaurantes(){
        List<Restaurante> list = new ArrayList<>(); 
        Iterable<Restaurante> iterable = repository.findAll();
        iterable.forEach(list:: add);
        return list;
    }
    
    
    public void listarRestaurantesTabla(JTable tabla, String nombre) {
        DefaultTableModel model;
        String[] HEADERS = {"CODIGO", "NOMBRE", "TELEFONO", "DIRECCION",
            "COLONIA", "CODIGO POSTAL", "CIUDAD", "PAIS"};
        model = new DefaultTableModel(null, HEADERS);

        List<Restaurante> data = repository.findRestauranteByNombre(nombre);
        String[] datosRestaurante = new String[8];

        for (Restaurante oRestaurante : data) {
            datosRestaurante[0] = oRestaurante.getCodigoRestaurante() + "";
            datosRestaurante[1] = oRestaurante.getNombre() + "";
            datosRestaurante[2] = oRestaurante.getTelefono() + "";
            datosRestaurante[3] = oRestaurante.getDireccion() + "";
            datosRestaurante[4] = oRestaurante.getColonia() + "";
            datosRestaurante[5] = oRestaurante.getCodigoPostal() + "";
            datosRestaurante[6] = oRestaurante.getCiudad() + "";
            datosRestaurante[7] = oRestaurante.getPais() + "";
            model.addRow(datosRestaurante);
        }

        tabla.setModel(model);
        model.fireTableDataChanged();
    }
    
    
    public boolean validarCodigo(String codigo) {
        boolean noFound = true;
        Optional<Restaurante> value = repository.findById(codigo);
        
        if(!value.isPresent()){
            noFound = false;
        }
        
        return noFound;
    }
    
}
