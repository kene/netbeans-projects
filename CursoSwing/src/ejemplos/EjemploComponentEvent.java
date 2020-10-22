/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
class EjemploComponentEvent extends JPanel
                                implements ComponentListener,
                                           ItemListener {
    static JFrame frame;
    JTextArea display;
    JLabel label;
    String newline = "\n";
 
    public EjemploComponentEvent() {
        super(new BorderLayout());
 
        display = new JTextArea();
        display.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setPreferredSize(new Dimension(350, 200));
 
        JPanel panel = new JPanel(new BorderLayout());
        label = new JLabel("Esto es una etiqueta", JLabel.CENTER);
        label.addComponentListener(this);
        panel.add(label, BorderLayout.CENTER);
 
        JCheckBox checkbox = new JCheckBox("Etiqueta visible", true);
        checkbox.addItemListener(this);
        checkbox.addComponentListener(this);
        panel.add(checkbox, BorderLayout.PAGE_END);
        panel.addComponentListener(this);
 
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.PAGE_END);
        frame.addComponentListener(this);
    }
 
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            label.setVisible(true);

            label.revalidate();
            label.repaint();
        } else {
            label.setVisible(false);
        }
    }
 
    protected void displayMessage(String message) {

        display.append(message + newline);
            display.setCaretPosition(display.getDocument().getLength());
        //}
    }
 
    public void componentHidden(ComponentEvent e) {
        displayMessage(e.getComponent().getClass().getName() + " --- Esconder");
    }
 
    public void componentMoved(ComponentEvent e) {
        displayMessage(e.getComponent().getClass().getName() + " --- Mover");
    }
 
    public void componentResized(ComponentEvent e) {
        displayMessage(e.getComponent().getClass().getName() + " --- Redimensionar");            
    }
 
    public void componentShown(ComponentEvent e) {
        displayMessage(e.getComponent().getClass().getName() + " --- Mostrar");
 
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("EjemploComponentEvent");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        JComponent newContentPane = new EjemploComponentEvent();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
} 