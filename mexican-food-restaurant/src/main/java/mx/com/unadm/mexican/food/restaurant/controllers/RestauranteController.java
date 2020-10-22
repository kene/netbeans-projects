/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mx.com.unadm.mexican.food.restaurant.exceptions.NonexistentEntityException;
import mx.com.unadm.mexican.food.restaurant.models.Restaurante;
import mx.com.unadm.mexican.food.restaurant.services.RestauranteService;
import mx.com.unadm.mexican.food.restaurant.views.FrmRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *  Clase que maneja el control entre el modelo y las vistas para
 *  la entidad Restaurante
 * @author Josue Palemon Perez
 * @version 1.0
 * @since 1.0
 */
@Controller
public class RestauranteController {
    
    
    @Autowired
    private RestauranteService service;
    @Autowired
    private FrmRestaurante view;

    public RestauranteController(){ }

    /**
     * Metodo que recupera los datos de una fila dentro del JTable
     * @param evt evento que se ejecuta al dar clic en una fila de la tabla
     */
    public void selectedRowRestauranteTabla(MouseEvent evt) {
        int selected = view.getTblRestauranteData().getSelectedRow();
        view.getTxtCodigo().setText(view.getTblRestauranteData().getValueAt(selected, 0) + "");
        view.getTxtNombre().setText(view.getTblRestauranteData().getValueAt(selected, 1) + "");
        view.getTxtTelefono().setText(view.getTblRestauranteData().getValueAt(selected, 2) + "");
        view.getTxtDireccion().setText(view.getTblRestauranteData().getValueAt(selected, 3) + "");
        view.getTxtColonia().setText(view.getTblRestauranteData().getValueAt(selected, 4) + "");
        view.getTxtCodigoPostal().setText(view.getTblRestauranteData().getValueAt(selected, 5) + "");
        view.getTxtCiudad().setText(view.getTblRestauranteData().getValueAt(selected, 6) + "");
        view.getTxtPais().setText(view.getTblRestauranteData().getValueAt(selected, 7) + "");
    }
    
    /**
     * Metodo que guarda un restaurante en la base de datos, recupera los valores 
     * del restaurante de la vista
     * @param evt evento que se ejeuta al dar clic en el boton de guardar
     */
    public void guardarRestaurante(ActionEvent evt){
        if (view.getTxtCodigo().getText().equals("")
            || view.getTxtNombre().getText().equals("")
            || view.getTxtCiudad().getText().equals("")
            || view.getTxtTelefono().getText().equals("")
            || view.getTxtDireccion().getText().equals("")
            || view.getTxtCodigoPostal().getText().equals("")
            || view.getTxtColonia().getText().equals("")
            || view.getTxtPais().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        } else {
            String codigo = view.getTxtCodigo().getText();
            String nombre = view.getTxtNombre().getText();
            String ciudad = view.getTxtCiudad().getText();
            String telefono = view.getTxtTelefono().getText();
            String direccion = view.getTxtDireccion().getText();
            String codigoPostal =  view.getTxtCodigoPostal().getText();
            String colonia = view.getTxtColonia().getText();
            String pais = view.getTxtPais().getText();
            
            Restaurante r = new Restaurante(codigo, nombre, ciudad, telefono, direccion, pais, codigoPostal, colonia);
            
            String mensaje = service.createRestaurante(r);
            
            JOptionPane.showMessageDialog(null, mensaje);
            view.mostrarTabla("");
            view.limpiarCampos();

        }

    }
    
    /**
     * Metodo que modifica la informacion de un restaurante en la base de datos
     * @param evt evento que se ejecuta al presionar el boton de modificar
     */
    public void modificarRestaurante(ActionEvent evt) {
        if (view.getTxtCodigo().getText().equals("")
            || view.getTxtNombre().getText().equals("")
            || view.getTxtCiudad().getText().equals("")
            || view.getTxtTelefono().getText().equals("")
            || view.getTxtDireccion().getText().equals("")
            || view.getTxtCodigoPostal().getText().equals("")
            || view.getTxtColonia().getText().equals("")
            || view.getTxtPais().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        } else {
            String codigo = view.getTxtCodigo().getText();
            String nombre = view.getTxtNombre().getText();
            String ciudad = view.getTxtCiudad().getText();
            String telefono = view.getTxtTelefono().getText();
            String direccion = view.getTxtDireccion().getText();
            String codigoPostal =  view.getTxtCodigoPostal().getText();
            String colonia = view.getTxtColonia().getText();
            String pais = view.getTxtPais().getText();
            
            Restaurante r = new Restaurante(codigo, nombre, ciudad, telefono, direccion, pais, codigoPostal, colonia);
            
            String mensaje = service.updateRestaurante(r);
            JOptionPane.showMessageDialog(null, mensaje);
            view.mostrarTabla("");
            view.limpiarCampos();
        } 

    }

    /**
     * Elimina un restaurante de la base de datos
     * @param evt evento que se ejecuta al presionar el boton de eliminar
     */
    public void eliminarRestaurante(ActionEvent evt) {
        if (view.getTxtCodigo().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El codigo es obligatorio");
        } else {
            String codigo = view.getTxtCodigo().getText();
            String mensaje = service.deleteRestauranteByCodigoRestaurante(codigo);
            JOptionPane.showMessageDialog(null, mensaje);
//            mostrarTabla("");
            view.limpiarCampos();
        }
//        JOptionPane.showMessageDialog(null, "Boton eliminar");
    }
    
    /**
     * Metodo que lista los restaurantes que se encuentran en la base de datos
     *  en la vista por medio de JTable
     * @param tabla JTable que contendra la informacion de los restaurantes recuperados
     *      de la base de datos
     * @param nombre parametro para buscar los restaurantes por nombre
     */
    public void listarRestaurantesTabla(JTable tabla, String nombre){
        service.listarRestaurantesTabla(tabla, nombre);   
    }
    
    public void buscarRestaurantePorCodigo(){
        if(view.getTxtCodigo().getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo de codigo esta vacio");
        }else{

            boolean encontrado = service.validarCodigo(view.getTxtCodigo().getText());
            
            if(encontrado){    
                try{
                    Restaurante restaurante = service.findRestauranteByCodigo(view.getTxtCodigo().getText());
                    view.getTxtCodigo().setText(restaurante.getCodigoRestaurante());
                    view.getTxtCiudad().setText(restaurante.getCiudad());
                    view.getTxtCodigoPostal().setText(restaurante.getCodigoPostal());
                    view.getTxtColonia().setText(restaurante.getColonia());
                    view.getTxtDireccion().setText(restaurante.getDireccion());
                    view.getTxtNombre().setText(restaurante.getNombre());
                    view.getTxtPais().setText(restaurante.getPais());
                    view.getTxtTelefono().setText(restaurante.getTelefono());
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
     * Metodo que busca un restaurante por nombre y regresa la informacion a la vista
     */
    public void buscarRestaurantePorNombre(){
        if(view.getTxtNombre().getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo de nombre esta vacio");
            view.mostrarTabla("");
        }else{
            view.mostrarTabla(view.getTxtNombre().getText());
        }
    }
    
   
}
