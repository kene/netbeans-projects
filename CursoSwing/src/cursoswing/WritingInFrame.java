/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cursoswing;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class WritingInFrame {
    
    
    public static void main(String[] args) {
        
        MyFrame myFrame = new MyFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
}

class MyFrame extends JFrame{
    
    public MyFrame(){
        this.setVisible(true);
        this.setBounds(600, 400, 400, 200);
        this.setTitle("Primer texto");
        MyPanel myPanel = new MyPanel();
        
        this.add(myPanel);
    }
    
}

class MyPanel extends JPanel{
    
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.drawString("Segundo Texto", 100, 100);
    }
    
    
}

