/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mx.com.unadm.mexican.food.restaurant.exceptions.NonexistentEntityException;
import mx.com.unadm.mexican.food.restaurant.models.Producto;
import mx.com.unadm.mexican.food.restaurant.models.Restaurante;
import mx.com.unadm.mexican.food.restaurant.services.ProductoService;
import mx.com.unadm.mexican.food.restaurant.services.RestauranteService;
import mx.com.unadm.mexican.food.restaurant.views.FrmProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Clase que maneja los modelos y vistas para los productos
 * @author Josue Palemon Perez
 * @version 1.0
 */
@Controller
public class ProductoController {
    
    
    @Autowired
    private ProductoService service;
    @Autowired
    private RestauranteService restService;
    @Autowired
    private FrmProducto view;

    public ProductoController(){ }

    /**
     * Metodo que recupera los campos de la tabla para llenar los campos de texto
     * @param evt evento que se ejeuta al realizar el clic sobre una fila de la tabla
     */
    public void selectedRowProductoTabla(MouseEvent evt) {
        int selected = view.getTblProductoData().getSelectedRow();
        view.getTxtCodigo().setText(view.getTblProductoData().getValueAt(selected, 0) + "");
        view.getTxtNombre().setText(view.getTblProductoData().getValueAt(selected, 1) + "");
        view.getTxtDescripcion().setText(view.getTblProductoData().getValueAt(selected, 2) + "");
        view.getTxtCantidad().setText(view.getTblProductoData().getValueAt(selected, 3) + "");
        view.getTxtPrecio().setText(view.getTblProductoData().getValueAt(selected, 4) + "");
    }
    
    /**
     * Metodo que guarda un producto al ejecutar el evento el bton
     * @param evt evento que se ejecuta al presionar el boton guardar
     * @throws NonexistentEntityException excepcion que se ejecuta al no existir 
     *      la funcion con la que se relaziona
     */
    public void guardarProducto(ActionEvent evt) throws NonexistentEntityException{
        if (view.getTxtCodigo().getText().equals("")
            || view.getTxtNombre().getText().equals("")
            || view.getTxtDescripcion().getText().equals("")
            || view.getTxtCantidad().getText().equals("")
            || view.getTxtPrecio().getText().equals("")){
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        } else {
            String codigo = view.getTxtCodigo().getText();
            String nombre = view.getTxtNombre().getText();
            String descripcion = view.getTxtDescripcion().getText();
            String cantidad = view.getTxtCantidad().getText();
            String strPrecio = view.getTxtPrecio().getText();
            BigDecimal precio = new BigDecimal(strPrecio);

            Producto p = new Producto(codigo, nombre, descripcion, cantidad, precio);
            Restaurante r = restService.findRestauranteByCodigo("R001");
            p.setCodigoRestaurante(r);
            String mensaje = service.createProducto(p);
            
            JOptionPane.showMessageDialog(null, mensaje);
            view.mostrarTabla("");
            view.limpiarCampos();

        }

    }
    
    /**
     * Metodo que modifica un producto existen en la base de datos
     * @param evt evento que se ejecuta al presionar el boton modificar
     * @throws NonexistentEntityException excepcion que es lanzada cuando no 
     *  se encuentra la entidad con la que se relaciona el producto
     */
    public void modificarProducto(ActionEvent evt) throws NonexistentEntityException {
        if (view.getTxtCodigo().getText().equals("")
            || view.getTxtNombre().getText().equals("")
            || view.getTxtDescripcion().getText().equals("")
            || view.getTxtCantidad().getText().equals("")
            || view.getTxtPrecio().getText().equals("")){
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        } else {
            String codigo = view.getTxtCodigo().getText();
            String nombre = view.getTxtNombre().getText();
            String descripcion = view.getTxtDescripcion().getText();
            String cantidad = view.getTxtCantidad().getText();
            String strPrecio = view.getTxtPrecio().getText();
            BigDecimal precio = new BigDecimal(strPrecio);
            
            Producto p = new Producto(codigo, nombre, descripcion, cantidad, precio);
            
            
            Restaurante r = restService.findRestauranteByCodigo("R001");
            p.setCodigoRestaurante(r);
            String mensaje = service.updateProducto(p);
            
            JOptionPane.showMessageDialog(null, mensaje);
            view.mostrarTabla("");
            view.limpiarCampos();
        } 

    }

    /**
     * Metodo que elimina un producto de la base de datos
     * @param evt evento que se ejecuta al presionar el boton eliminar 
     */
    public void eliminarProducto(ActionEvent evt) {
        if (view.getTxtCodigo().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El codigo es obligatorio");
        } else {
            String codigo = view.getTxtCodigo().getText();
            String mensaje = service.deleteProductoByCodigoProducto(codigo);
            JOptionPane.showMessageDialog(null, mensaje);
            view.mostrarTabla("");
            view.limpiarCampos();
        }
    }
    
    /**
     * Metodo que lista los productos recuperados de la base de datos en un JTable
     * @param tabla objeto Jtable que va a llenar on la informacion
     * @param nombre parametro de consulta para el modelo JTable
     */
    public void listarProductosTabla(JTable tabla, String nombre){
        service.listarProductosTabla(tabla, nombre);   
    }
    
    /**
     * Metodo que busca un producto por su codigo y la informacion
     * recuperada la envia a la vista
     */
    public void buscarProductoPorCodigo(){
        if(view.getTxtCodigo().getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo de codigo esta vacio");
        }else{

            boolean encontrado = service.validarCodigo(view.getTxtCodigo().getText());
            
            if(encontrado){    
                try{
                    Producto producto = service.findProductoByCodigo(view.getTxtCodigo().getText());
                    view.getTxtCodigo().setText(producto.getCodigoProducto());
                    view.getTxtNombre().setText(producto.getNombre());
                    view.getTxtDescripcion().setText(producto.getDescripcion());
                    view.getTxtCantidad().setText(producto.getCantidad());
                    view.getTxtPrecio().setText("" + producto.getPrecio());

                    JOptionPane.showMessageDialog(null, "Busqueda exitosa");
                }catch(NonexistentEntityException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }else{
                JOptionPane.showMessageDialog(null, "El codigo: " + view.getTxtCodigo().getText() + " no existe");
                view.limpiarCampos();
            }
        }
    }
 
    /**
     * Metodo que busca un producto por nombre 
     * la informacion recupera la envia a la vista a llenar los 
     * componentes de texto
     */
    public void buscarProductoPorNombre(){
        if(view.getTxtNombre().getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo de nombre esta vacio");
            view.mostrarTabla("");
        }else{
            view.mostrarTabla(view.getTxtNombre().getText());
        }
    }
    
}
