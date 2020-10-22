/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.com.unadm.mexican.food.restaurant.models.Empleado;
import mx.com.unadm.mexican.food.restaurant.models.Nomina;
import mx.com.unadm.mexican.food.restaurant.models.Producto;
import mx.com.unadm.mexican.food.restaurant.models.Restaurante;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 *
 * Clase que maneja la generacion de los reportes
 *
 * @author Josue Palemon Perez
 */
@Service
public class ReporteService {

    @Autowired
    private EmpleadoService empService;
    @Autowired
    private RestauranteService restService;
    @Autowired
    private ProductoService prodService;
    @Autowired
    private NominaService nomService;
    
    
    public JRViewer generateEmployeesReport() throws JRException, FileNotFoundException {

        List<Empleado> empleados = empService.getAllEmpleados();
        File file = ResourceUtils.getFile("classpath:reports/Employees.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empleados);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("creado", "Josue Palemon");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JRViewer viewer = new JRViewer(jasperPrint);

        return viewer;
    }
    
    
    public JRViewer generateRestaurantsReport() throws JRException, FileNotFoundException{
        
        List<Restaurante> empleados = restService.getAllRestaurantes();
        File file = ResourceUtils.getFile("classpath:reports/Restaurants.jrxml");
        
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empleados);
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("creado", "Josue Palemon");
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JRViewer viewer = new JRViewer(jasperPrint);
        
        return viewer;     
    }
    
    public JRViewer generateProductsReport() throws JRException, FileNotFoundException{
        
        List<Producto> productos = prodService.getAllProductos();
        File file = ResourceUtils.getFile("classpath:reports/Products.jrxml");
        
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productos);
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("creado", "Josue Palemon");
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JRViewer viewer = new JRViewer(jasperPrint);
        return viewer;     
    }
    
    public JRViewer generatePayRollsReport() throws JRException, FileNotFoundException{
        
        List<Nomina> nominas = nomService.getAllNominas();
        File file = ResourceUtils.getFile("classpath:reports/PayRolls.jrxml");
        
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(nominas);
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("creado", "Josue Palemon");
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JRViewer viewer = new JRViewer(jasperPrint);
        return viewer;     
    }

}
