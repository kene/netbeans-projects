
package eventos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ventana extends JFrame implements MouseListener {

    JLabel etiqueta1, etiqueta2;
    JLabel labelTitulo;
    JButton boton1;

    public Ventana() {
        this.initComponents();
    }
    
    
    public void initComponents(){
        setLayout(null);

        labelTitulo = new JLabel();
        labelTitulo.setText("Eventos del Mouse");
        labelTitulo.setFont(new java.awt.Font("Comic Sans MS", 0, 28));
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        labelTitulo.setBounds(40, 10, 300, 40);
        labelTitulo.addMouseListener(this);

        etiqueta1 = new JLabel();
        etiqueta1.setBounds(10, 160, 190, 20);
        etiqueta2 = new JLabel();
        etiqueta2.setBounds(10, 180, 190, 20);

        boton1 = new JButton();
        boton1.setBounds(110, 75, 150, 75);
        boton1.setText("Presioname");
        boton1.addMouseListener(this);

        add(labelTitulo);
        add(etiqueta1);
        add(etiqueta2);
        add(boton1);

        setTitle("Eventos del Mouse");
        setSize(400, 240);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == boton1) {
            etiqueta1.setText("Hizo clic en mi botón");
        }
        if (e.getSource() == labelTitulo) {
            etiqueta1.setText("Hizo clic en el Titulo");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        etiqueta1.setText("");
        if (e.getSource() == boton1) {
            etiqueta2.setText("Presiono el botón");
        }
        if (e.getSource() == labelTitulo) {
            etiqueta2.setText("Presiono el Titulo");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == boton1) {
            etiqueta2.setText("Libero el botón");
        }
        if (e.getSource() == labelTitulo) {
            etiqueta2.setText("Libero el Titulo");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == boton1) {
            etiqueta1.setText("Entro a mi botón");
        }
        if (e.getSource() == labelTitulo) {
            etiqueta1.setText("Entro al Titulo");
        }
        etiqueta2.setText("");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == boton1) {
            etiqueta1.setText("Salio del botón");
        }
        if (e.getSource() == labelTitulo) {
            etiqueta1.setText("Salio del Titulo");
        }
        etiqueta2.setText("");
    }

    public static void main(String[] args) {
        Ventana ventana;
        ventana = new Ventana();
        ventana.setVisible(true);

    }
}
