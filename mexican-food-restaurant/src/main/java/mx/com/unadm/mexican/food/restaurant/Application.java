/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant;

import mx.com.unadm.mexican.food.restaurant.views.FrmPrincipal;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Clase que inicia la aplicacion
 *
 * @author Josue Palemon Perez
 * @version 1.0
 */
// Configura esta clase como la clase que iniciara la aplicacion
@SpringBootApplication
// Ejecuta la configuracion de la aplicacion que se encuentra en el archivo properties
// src/main/resources/application.properties
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) {
        // create el contexto de la aplicacion para mandar a llamar a los beans de la aplicacion 
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).headless(false).run(args);
        java.awt.EventQueue.invokeLater(() -> {
            // Recupera la clase FrmPrincipal del contexto del contenedor de beans de Spring
            FrmPrincipal frmApp = context.getBean(FrmPrincipal.class);
            frmApp.setVisible(true);
            frmApp.showComponents();
        });

    }

}
