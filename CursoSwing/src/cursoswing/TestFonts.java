/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cursoswing;

import java.awt.GraphicsEnvironment;
import javax.swing.JOptionPane;


public class TestFonts {
    
    public static void main(String[] args) {
        String provideFont = JOptionPane.showInputDialog("Introduce una fuente");
        
        boolean fontExist = false;
        
        String [] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        
        for (String font : fonts) {
            
            if(provideFont.equals(font)){
                fontExist = true;
            }
        }
        
        
        if(fontExist){
            System.out.println("Fuente instalada");
        }else{
            System.out.println("Fuente no instalada");
        }
    }
}
