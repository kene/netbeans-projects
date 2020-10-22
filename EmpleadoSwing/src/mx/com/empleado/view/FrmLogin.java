/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.empleado.view;

import javax.swing.JOptionPane;
import mx.com.empleado.dao.UsuarioDao;

/**
 *
 * @author macos
 */
public class FrmLogin extends javax.swing.JFrame {

    private UsuarioDao udao = new UsuarioDao();
    
    /**
     * Creates new form FrmLogin
     */
    public FrmLogin() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Login");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panLogin = new javax.swing.JPanel();
        lblContrasena = new javax.swing.JLabel();
        lblTitulo1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnIniciarSesion = new javax.swing.JButton();
        pwdContrasena = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(null);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panLogin.setBackground(new java.awt.Color(255, 255, 255));
        panLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblContrasena.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblContrasena.setForeground(new java.awt.Color(0, 0, 0));
        lblContrasena.setText("CONTRASENA:");
        panLogin.add(lblContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        lblTitulo1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblTitulo1.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo1.setText("Login");
        panLogin.add(lblTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        lblUsuario.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(0, 0, 0));
        lblUsuario.setText("USUARIO:");
        panLogin.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        txtUsuario.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        panLogin.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 200, -1));

        btnIniciarSesion.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btnIniciarSesion.setText("INICIAR SESION");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        panLogin.add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, -1, -1));

        pwdContrasena.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        pwdContrasena.setForeground(new java.awt.Color(0, 0, 0));
        panLogin.add(pwdContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 200, 30));

        getContentPane().add(panLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        if(txtUsuario.getText().equals("") || pwdContrasena.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }else{
            boolean encontrado = udao.login(txtUsuario.getText(), pwdContrasena.getText());
            if(encontrado){
                FrmPersona frmPersona = new FrmPersona();
                frmPersona.setVisible(true);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Usuario y Contrasena no validos");
            }
        }
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panLogin;
    private javax.swing.JPasswordField pwdContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
