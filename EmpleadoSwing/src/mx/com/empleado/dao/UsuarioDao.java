
package mx.com.empleado.dao;

import empleadoswing.UsuarioJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mx.com.empleado.entity.Usuario;


public class UsuarioDao {
    
    private Usuario usuario = new Usuario();
    private UsuarioJpaController upc = new UsuarioJpaController();
    
    
    public boolean login(String usuario, String contrasena){
        boolean valor = false;
        String mensaje = "";
        EntityManager em = upc.getEntityManager();
        
        try {
            Query query = em.createQuery("SELECT u.usuario, u.contrasena "
                    + "FROM Usuario u "
                    + "WHERE u.usuario = :usuario AND u.contrasena = :contrasena");
            
            query.setParameter("usuario", usuario);
            query.setParameter("contrasena", contrasena);
            
            List resultado = query.getResultList();
            
            if(!resultado.isEmpty()){
                valor = true;
            }
            
        } catch (Exception e) {
            valor = false;
            mensaje = "Usuario y contrasena invalido";
        }
        
        return valor;
    }
    
}
