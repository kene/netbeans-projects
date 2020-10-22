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
import mx.com.unadm.mexican.food.restaurant.models.Empleado;
import mx.com.unadm.mexican.food.restaurant.models.Restaurante;
import mx.com.unadm.mexican.food.restaurant.services.EmpleadoService;
import mx.com.unadm.mexican.food.restaurant.services.RestauranteService;
import mx.com.unadm.mexican.food.restaurant.views.FrmEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Clase que controla las interacciones entre la vista y el modelo para Empleado
 * @author Josue Palemon Perez
 * @version 1.0
 */
// Anotacion Spring que define esta clase como controller 
@Controller
public class EmpleadoController {
    
    // injecta el objeto EmpleadoService 
    @Autowired
    private EmpleadoService service;
    // injecta el objeto FrmEmpleado
    @Autowired
    private FrmEmpleado view;
    // injecta el objeto RestauranteService
    @Autowired
    private RestauranteService restService;

    // Cada clase anotada debe tener un constructor vacio,
    // para ser instanciado por Spring
    public EmpleadoController(){ }

    /**
     * Metodo que recuperar los campos de la tabla Empleados
     * definida en la vista FrmEmpleado
     * @param evt evento MouseEvent que se ejecuta al seleccionar una fila
     */
    public void selectedRowEmpleadoTabla(MouseEvent evt) {
        int selected = view.getTblEmpleadoData().getSelectedRow();
        view.getTxtNumero().setText(view.getTblEmpleadoData().getValueAt(selected, 0) + "");
        view.getTxtRfc().setText(view.getTblEmpleadoData().getValueAt(selected, 1) + "");
        view.getTxtCurp().setText(view.getTblEmpleadoData().getValueAt(selected, 2) + "");
        view.getTxtNombre().setText(view.getTblEmpleadoData().getValueAt(selected, 3) + "");
        view.getTxtApellidoPaterno().setText(view.getTblEmpleadoData().getValueAt(selected, 4) + "");
        view.getTxtApellidoMaterno().setText(view.getTblEmpleadoData().getValueAt(selected, 5) + "");
        view.getTxtCorreoElectronico().setText(view.getTblEmpleadoData().getValueAt(selected, 6) + "");
        view.getTxtTelefono().setText(view.getTblEmpleadoData().getValueAt(selected, 7) + "");
        view.getTxtDireccion().setText(view.getTblEmpleadoData().getValueAt(selected, 8) + "");
        view.getTxtSalario().setText(view.getTblEmpleadoData().getValueAt(selected, 9) + "");
    }
    
    /**
     * Metodo que guarda un empleado, recupera los valores de la vista,
     * y por medio de un servicio  (EmpleadoService) ejecuta la consulta
     * para guardar la entidad Empleado
     * @param evt evento que se ejecuta al presionar el boton de guardar de 
     *            la vista FrmEmpleado
     * @throws NonexistentEntityException excepcion que es lanzada cuando no se encuentra 
     *         la entidad Restaurante relacinada con el Empleado
     */
    public void guardarEmpleado(ActionEvent evt) throws NonexistentEntityException {

        if ( view.getTxtRfc().getText().equals("")
            || view.getTxtCurp().getText().equals("")
            || view.getTxtNombre().getText().equals("")
            || view.getTxtApellidoPaterno().getText().equals("")
            || view.getTxtApellidoMaterno().getText().equals("")
            || view.getTxtCorreoElectronico().getText().equals("")
            || view.getTxtTelefono().getText().equals("")
            || view.getTxtDireccion().getText().equals("")
            || view.getTxtSalario().getText().equals("")){
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            
        } else {
           
            String rfc = view.getTxtRfc().getText();
            String curp = view.getTxtCurp().getText();
            String nombre = view.getTxtNombre().getText();
            String apellidoPaterno = view.getTxtApellidoPaterno().getText();
            String apellidoMaterno = view.getTxtApellidoMaterno().getText();
            String correoElectronico = view.getTxtCorreoElectronico().getText();
            String telefono = view.getTxtTelefono().getText();
            String direccion = view.getTxtDireccion().getText();
            String sSalario = view.getTxtSalario().getText();
            BigDecimal salario = new BigDecimal(sSalario);
            BigDecimal incremento = new BigDecimal(0.0);
                    
            Empleado e = new Empleado(rfc, curp, nombre, apellidoPaterno, 
                    apellidoMaterno, correoElectronico, telefono, direccion, salario, incremento);
            
            // TODO: Corregir para seleccionar un restaurante desde la vista
            Restaurante r = restService.findRestauranteByCodigo("R001");
            e.setCodigoRestaurante(r);
            String mensaje = service.createEmpleado(e);
            
            JOptionPane.showMessageDialog(null, mensaje);
            view.mostrarTabla("");
            view.limpiarCampos();

        }
    }
    
    
    /** 
     * Metodo que modifica los datos de una entidad Empleado
     * @param evt evento que se ejecuta al presionar el boton modificar
     * @throws NonexistentEntityException excepcion que es lanzada cuando no se
     *         encuentra la entidad Restaurante relacionada con la entidad Empleado
     */
    public void modificarEmpleado(ActionEvent evt) throws NonexistentEntityException {
        if (view.getTxtNumero().getText().equals("")
            || view.getTxtRfc().getText().equals("")
            || view.getTxtCurp().getText().equals("")
            || view.getTxtNombre().getText().equals("")
            || view.getTxtApellidoPaterno().getText().equals("")
            || view.getTxtApellidoMaterno().getText().equals("")
            || view.getTxtCorreoElectronico().getText().equals("")
            || view.getTxtTelefono().getText().equals("")
            || view.getTxtDireccion().getText().equals("")
            || view.getTxtSalario().getText().equals("")){
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            
        } else {
     
            String rfc = view.getTxtRfc().getText();
            String curp = view.getTxtCurp().getText();
            String nombre = view.getTxtNombre().getText();
            String apellidoPaterno = view.getTxtApellidoPaterno().getText();
            String apellidoMaterno = view.getTxtApellidoMaterno().getText();
            String correoElectronico = view.getTxtCorreoElectronico().getText();
            String telefono = view.getTxtTelefono().getText();
            String direccion = view.getTxtDireccion().getText();
            String sSalario = view.getTxtSalario().getText();
            BigDecimal salario = new BigDecimal(sSalario);
            BigDecimal incremento = new BigDecimal(0.0);

            Empleado e = new Empleado(rfc, curp, nombre, apellidoPaterno, 
                    apellidoMaterno, correoElectronico, telefono, direccion, salario, incremento);
            
            // TODO: Corregir para seleccionar un restaurante desde la vista
           Restaurante r = restService.findRestauranteByCodigo("R001");
            e.setCodigoRestaurante(r);
           String mensaje = service.updateEmpleado(e);
            
            JOptionPane.showMessageDialog(null, mensaje);
            view.mostrarTabla("");
            view.limpiarCampos();
        } 

    }

    
    /**
     * Metodo que se encarga de eliminar una entidad Empleado de la base de datos
     * @param evt evento que se ejecuta al presionar el boton eliminar de la vista 
     */
    public void eliminarEmpleado(ActionEvent evt) {
        if (view.getTxtNumero().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El numero de empleado es obligatorio");
        } else {
            Integer numero = Integer.parseInt(view.getTxtNumero().getText());
            String mensaje = service.deleteEmpleadoByNumero(numero);
            JOptionPane.showMessageDialog(null, mensaje);
            view.mostrarTabla("");
            view.limpiarCampos();
        }
    }
    
    /**
     * Metodo que permite llenar la JTable de empleados de la vista 
     * @param tabla Objeto que es pasado para ser llenado con el modelo 
     * @param nombre si se realiza una busqueda se usa esta campo para buscar y
     *          actualizar la tabla de acuerdo a este parametro
     */
    public void listarEmpleadosTabla(JTable tabla, String nombre){
        service.listarEmpleadosTabla(tabla, nombre);   
    }
    
    /**
     * Metodo que recuperar el numero de empleado para realizar la busqueda por 
     * este parametro, actualiza la tabla y los campos de la vista si se
     * encuentra la entidad, en caso contrario solo se muestra el mensaje que 
     * no se encuentro la entidad
     */
    public void buscarEmpleadoPorNumero(){
        if(view.getTxtNumero().getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo de numero empelado esta vacio");
        }else{

            boolean encontrado = service.validarNumero(Integer.parseInt(view.getTxtNumero().getText()));
            
            if(encontrado){    
                try{
                    
                    Empleado empleado = service.findEmpleadoByNumero(Integer.parseInt(view.getTxtNumero().getText()));
                    view.getTxtNumero().setText("" + empleado.getNumeroEmpleado());
                    view.getTxtRfc().setText(empleado.getRfc());
                    view.getTxtCurp().setText(empleado.getCurp());
                    view.getTxtNombre().setText(empleado.getNombre());
                    view.getTxtApellidoPaterno().setText(empleado.getApellidoPaterno());
                    view.getTxtApellidoMaterno().setText(empleado.getApellidoMaterno());
                    view.getTxtCorreoElectronico().setText(empleado.getCorreoElectronico());
                    view.getTxtTelefono().setText(empleado.getTelefono());
                    view.getTxtDireccion().setText(empleado.getDireccion());
                    view.getTxtSalario().setText("" + empleado.getSalario());

                    JOptionPane.showMessageDialog(null, "Busqueda exitosa");
                }catch(NonexistentEntityException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }else{
                JOptionPane.showMessageDialog(null, "El numero empleado: " + view.getTxtNumero().getText() + " no existe");
                view.limpiarCampos();
            }
        }
    }
 
    /**
     * Metodo que busca una entidad Empleado por nombre
     * si lo encuentra actualiza la tabla y los campos del formulario
     */
    public void buscarEmpleadoPorNombre(){
        if(view.getTxtNombre().getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo de nombre esta vacio");
            view.mostrarTabla("");
        }else{
            view.mostrarTabla(view.getTxtNombre().getText());
        }
    }

    
}
