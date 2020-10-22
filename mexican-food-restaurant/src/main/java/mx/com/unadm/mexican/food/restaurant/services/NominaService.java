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
import mx.com.unadm.mexican.food.restaurant.models.Nomina;
import mx.com.unadm.mexican.food.restaurant.repositories.NominaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que se encarga de manejar las solicitudes para insertar,
 * eliminar, actualizar y recuperar las entidades Nomina
 * @author Josue Palemon Perez
 * @version 1.0
 */

@Service
public class NominaService {
    
    @Autowired
    private NominaRepository repository;
    private static final Logger LOGGER = LogManager.getLogger(NominaService.class);
    private String mensaje = "";
    
    /**
     * Metodo que crea una nueva entidad Nomina en la base de datos
     * @param nomina entidad que se va a registrar en la base de datos
     * @return cada con el mensaje si se creo corretamente o no la entidad 
     */
    public String createNomina(Nomina nomina){
        try {
            repository.save(nomina);
            mensaje = "Guardado correctamente";
        } catch (Exception e) {
            LOGGER.info("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se guardo la informacion";
        }

        return mensaje;
    }
    
    /**
     * Metodo que busca una nomina por su numero
     * @param numeroNomina numero de la nomina
     * @return Nomina entidad encontrada en la base de datos
     * @throws NonexistentEntityException Si no se encuentra la entidad Producto 
     *         en la base de datos, lanza una exeception
     */
    public Nomina findNominaByNumero(Integer numeroNomina) throws NonexistentEntityException{
        Optional<Nomina> value = repository.findById(numeroNomina);
        
        if(!value.isPresent()){
            throw new NonexistentEntityException("No se encontro la nomina con el numero de nomina: " + numeroNomina);
        }
        
        return value.get();
        
    }
    
    /**
     * Metodo que elimina una entidad Nomina de la base de datos
     * @param nomina entidad que sera eliminada en la base de datos
     * @return String cadena que indica si se elimino correctamente la entidad o no.
     */
    public String deleteNomina(Nomina nomina){
        
        try {
            repository.delete(nomina);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            LOGGER.error("Mensaje eliminar: " + e.getMessage());
            mensaje = "No se elimino la informacion \n" +  e.getMessage();
        }
        
        return mensaje;
    }
    
    /**
     * Metodo que elimina una entidad Nomina por su numero
     * @param numeroNomina numero de la nomina
     * @return String cadena que indica si se elimino correctamente 
     */
    public String  deleteNominaByNumero(Integer numeroNomina){
        
        try {
            repository.deleteById(numeroNomina);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            LOGGER.error("Mensaje eliminar: " + e.getMessage());
            mensaje = "No se elimino la informacion de la nomina  con el numero: " + numeroNomina  + ", error: " +  e.getMessage();
        }
        
        return mensaje;
    }
    
    
    /**
     * Metodo que actualiza los datos de una entidad Nomina
     * @param nomina entidad que se actualizara 
     * @return String caden que indica si los datos fueron actualizados correctamente o no
     */
    public String updateNomina(Nomina nomina){
        
        try {
            repository.save(nomina);
            mensaje = "Actualizado correctamente";
        } catch (Exception e) {
            LOGGER.error("Mensaje en actualizar: " + e.getMessage());
            mensaje = "No se actualizo la informacion \n" + e.getMessage();
        }
        
        return mensaje;
        
    }
    
    
    /**
     * Metodo que recupera todas las entidades Nomina
     * @return List lista con todas las entidades Producto
     */
    public List<Nomina> getAllNominas(){
        List<Nomina> list = new ArrayList<>(); 
        Iterable<Nomina> iterable = repository.findAll();
        iterable.forEach(list:: add);
        return list;
    }

    public void listarNominasTabla(JTable tabla, String razonSocial) {
        DefaultTableModel model;
        String[] HEADERS = {"NUMERO", "RAZON SOCIAL", "DOMICILIO", "FECHA", "INICIO", "FIN", "DIAS"};
        model = new DefaultTableModel(null, HEADERS);

        List<Nomina> data = repository.findNominaByRazonSocial(razonSocial);
        String[] datosNomina = new String[8];

        for (Nomina oNomina : data) {
            datosNomina[0] = oNomina.getNumeroNomina() + "";
            datosNomina[1] = oNomina.getRazonSocial() + "";
            datosNomina[2] = oNomina.getDomicilio() + "";
            datosNomina[3] = oNomina.getFecha() + "";
            datosNomina[4] = oNomina.getInicioPeriodo() + "";
            datosNomina[5] = oNomina.getFinPeriodo() + "";
            datosNomina[6] = oNomina.getDias() + "";
            model.addRow(datosNomina);
        }

        tabla.setModel(model);
        model.fireTableDataChanged();
    }

    public boolean validarNumero(Integer numero) {
        boolean noFound = true;
        Optional<Nomina> value = repository.findById(numero);
        
        if(!value.isPresent()){
            noFound = false;
        }
        
        return noFound;
    }
    
    
    
    public Nomina findNominaByCodigo(Integer numeroNomina)throws NonexistentEntityException{
        Optional<Nomina> value = repository.findById(numeroNomina);
        
        if(!value.isPresent()){
            throw new NonexistentEntityException("No se encontro el numero de nomina: " + numeroNomina);
        }
        
        return value.get();
    }
}
