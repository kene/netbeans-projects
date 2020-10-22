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
import mx.com.unadm.mexican.food.restaurant.models.Producto;
import mx.com.unadm.mexican.food.restaurant.repositories.ProductoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase ProductoDao que se encarga de manejar las solicitudes
 * de eliminar, actualizar, insertar, y recuperar una entidad Producto
 * @author Josue Palemon Perez
 * @version 1.0
 */
@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository repository;
    private static final Logger LOGGER = LogManager.getLogger(ProductoService.class);
    private String mensaje = "";
    
    /**
     * Metodo que crea una nueva entidad Producto en la base de datos
     * @param producto entidad que se va a registrar en la base de datos
     * @return cada con el mensaje si se creo corretamente o no la entidad 
     */
    public String createProducto(Producto producto){
        try {
            repository.save(producto);
            mensaje = "Guardado correctamente";
        } catch (Exception e) {
            LOGGER.info("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se guardo la informacion";
        }

        return mensaje;
    }
    
    /**
     * Metodo que busca un producto por su codigo
     * @param codigoProducto codigo del producto
     * @return Producto entidad encontrada en la base de datos
     * @throws NonexistentEntityException Si no se encuentra la entidad Producto 
     *         en la base de datos, lanza una exeception
     */
    public Producto findRestauranteByCodigoProducto(String codigoProducto) throws NonexistentEntityException{
        Optional<Producto> value = repository.findById(codigoProducto);
        
        if(!value.isPresent()){
            throw new NonexistentEntityException("No se encontro el producto con codigo: " + codigoProducto);
        }
        
        return value.get();
        
    }
    
    /**
     * Metodo que elimina una entidad Producto de la base de datos
     * @param producto entidad que sera eliminada en la base de datos
     * @return String cadena que indica si se elimino correctamente la entidad o no.
     */
    public String deleteProducto(Producto producto){
        
        try {
            repository.delete(producto);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            LOGGER.error("Mensaje eliminar: " + e.getMessage());
            mensaje = "No se elimino la informacion \n" +  e.getMessage();
        }
        
        return mensaje;
    }
    
    /**
     * Metodo que elimina una entidad Producto por sus codigo
     * @param codigoProducto codigo del producto 
     * @return String cadena que indica si se elimino correctamente 
     */
    public String  deleteProductoByCodigoProducto(String codigoProducto){
        
        try {
            repository.deleteById(codigoProducto);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            LOGGER.error("Mensaje eliminar: " + e.getMessage());
            mensaje = "No se elimino la informacion del producto con el codigo: " + codigoProducto  + ", error: " +  e.getMessage();
        }
        
        return mensaje;
    }
    
    
    /**
     * Metodo que actualiza los datos de una entidad Producto
     * @param producto entidad que se actualizara 
     * @return String caden que indica si los datos fueron actualizados correctamente o no
     */
    public String updateProducto(Producto producto){
        
        try {
            repository.save(producto);
            mensaje = "Actualizado correctamente";
        } catch (Exception e) {
            LOGGER.error("Mensaje en actualizar: " + e.getMessage());
            mensaje = "No se actualizo la informacion \n" + e.getMessage();
        }
        
        return mensaje;
        
    }
    
    
    /**
     * Metodo que recupera todas las entidades Producto
     * @return List lista con todas las entidades Producto
     */
    public List<Producto> getAllProductos(){
        List<Producto> list = new ArrayList<>(); 
        Iterable<Producto> iterable = repository.findAll();
        iterable.forEach(list:: add);
        return list;
    }
    
    
    public void listarProductosTabla(JTable tabla, String codigo) {
        DefaultTableModel model;
        String[] HEADERS = {"CODIGO", "NOMBRE", "DESCRIPCION", "CANTIDAD", "PRECIO"};
        model = new DefaultTableModel(null, HEADERS);

        List<Producto> data = repository.findProductoByNombre(codigo);
        String[] datosProducto = new String[8];

        for (Producto oProducto : data) {
            datosProducto[0] = oProducto.getCodigoProducto() + "";
            datosProducto[1] = oProducto.getNombre() + "";
            datosProducto[2] = oProducto.getDescripcion() + "";
            datosProducto[3] = oProducto.getCantidad() + "";
            datosProducto[4] = oProducto.getPrecio() + "";
            model.addRow(datosProducto);
        }

        tabla.setModel(model);
        model.fireTableDataChanged();
    }
    
    
    public boolean validarCodigo(String codigo) {
        boolean noFound = true;
        Optional<Producto> value = repository.findById(codigo);
        
        if(!value.isPresent()){
            noFound = false;
        }
        
        return noFound;
    }
    
    
    public Producto findProductoByCodigo(String codigo) throws NonexistentEntityException{
        Optional<Producto> value = repository.findById(codigo);
        if(!value.isPresent()){
            throw new NonexistentEntityException("No se encontro el producto con codigo: " + codigo);
        }
        
        return value.get();
    }
    
    
}
