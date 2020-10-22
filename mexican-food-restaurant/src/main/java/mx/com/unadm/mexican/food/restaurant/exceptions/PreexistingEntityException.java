package mx.com.unadm.mexican.food.restaurant.exceptions;

/**
 * Clase que maneja las excepciones para cuando una entidad ya existe en la base de datos
 * @author Josue Palemon Perez
 */
public class PreexistingEntityException extends Exception {
    public PreexistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public PreexistingEntityException(String message) {
        super(message);
    }
}
