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
import mx.com.unadm.mexican.food.restaurant.models.Empleado;
import mx.com.unadm.mexican.food.restaurant.repositories.EmpleadoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que se encarga de manejar las solicitudes para insertar,
 * eliminar, actualizar y recuperar las entidades de Empleado
 * @author Josue Palemon Perez
 * @version 1.0
 * @since 1.0
 */
@Service
public class EmpleadoService {
    
    @Autowired
    private EmpleadoRepository repository;
    private static final Logger LOGGER = LogManager.getLogger(EmpleadoService.class);
    private String mensaje = "";
    
    /**
     * Metodo que crea una nueva entidad Empleado en la base de datos
     * @param empleado entidad que se va a registrar en la base de datos
     * @return cada con el mensaje si se creo corretamente o no la entidad 
     */
    public String createEmpleado(Empleado empleado){
        try {
            repository.save(empleado);
            mensaje = "Guardado correctamente";
        } catch (Exception e) {
            LOGGER.info("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se guardo la informacion";
        }

        return mensaje;
    }
    
    /**
     * Metodo que busca un empleado por su numero
     * @param numeroEmpleado numero del empleado
     * @return Empleado entidad encontrada en la base de datos
     * @throws NonexistentEntityException Si no se encuentra la entidad Producto 
     *         en la base de datos, lanza una exeception
     */
    public Empleado findEmpleadoByNumero(Integer numeroEmpleado) throws NonexistentEntityException{
        Optional<Empleado> value = repository.findById(numeroEmpleado);
        
        if(!value.isPresent()){
            throw new NonexistentEntityException("No se encontro el empleado con el numero: " + numeroEmpleado);
        }
        
        return value.get();
        
    }
    
    /**
     * Metodo que elimina una entidad Empleado de la base de datos
     * @param empleado entidad que sera eliminada en la base de datos
     * @return String cadena que indica si se elimino correctamente la entidad o no.
     */
    public String deleteEmpleado(Empleado empleado){
        
        try {
            repository.delete(empleado);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            LOGGER.error("Mensaje eliminar: " + e.getMessage());
            mensaje = "No se elimino la informacion \n" +  e.getMessage();
        }
        
        return mensaje;
    }
    
    /**
     * Metodo que elimina una entidad Empleado por su numero
     * @param numeroEmpleado numero del empleado
     * @return String cadena que indica si se elimino correctamente 
     */
    public String  deleteEmpleadoByNumero(Integer numeroEmpleado){
        
        try {
            repository.deleteById(numeroEmpleado);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            LOGGER.error("Mensaje eliminar: " + e.getMessage());
            mensaje = "No se elimino la informacion del empleado  con el numero: " + numeroEmpleado  + ", error: " +  e.getMessage();
        }
        
        return mensaje;
    }
    
    
    /**
     * Metodo que actualiza los datos de una entidad Empleado
     * @param empleado entidad que se actualizara 
     * @return String caden que indica si los datos fueron actualizados correctamente o no
     */
    public String updateEmpleado(Empleado empleado){
        
        try {
            repository.save(empleado);
            mensaje = "Actualizado correctamente";
        } catch (Exception e) {
            LOGGER.error("Mensaje en actualizar: " + e.getMessage());
            mensaje = "No se actualizo la informacion \n" + e.getMessage();
        }
        
        return mensaje;
        
    }
    
    
    /**
     * Metodo que recupera todas las entidades Empleado
     * @return List lista con todas las entidades Producto
     */
    public List<Empleado> getAllEmpleados(){
        List<Empleado> list = new ArrayList<>(); 
        Iterable<Empleado> iterable = repository.findAll();
        iterable.forEach(list:: add);
        return list;
    }
    

    public void listarEmpleadosTabla(JTable tabla, String nombre) {
        DefaultTableModel model;
        String[] HEADERS = {"NUMERO", "RFC", "CURP", "NOMBRE", "AP", "AM", "CORREO",
            "TELEFONO", "DIRECCION", "SALARIO"};
        model = new DefaultTableModel(null, HEADERS);

        List<Empleado> data = repository.findEmpleadoByNombre(nombre);
        String[] datosEmpleado = new String[10];

        for (Empleado oEmpleado : data) {
            datosEmpleado[0] = oEmpleado.getNumeroEmpleado() + "";
            datosEmpleado[1] = oEmpleado.getRfc() + "";
            datosEmpleado[2] = oEmpleado.getCurp() + "";
            datosEmpleado[3] = oEmpleado.getNombre() + "";
            datosEmpleado[4] = oEmpleado.getApellidoPaterno() + "";
            datosEmpleado[5] = oEmpleado.getApellidoMaterno() + "";
            datosEmpleado[6] = oEmpleado.getCorreoElectronico() + "";
            datosEmpleado[7] = oEmpleado.getTelefono() + "";
            datosEmpleado[8] = oEmpleado.getDireccion() + "";
            datosEmpleado[9] = oEmpleado.getSalario() + "";
            
            model.addRow(datosEmpleado);
        }

        tabla.setModel(model);
        model.fireTableDataChanged();
    }
    
    
    public boolean validarNumero(Integer numero) {
        boolean noFound = true;
        Optional<Empleado> value = repository.findById(numero);
        
        if(!value.isPresent()){
            noFound = false;
        }
        
        return noFound;
    }
    
    
    public Empleado findEmpleadoByCodigo(Integer numero) throws NonexistentEntityException{
        Optional<Empleado> value = repository.findById(numero);
        if(!value.isPresent()){
            throw new NonexistentEntityException("No se encontro el empleado con numero: " + numero);
        }
        
        return value.get();
    }


    
}
