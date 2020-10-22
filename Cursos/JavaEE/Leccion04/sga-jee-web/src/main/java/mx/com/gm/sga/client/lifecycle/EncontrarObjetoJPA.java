package mx.com.gm.sga.client.lifecycle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EncontrarObjetoJPA {

    private static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // Inicia transaccion 
        // Paso 1. Iniciar transaccion
        tx.begin();

        // Paso 2. Ejecuta SQL tipo SELECT
        Persona persona1 = em.find(Persona.class, 1);

        // Paso 3. Terminar la transaccion commit
        tx.commit();

        // Objeto en estado detached
        log.debug("Objeto recuperado - estado detached: " + persona1);

        // Cerramos el objeto entityManager
        em.close();
   
    }
}
