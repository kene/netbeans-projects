package mx.com.unadm.mexican.food.restaurant.exceptions;

/**
 * Clase que maneja las excepciones cuando una entidad no existe en la base de datos
 * @author Josue Palemon Perez
 */
public class NonexistentEntityException extends Exception {
    public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public NonexistentEntityException(String message) {
        super(message);
    }
}
