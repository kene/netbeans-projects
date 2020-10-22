package mx.com.empleado.test;

import mx.com.empleado.dao.PersonaDao;
import org.junit.Test;
import org.junit.Assert;

public class PersonaTest {

    private PersonaDao personaDao = new PersonaDao();

    public PersonaTest() {
    }

    @Test
    public void insertarPersonaTest() {
        String expected = personaDao.insertarPersona("Julian", "Perez", 40, "5648920102");
        String actual = "Guardado correctamente";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void actualizarPersonaTest() {
        String expected = personaDao.actualizarPersona(5, "Julian", "Ramirez", 34, "5648920105");
        String actual = "Actualizado correctamente";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void eliminarPersonaTest() {
        String expected = personaDao.eliminarPersona(5);
        String actual = "Eliminado correctamente";
        Assert.assertEquals(expected, actual);
    }
}
