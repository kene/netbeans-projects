/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cursoswing;

import java.awt.Frame;
import javax.swing.JFrame;


public class CursoSwing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            Marco miMarco = new Marco();
    }
    
}

class Marco extends JFrame{


    public Marco(){
       
        this.setVisible(true);
//         this.setSize(300, 200);
//        this.setLocation(500, 300);
        this.setBounds(300,200, 500, 300);
        this.setTitle("Mi Ventana");
//        this.setResizable(false);
//        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
