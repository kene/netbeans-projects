
package mx.com.gm.sga.services;

import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Inject;
import mx.com.gm.sga.data.UsuarioDao;
import mx.com.gm.sga.domain.Usuario;

@Stateful
public class UsuarioServiceImpl implements UsuarioServiceRemote, UsuarioService{

    @Inject
    private UsuarioDao usuarioDao;
    
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioDao.findAllUsuarios();
    }

    @Override
    public Usuario encontrarUsuarioPorId(Usuario usuario) {
        return usuarioDao.findUsuarioById(usuario);
    }

    @Override
    public Usuario encontrarUsuarioPorUserName(Usuario usuario) {
        return usuarioDao.findUsuarioByUserName(usuario);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        usuarioDao.insertUsuario(usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        usuarioDao.updateUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioDao.deleteUsuario(usuario);
    }
    
    
    
}
