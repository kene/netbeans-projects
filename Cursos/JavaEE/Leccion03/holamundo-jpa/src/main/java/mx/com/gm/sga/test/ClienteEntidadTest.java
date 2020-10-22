
package mx.com.gm.sga.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ClienteEntidadTest {
    static Logger log = LogManager.getRootLogger();
    
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction tx = em.getTransaction();
        
        // inicia transaction 
        tx.begin();
        // no se puede especificar el ID de la base de datos
        Persona persona1 = new Persona("Maria", "Guitierrez", "mgutierez@gmail.com", "889921244");
        log.debug("Objeto a persistir: " + persona1);
        // persistir el objeto
        em.persist(persona1);
        // terminamos la transacion 
        tx.commit();
        log.debug("Objeto persistido: " + persona1);
        em.close();
        
    }
}
