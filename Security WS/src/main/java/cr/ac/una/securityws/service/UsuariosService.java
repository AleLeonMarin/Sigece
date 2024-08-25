package cr.ac.una.securityws.service;

import cr.ac.una.securityws.model.Roles;
import cr.ac.una.securityws.model.RolesDto;
import cr.ac.una.securityws.model.Sistemas;
import cr.ac.una.securityws.model.SistemasDto;
import cr.ac.una.securityws.model.SistemasRolesUsuarios;
import cr.ac.una.securityws.model.SistemasRolesUsuariosDto;
import cr.ac.una.securityws.model.Usuarios;
import cr.ac.una.securityws.model.UsuariosDto;
import cr.ac.una.securityws.util.CodigoRespuesta;
import cr.ac.una.securityws.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
@LocalBean
public class UsuariosService {

    @PersistenceContext(unitName = "SigeceUnaWsPU")
    private EntityManager em;

    public Respuesta validateUser(String usuario, String clave) {
        try {
            Query query = em.createNamedQuery("Usuarios.findByUsuClave");
            query.setParameter("usu_usuario", usuario);
            query.setParameter("usu_clave", clave);
            Usuarios usuarios = (Usuarios) query.getSingleResult();

            UsuariosDto usuariosDto = new UsuariosDto(usuarios);

            if (usuarios.getSisRolesUsuarios() != null) {
                for (SistemasRolesUsuarios sistemasRolesUsuarios : usuarios.getSisRolesUsuarios()) {
                    Sistemas sistema = sistemasRolesUsuarios.getSisID();
                    Roles rol = sistemasRolesUsuarios.getRolID();

                    // Verificar si el usuario tiene un rol asignado en al menos un sistema
                    if (sistema != null && rol != null) {
                        return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", usuariosDto);
                    }
                }
            }

            // Si el usuario no tiene roles en ningún sistema
            return new Respuesta(false, CodigoRespuesta.ERROR_PERMISOS,"Usuario no tiene acceso a ningún sistema asignado.");
        } catch (NoResultException e) {
            return new Respuesta(false, CodigoRespuesta.ERROR_ACCESO, "Usuario o clave incorrectos.",e);
        } catch (Exception e) {
            e.printStackTrace();
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error en la validación del usuario.");
        }
    }

    /*
     * public Respuesta validateUser(String usuario, String clave, String
     * sistemaNombre, String rolNombre) {
     * try {
     * Query query = em.createNamedQuery("Usuarios.findByUsuClave");
     * query.setParameter("usu_usuario", usuario);
     * query.setParameter("usu_clave", clave);
     * Usuarios usuarios = (Usuarios) query.getSingleResult();
     * 
     * UsuariosDto usuariosDto = new UsuariosDto(usuarios);
     * 
     * if (usuarios.getSisRolesUsuarios() != null) {
     * for (SistemasRolesUsuarios sistemasRolesUsuarios :
     * usuarios.getSisRolesUsuarios()) {
     * Sistemas sistema = sistemasRolesUsuarios.getSisID();
     * Roles rol = sistemasRolesUsuarios.getRolID();
     * 
     * // Verificar si el sistema y rol coinciden con los parámetros proporcionados
     * if (sistema.getSisNombre().equals(sistemaNombre) &&
     * rol.getRolNombre().equals(rolNombre)) {
     * return new Respuesta(true,
     * "Usuario validado con acceso al sistema y rol correspondiente.");
     * }
     * }
     * }
     * return new Respuesta(false,
     * "Usuario no tiene acceso al sistema o no tiene el rol adecuado.");
     * } catch (NoResultException e) {
     * return new Respuesta(false, "Usuario o clave incorrectos.");
     * } catch (Exception e) {
     * e.printStackTrace();
     * return new Respuesta(false, "Error en la validación del usuario.");
     * }
     * }
     */

    /*
     * public Respuesta validateUser(String usuario, String clave) {
     * try {
     * Query query = em.createNamedQuery("Usuarios.findByUsuClave");
     * query.setParameter("usu_usuario", usuario);
     * query.setParameter("usu_clave", clave);
     * Usuarios usuarios = (Usuarios) query.getSingleResult();
     * 
     * UsuariosDto usuariosDto = new UsuariosDto(usuarios);
     * 
     * if (usuarios.getSisRolesUsuarios() != null) {
     * for (SistemasRolesUsuarios sistemasRolesUsuarios :
     * usuarios.getSisRolesUsuarios()) {
     * Sistemas sistema = sistemasRolesUsuarios.getSisID();
     * Roles rol = sistemasRolesUsuarios.getRolID();
     * 
     * // Verificar si el usuario tiene un rol asignado en al menos un sistema
     * if (sistema != null && rol != null) {
     * return new Respuesta(true, "Usuario validado con acceso al sistema: " +
     * sistema.getSisNombre() + " y rol: " + rol.getRolNombre());
     * }
     * }
     * }
     * 
     * // Si el usuario no tiene roles en ningún sistema
     * return new Respuesta(false,
     * "Usuario no tiene acceso a ningún sistema asignado.");
     * } catch (NoResultException e) {
     * return new Respuesta(false, "Usuario o clave incorrectos.");
     * } catch (Exception e) {
     * e.printStackTrace();
     * return new Respuesta(false, "Error en la validación del usuario.");
     * }
     * }
     * 
     */

    /*
     * public Respuesta validateUser(String usuario , String clave){
     * 
     * try{
     * Query query = em.createNamedQuery("Usuarios.findByUsuClave");
     * query.setParameter("usu_usuario", usuario );
     * query.setParameter("usu_clave", clave );
     * Usuarios usuarios = (Usuarios) query.getSingleResult();
     * 
     * UsuariosDto usuariosDto = new UsuariosDto(usuarios);
     * 
     * if(usuarios.getSisRolesUsuarios() != null){
     * 
     * for(SistemasRolesUsuarios sistemasRolesUsuariosDto :
     * usuarios.getSisRolesUsuarios()){
     * 
     * sistemasRolesUsuariosDto.setSistemasDto(new
     * SistemasDto(sistemasRolesUsuariosDto.getSisID()));
     * sistemasRolesUsuariosDto.setRolesDto(new
     * RolesDto(sistemasRolesUsuariosDto.getRolID()));
     * }
     * }
     * }
     * }
     */

}
