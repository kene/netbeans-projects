package mx.com.empleado.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mx.com.empleado.controller.PersonaJpaController;
import mx.com.empleado.entity.Persona;

public class PersonaDao {

    private PersonaJpaController pjc = new PersonaJpaController();
    private Persona persona = new Persona();
    private String mensaje = "";

    public String insertarPersona(String nombre, String apellidos, int edad, String telefono) {

        try {
            persona.setId(Integer.BYTES);
            persona.setNombres(nombre);
            persona.setApellido(apellidos);
            persona.setEdad(edad);
            persona.setTelefono(telefono);
            pjc.create(persona);
            mensaje = "Guardado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se guardo la informacion";
        }

        return mensaje;
    }

    public String actualizarPersona(int id, String nombre, String apellidos, int edad, String telefono) {
        try {
            Persona actualPersona = pjc.findPersona(id);
            if (actualPersona != null) {
                actualPersona.setId(id);
                actualPersona.setNombres(nombre);
                actualPersona.setApellido(apellidos);
                actualPersona.setEdad(edad);
                actualPersona.setTelefono(telefono);
                pjc.edit(actualPersona);
                mensaje = "Actualizado correctamente";
            } else {
                throw new Exception("Persona con id: " + id + " no existe");
            }
        } catch (Exception e) {
            System.out.println("Mensaje en actualizar: " + e.getMessage());
            mensaje = "No se actualizo la informacion \n" + e.getMessage();
        }

        return mensaje;
    }

    public String eliminarPersona(int id) {

        try {
            pjc.destroy(id);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje eliminar: " + e.getMessage());
            mensaje = "No se elimino la informacion \n" + e.getMessage();
        }

        return mensaje;
    }

    public void listarPersonas(JTable tabla, String nombres) {
        DefaultTableModel model;
        String[] HEADERS = {"ID", "NOMBRES", "APELLIDOS", "EDAD", "TELEFONO"};
        model = new DefaultTableModel(null, HEADERS);

        List<Persona> data = buscarPersona(nombres); //pjc.findPersonaEntities();
        String[] datosPersona = new String[5];

        for (Persona oPersona : data) {
            datosPersona[0] = oPersona.getId() + "";
            datosPersona[1] = oPersona.getNombres() + "";
            datosPersona[2] = oPersona.getApellido() + "";
            datosPersona[3] = oPersona.getEdad() + "";
            datosPersona[4] = oPersona.getTelefono() + "";
            model.addRow(datosPersona);
        }

        tabla.setModel(model);
        model.fireTableDataChanged();
    }

    private List<Persona> buscarPersona(String nombres) {
        EntityManager em = pjc.getEntityManager();
        Query query = em.createQuery("SELECT p FROM Persona p WHERE p.nombres LIKE :nombres");
        query.setParameter("nombres", nombres + "%");
        List<Persona> lista = query.getResultList();
        return lista;
    }

    public Persona buscarPersonaPorId(int id) {
        String mensaje = "";
        EntityManager em = pjc.getEntityManager();
        Query query = em.createQuery("SELECT p FROM Persona p WHERE p.id = :id");
        query.setParameter("id", id);
        Persona result = (Persona) query.getSingleResult();
        return result;
    }

    public boolean validarId(int id) {
        boolean noFound = true;
        Persona persona = pjc.findPersona(id);
        if (persona == null) {
            noFound = false;
        }

        return noFound;
    }
    
    
    public String getIdIncremental(){
        EntityManager em = pjc.getEntityManager();
        Query query = em.createQuery("SELECT Max(p.id) + 1 FROM Persona p");
        List id = query.getResultList();
        String idIncrement = id.toString();
        return idIncrement.replace("[", "").replace("]", "");
    }

}
