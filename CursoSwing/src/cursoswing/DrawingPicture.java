/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cursoswing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class DrawingPicture {
    
    public static void main(String[] args) {
        MyFrameDrawing mfd = new MyFrameDrawing();
        mfd.setVisible(true);
        mfd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}


class MyFrameDrawing extends JFrame{

    public MyFrameDrawing() {
        this.setTitle("Mi Dibujo");
        this.setSize(400,400);
        PanelDrawing pd = new PanelDrawing();
        pd.setBackground(SystemColor.window);
        this.add(pd);
    }
    
}


class PanelDrawing extends JPanel{

    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        g.drawRect(50, 50, 200, 200);
//        g.drawLine(50, 50, 200, 200);
//        g.drawArc(50, 50, 10, 120, 150, 180);


            Graphics2D g2 = (Graphics2D)g;
            
            Rectangle2D rec = new Rectangle2D.Double(100, 100, 200.25, 150);
            
            g2.setPaint(Color.BLUE);
            g2.draw(rec);
            g2.setPaint(Color.RED);
            g2.fill(rec);
            
            Ellipse2D elipse = new Ellipse2D.Double();
            
            elipse.setFrame(rec);
            
            g2.setPaint(new Color(109, 172, 99).darker());
            
            g2.fill(elipse);
        
    }
    
}
