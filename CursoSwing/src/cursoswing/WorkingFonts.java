/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cursoswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class WorkingFonts {
    
    public static void main(String[] args) {
        MyFrameFont frameFont = new MyFrameFont();
        
    }
    
}


class MyFrameFont extends JFrame{
    
    public MyFrameFont(){
        setBounds(80, 80, 300, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Fuentes");
        MyPanelFont panel = new MyPanelFont();
        
        add(panel);
        
    }
    
}


class MyPanelFont extends JPanel{
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        
        Font font = new Font("Arial", Font.BOLD, 26);
        g2.setFont(font);
        g2.setColor(Color.BLUE);
        
        g2.drawString("Kene", 100, 100);
        
        g2.setFont(new Font("Courier", Font.BOLD, 26));
        g2.setColor(new Color(128, 90, 50));
        g2.drawString("Curso Java Swing", 100, 200);
    }
    
}