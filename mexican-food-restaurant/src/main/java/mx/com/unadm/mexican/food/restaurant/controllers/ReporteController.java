/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant.controllers;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mx.com.unadm.mexican.food.restaurant.models.Producto;
import mx.com.unadm.mexican.food.restaurant.models.Restaurante;
import mx.com.unadm.mexican.food.restaurant.services.ProductoService;
import mx.com.unadm.mexican.food.restaurant.services.ReporteService;
import mx.com.unadm.mexican.food.restaurant.services.RestauranteService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;

/**
 * Clase que controla el flujo entre las vistas y los reportes que se generan 
 * en la aplicacion
 * @author Josue Palemon Perez
 */
@Controller
public class ReporteController {
    
    @Autowired
    private ReporteService service;
    private static final Logger LOGGER = LogManager.getLogger(ProductoService.class);
    
    /**
     * Metodo que genera el reporte de empleados
     */
    public void generarReporteEmpleado(){
        try {
            JFrame frame = new JFrame("Jasper report");
            frame.add(service.generateEmployeesReport());
            frame.setSize(new Dimension(800, 900));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        } catch (JRException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se genero el reporte empleados correctamente");
            LOGGER.error("Mensaje error: ", e.getMessage());
        }
    }
    

    /**
     * Metodo que genera el reporte de restaurantes sucursales
     */
    public void generarReporteRestaurante() {
        try {
            JFrame frame = new JFrame("Jasper report");
            frame.add(service.generateRestaurantsReport());
            frame.setSize(new Dimension(800, 900));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        } catch (JRException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se genero el reporte restaurantes correctamente");
            LOGGER.error("Mensaje error: ", e.getMessage());
        }     
    }
    
    /**
     * Metodo que genera el reporte de producto por sucursal
     */
    public void generarReporteProducto(){
        try {
            JFrame frame = new JFrame("Jasper report");
            frame.add(service.generateProductsReport());
            frame.setSize(new Dimension(800, 900));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        } catch (JRException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se genero el reporte productos correctamente");
            LOGGER.error("Mensaje error: ", e.getMessage());
        }     
    }
    
    /**
     * Metodo que genera el reporte de nominas 
     */
    public void generarReporteNomina(){
        try {
            JFrame frame = new JFrame("Jasper report");
            frame.add(service.generatePayRollsReport());
            frame.setSize(new Dimension(800, 900));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        } catch (JRException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se genero el reporte nominas correctamente");
            LOGGER.error("Mensaje error: ", e.getMessage());
        }     
    }
    
}
