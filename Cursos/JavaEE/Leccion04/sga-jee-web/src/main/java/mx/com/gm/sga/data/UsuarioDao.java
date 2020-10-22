
package mx.com.gm.sga.data;

import java.util.List;
import mx.com.gm.sga.domain.Usuario;


public interface UsuarioDao {
    
    public List<Usuario> findAllUsuarios();
    
    public Usuario findUsuarioById(Usuario usuario);
    
    public Usuario findUsuarioByUserName(Usuario usuario);
   
    public void insertUsuario(Usuario usuario);
    
    public void updateUsuario(Usuario usuario);
    
    public void deleteUsuario(Usuario usuario);
}
