package mx.com.gm.sga.client.lifecycle;

import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EliminarObjetoJPA {

    private static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // Inicia transaccion 
        // Paso 1. Iniciar transaccion
        tx.begin();

        // Paso 2. Ejecuta SQL tipo SELECT
        Persona persona1 = em.find(Persona.class, 6);
        
        // Paso 3. Terminar la transaccion 
        tx.commit();
        
        log.debug("objeto recuperado: " + persona1);
        
        // Paso 4. Inicia transaccion 2
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        
        
        // Paso 5. Ejecuta SQL delete
        em.remove(em.merge(persona1)); // actualiza y sincroniza la informacion con el objeto en memoria y se hace el delete
        
        // Paso 6. Termina transaccion 2
        tx2.commit();
        
        // objeto en detached ya eliminado
        log.debug("objeto eliminado: " + persona1);
        
        // Cerramos el objeto entityManager
        em.close();

    }
}
