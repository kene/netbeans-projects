/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mx.com.unadm.mexican.food.restaurant.exceptions.NonexistentEntityException;
import mx.com.unadm.mexican.food.restaurant.models.Empleado;
import mx.com.unadm.mexican.food.restaurant.models.Nomina;
import mx.com.unadm.mexican.food.restaurant.services.EmpleadoService;
import mx.com.unadm.mexican.food.restaurant.services.NominaService;
import mx.com.unadm.mexican.food.restaurant.views.FrmNomina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Clase que maneja el flujo de control entre los modelos y las vistas de la Nomina
 * @author Josue Palemon Perez
 */
// Anotacion de Spring que marca esta clase como un controlador
@Controller
public class NominaController {

    @Autowired
    private NominaService service;
    @Autowired
    private EmpleadoService empService;
    @Autowired
    private FrmNomina view;

    public NominaController(){ }

    
    public void selectedRowNominaTabla(MouseEvent evt) {
        int selected = view.getTblNominaData().getSelectedRow();
        view.getTxtNumero().setText(view.getTblNominaData().getValueAt(selected, 0) + "");
        view.getTxtRazonSocial().setText(view.getTblNominaData().getValueAt(selected, 1) + "");
        view.getTxtDomicilio().setText(view.getTblNominaData().getValueAt(selected, 2) + "");
        view.getTxtFecha().setText((view.getTblNominaData().getValueAt(selected, 3) + "").split(" ")[0]);
        view.getTxtPeriodoInicio().setText((view.getTblNominaData().getValueAt(selected, 4) + "").split(" ")[0]);
        view.getTxtPeriodoFin().setText((view.getTblNominaData().getValueAt(selected, 5) + "").split(" ")[0]);
        view.getTxtDias().setText(view.getTblNominaData().getValueAt(selected, 6) + "");
    }
    
    
    public void guardarNomina(ActionEvent evt) throws NonexistentEntityException {

        if (view.getTxtRazonSocial().getText().equals("")
            || view.getTxtDomicilio().getText().equals("")
            || view.getTxtFecha().getText().equals("")
            || view.getTxtPeriodoInicio().getText().equals("")
            || view.getTxtPeriodoFin().getText().equals("")){
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            
        } else {
            
            String razonSocial = view.getTxtRazonSocial().getText();
            String domicilio = view.getTxtDomicilio().getText();
            String sFecha = view.getTxtFecha().getText().split(" ")[0];
            String sPeriodoInicio = view.getTxtPeriodoInicio().getText().split(" ")[0];
            String sPeriodoFin = view.getTxtPeriodoFin().getText().split(" ")[0]  ;
            Integer dias = Integer.parseInt(view.getTxtDias().getText());
            
            Nomina n = null;
            try {
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(sFecha);  
                Date periodoInicio = new SimpleDateFormat("yyyy-MM-dd").parse(sPeriodoInicio);  
                Date periodoFin = new SimpleDateFormat("yyyy-MM-dd").parse(sPeriodoFin);  
                
                
                n = new Nomina(razonSocial, domicilio, fecha, periodoInicio, periodoFin, dias);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Error al recuperar la fecha");  
            }
            Empleado e = empService.findEmpleadoByCodigo(1);
            n.setNumeroEmpleado(e);
            String mensaje = service.createNomina(n);
            
            JOptionPane.showMessageDialog(null, mensaje);
            view.mostrarTabla("");
            view.limpiarCampos();

        }

    }
    
    public void modificarNomina(ActionEvent evt) throws NonexistentEntityException {
        if (view.getTxtNumero().getText().equals("")
            || view.getTxtRazonSocial().getText().equals("")
            || view.getTxtDomicilio().getText().equals("")
            || view.getTxtFecha().getText().equals("")
            || view.getTxtPeriodoInicio().getText().equals("")
            || view.getTxtPeriodoFin().getText().equals("")){
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        } else {
            Integer numero = Integer.parseInt(view.getTxtNumero().getText());
            String razonSocial = view.getTxtRazonSocial().getText();
            String domicilio = view.getTxtDomicilio().getText();
            String sFecha = view.getTxtFecha().getText();
            String sPeriodoInicio = view.getTxtPeriodoInicio().getText();
            String sPeriodoFin = view.getTxtPeriodoFin().getText();
            Integer dias = Integer.parseInt(view.getTxtDias().getText());
            
            Nomina n = null;
            try {
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(sFecha);  
                Date periodoInicio = new SimpleDateFormat("yyyy-MM-dd").parse(sPeriodoInicio);  
                Date periodoFin = new SimpleDateFormat("yyyy-MM-dd").parse(sPeriodoFin);  
                
                n = new Nomina(numero, razonSocial, domicilio, fecha, periodoInicio, periodoFin, dias);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Error al recuperar la fecha");  
            }
           
            Empleado e = empService.findEmpleadoByCodigo(1);
            n.setNumeroEmpleado(e);
            String mensaje = service.updateNomina(n);
            
            JOptionPane.showMessageDialog(null, mensaje);
            view.mostrarTabla("");
            view.limpiarCampos();
        } 

    }

    public void eliminarNomina(ActionEvent evt) {
        if (view.getTxtNumero().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El numero nomina es obligatorio");
        } else {
            Integer numero = Integer.parseInt(view.getTxtNumero().getText());
            String mensaje = service.deleteNominaByNumero(numero);
            JOptionPane.showMessageDialog(null, mensaje);
            view.mostrarTabla("");
            view.limpiarCampos();
        }
    }
    
    
    public void listarNominasTabla(JTable tabla, String razonSocial){
        service.listarNominasTabla(tabla, razonSocial);   
    }
    
    public void buscarNominaPorNumero(){
        if(view.getTxtNumero().getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo de numero nomina esta vacio");
        }else{

            boolean encontrado = service.validarNumero(Integer.parseInt(view.getTxtNumero().getText()));
            
            if(encontrado){    
                try{
                    Nomina nomina = service.findNominaByCodigo(Integer.parseInt(view.getTxtNumero().getText()));
                    view.getTxtNumero().setText("" + nomina.getNumeroNomina());
                    view.getTxtRazonSocial().setText(nomina.getRazonSocial());
                    view.getTxtDomicilio().setText(nomina.getDomicilio());
                    view.getTxtDias().setText("" + nomina.getDias());
                    view.getTxtPeriodoInicio().setText("" + nomina.getInicioPeriodo());
                    view.getTxtPeriodoInicio().setText("" + nomina.getFinPeriodo());

                    JOptionPane.showMessageDialog(null, "Busqueda exitosa");
                }catch(NonexistentEntityException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }else{
                JOptionPane.showMessageDialog(null, "El numero nomina: " + view.getTxtNumero().getText() + " no existe");
                view.limpiarCampos();
            }
        }
    }
 
    
    public void buscarNominaPorRazonSocial(){
        if(view.getTxtRazonSocial().getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo de razon social esta vacio");
            view.mostrarTabla("");
        }else{
            view.mostrarTabla(view.getTxtRazonSocial().getText());
        }
    }

    
}
