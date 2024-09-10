package cr.ac.una.securityws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import cr.ac.una.securityws.model.Roles;
import cr.ac.una.securityws.model.RolesDto;
import cr.ac.una.securityws.model.Usuarios;
import cr.ac.una.securityws.model.UsuariosDto;
import cr.ac.una.securityws.util.CodigoRespuesta;
import cr.ac.una.securityws.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
@LocalBean
public class UsuariosService {

    private static final Logger LOG = Logger.getLogger(UsuariosService.class.getName());
    @PersistenceContext(unitName = "SigeceUnaWsPU")
    private EntityManager em;

    public Respuesta validateUser(String usuario, String clave) {
        try {
            LOG.log(Level.INFO, "Ejecutando consulta con usuario: {0} y clave: {1}", new Object[] { usuario, clave });

            // Realiza la consulta para encontrar el usuario con las credenciales
            // proporcionadas
            Query query = em.createNamedQuery("Usuarios.findByUsuClave", Usuarios.class);
            query.setParameter("usuario", usuario);
            query.setParameter("clave", clave);
            Usuarios usuarios = (Usuarios) query.getSingleResult();

            if (usuarios == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                        "No existe un usuario con las credenciales ingresadas.", "validateUser Usuario nulo");
            }

            // Crea un DTO de Usuarios basado en el usuario encontrado
            UsuariosDto usuariosDto = new UsuariosDto(usuarios);

            // Agrega los roles del usuario al DTO
            for (Roles rol : usuarios.getRoles()) {
                usuariosDto.getRolesDto().add(new RolesDto(rol));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", usuariosDto);

        } catch (NoResultException ex) {
            LOG.log(Level.INFO, "No se encontró ningún usuario con las credenciales proporcionadas.");
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                    "No existe un usuario con las credenciales ingresadas.", "validateUser NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al consultar el usuario. Más de un resultado encontrado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO,
                    "Ocurrió un error al consultar el usuario. Más de un resultado encontrado.",
                    "validateUser NonUniqueResultException");
        } catch (NullPointerException ex) {
            LOG.log(Level.SEVERE, "Error de referencia nula en la consulta o procesamiento del usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO,
                    "Error de referencia nula en la consulta o procesamiento del usuario.",
                    "validateUser NullPointerException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error inesperado al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO,
                    "Ocurrió un error inesperado al consultar el usuario.", "validateUser " + ex.getMessage());
        }
    }

    public Respuesta saveUser(UsuariosDto usuariosDto) {
        try {
            Usuarios usuarios;

            if (usuariosDto.getId() != null && usuariosDto.getId() > 0) {
                usuarios = em.find(Usuarios.class, usuariosDto.getId());
                if (usuarios == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                            "No se encontró el usuario a modificar.", "saveUser NoResultException");
                }

                usuarios.actualizar(usuariosDto);
                usuarios = em.merge(usuarios);
            } else {
                usuarios = new Usuarios(usuariosDto);
                em.persist(usuarios);
            }

            // Aseguramos que los cambios se confirmen en la base de datos
            em.flush();

            // Log para confirmar que el usuario fue guardado correctamente
            LOG.log(Level.INFO, "Usuario guardado correctamente: {0}", usuarios);

            // Retornamos la respuesta de éxito
            return new Respuesta(true, CodigoRespuesta.CORRECTO,
                    "El usuario se guardó correctamente", "", "Usuario",
                    new UsuariosDto(usuarios));
        } catch (NoResultException ex) {
            // Manejo de excepción específica cuando no se encuentra el usuario
            LOG.log(Level.SEVERE, "Usuario no encontrado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                    "No se encontró el usuario a modificar.",
                    "saveUser " + ex.getMessage());
        } catch (Exception ex) {
            // Manejo de cualquier otra excepción no esperada
            LOG.log(Level.SEVERE, "Ocurrió un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO,
                    "Ocurrió un error al guardar el usuario.",
                    "saveUser " + ex.getMessage());
        }
    }

    public Respuesta deleteUser(Long id) {
        try {
            Usuarios usuarios;
            if (id != null && id > 0) {
                usuarios = em.find(Usuarios.class, id);
                if (usuarios == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                            "No se encontro el usuario a eliminar.", "deleteUser NoResultException");
                }

                em.remove(usuarios);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el usuario a eliminar.",
                        "deleteUser NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al eliminar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el usuario.",
                    "deleteUser " + ex.getMessage());
        }
    }

    public Respuesta getAllUser() {

        try {
            Query query = em.createNamedQuery("Usuarios.findAll", Usuarios.class);
            List<Usuarios> usuarios = query.getResultList(); // No hacemos cast aquí
            List<UsuariosDto> usuariosDto = new ArrayList<>();
            for (Usuarios usuario : usuarios) {
                UsuariosDto usuarioDto = new UsuariosDto(usuario);
                usuariosDto.add(usuarioDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuarios", usuariosDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "", "getAllUsers NoResultException", ex);
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE,
                    "Ocurrio un error al consultar los usuarios.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "", "getAllUsers NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE,
                    "Ocurrio un error al consultar los usuarios.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "", "getAllUsers NonUniqueResultException");
        }
    }

    public Respuesta getUsuario(Long id) {
        try {
            Query query = em.createNamedQuery("Usuarios.findById", Usuarios.class);
            query.setParameter("id", id);
            Usuarios usuarios = (Usuarios) query.getSingleResult();
            UsuariosDto usuariosDto = new UsuariosDto(usuarios);

            for (Roles rol : usuarios.getRoles()) {
                usuariosDto.getRolesDto().add(new RolesDto(rol));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", usuariosDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                    "No existe un usuario con el código ingresado.", "getUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.",
                    "getUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuairo.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.",
                    "getUsuario " + ex.getMessage());
        }
    }

}
