
package mx.com.gm.sga.client.lifecycle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ActualizarObjetoJPA {
    
    private static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // Inicia transaccion 
        // Paso 1. Iniciar transaccion
        tx.begin();

        // Paso 2. Ejecuta SQL tipo SELECT
        // El id proporcionado debe existir en la base de datos
        Persona persona1 = em.find(Persona.class, 1);

        // Paso 3. Termina la transaccion
        tx.commit();
       

        // Objeto recuperado en estado detached
        log.debug("Objeto recuperado - estado detached: " + persona1);

        // Paso 4. setValue(nuevoValor)
        persona1.setApellido("Juarez");
        
        // Paso 5. Inicia la transaccion 2
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        
        // Paso 6. Modificamos el objeto 
        em.merge(persona1);
        
        //em.flush(); // ejecuta los cambios de SQL sin ejecutar el commit
        // Paso 7. Terminamos la transaccion 2
        tx2.commit();
        
        // objeto en estado de detached ya modificado
        log.debug("Objeto recuperado: " + persona1);
        
        
        // Cerramos el objeto entityManager
        em.close();
   
    }
}
