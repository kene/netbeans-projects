
package mx.com.gm.sga.data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.com.gm.sga.domain.Usuario;


public class UsuarioDaoImpl implements UsuarioDao{

    @PersistenceContext(name = "PersonaPU")
    private EntityManager em;

    @Override
    public List<Usuario> findAllUsuarios() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }

    @Override
    public Usuario findUsuarioById(Usuario usuario) {
        return em.find(Usuario.class, usuario.getIdUsuario());
    }

    @Override
    public Usuario findUsuarioByUserName(Usuario usuario) {
        Query query = em.createNamedQuery("Usuario.findByUsername");
        query.setParameter("username", usuario.getUsername());
        return (Usuario) query.getSingleResult();
    }

    @Override
    public void insertUsuario(Usuario usuario) {
        em.persist(usuario);
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        em.merge(usuario);
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        em.remove(em.merge(usuario));
    }

    
}
