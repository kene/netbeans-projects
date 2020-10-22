/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static sun.misc.ClassFileTransformer.add;

public class EjemploMouseMotionEvent extends JPanel
        implements MouseMotionListener {
    BlankArea blankArea;
    JTextArea textArea;
    static final String NEWLINE = System.getProperty("line.separator");
     
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
 
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("EjemploMouseMotionEvent");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Create and set up the content pane.
        JComponent newContentPane = new EjemploMouseMotionEvent();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
     
    public EjemploMouseMotionEvent() {
        super(new GridLayout(0,1));
        blankArea = new BlankArea(new Color(14, 244,128).brighter());
        add(blankArea);
         
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(200, 75));
         
        add(scrollPane);
         
        //Register for mouse events on blankArea and panel.
        blankArea.addMouseMotionListener(this);
        addMouseMotionListener(this);
         
        setPreferredSize(new Dimension(450, 450));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
     
    void eventOutput(String eventDescription, MouseEvent e) {
        textArea.append(eventDescription
                + " (" + e.getX() + "," + e.getY() + ")"
                + " detectado en "
                + e.getComponent().getClass().getName()
                + NEWLINE);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
     
    public void mouseMoved(MouseEvent e) {
        eventOutput("Mouse en movimiento", e);
    }
     
    public void mouseDragged(MouseEvent e) {
        eventOutput("Mouse arrastro", e);
    }
}