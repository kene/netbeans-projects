/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cursoswing;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class CreandoMarcoCentrado {
   
    public static void main(String[] args) {
        MarcoCentrado miMarco = new MarcoCentrado();
        miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miMarco.setVisible(true);
    }
    
}


class MarcoCentrado extends JFrame{
    
    public MarcoCentrado(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        
        int height = dimension.height;
        int width = dimension.width;
        
        this.setSize(width/2, height/2);
        this.setLocation(width/4, height/4);
        
        
        this.setTitle("Marco Centrado");
        
        Image myIcon = toolkit.getImage("small-png-icons.png");
        
        setIconImage(myIcon);
        
    }
    
}
