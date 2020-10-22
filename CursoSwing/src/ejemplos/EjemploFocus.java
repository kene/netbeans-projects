/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class EjemploFocus extends JFrame {

    //referencia de Variables
    Label etiqueta;
    TextField campoTexto;
    TextArea textArea1, textArea2;
    Button boton;
    Choice seleccion;
    FocusListener foco;

    public static void main(String[] args) {
        EjemploFocus ef = new EjemploFocus("Evento Focus");
        ef.setVisible(true);
        ef.setBounds(50, 50, 200, 200);
       
    }

    public EjemploFocus(String titulo) {
        super(titulo);
        setLayout(new BorderLayout());
        addWindowListener(new ControlVentana());	//Controlador cerrar
        Panel panelNorte = new Panel();				//Panel NORTE
        Panel panelCenter = new Panel();			//Panel SUR
        //objetos de ventana
        campoTexto = new TextField("            ");
        etiqueta = new Label("etiqueta");
        seleccion = new Choice();
        seleccion.add("item 1");
        seleccion.add("item 2");
        seleccion.add("item 3");
        seleccion.add("item 4");
        boton = new Button("boton");
        //Controlar eventos
        campoTexto.addFocusListener(new ControlFoco());
        boton.addFocusListener(new ControlFoco());
        seleccion.addFocusListener(new ControlFoco());
        //Objetos del panel NORTE
        panelNorte.add(campoTexto);
        panelNorte.add(etiqueta);
        panelNorte.add(seleccion);
        panelNorte.add(boton);
        add("North", panelNorte);
        //Objetos del panel CENTRO                                 
        textArea1 = new TextArea();
        textArea2 = new TextArea();
        textArea2.setEditable(false);	//No permite editar
        panelCenter.add(textArea1);
        panelCenter.add(textArea2);
        add("Center", panelCenter);

    }//Fin constructor	

    class ControlVentana extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }

    }//Fin Extendida

    //Controlador del boton
    class ControlBoton implements ActionListener {

        public void actionPerformed(ActionEvent action) {
            System.out.println(action.getSource());
        }
    }//Fin control boton	

    class ControlFoco implements FocusListener {

        public void focusGained(FocusEvent e) {
            textArea2.append("Foco ganado " + e.getSource() + "\n");
        }

        public void focusLost(FocusEvent e) {
            textArea2.append("Foco Perdido " + e.getSource() + "\n");
        }
    }//Fin de control de foco

}//Fin clase
