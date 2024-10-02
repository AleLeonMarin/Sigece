package cr.ac.una.securityws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import cr.ac.una.securityws.model.Roles;
import cr.ac.una.securityws.model.RolesDto;
import cr.ac.una.securityws.model.Usuarios;
import cr.ac.una.securityws.model.UsuariosDTORest;
import cr.ac.una.securityws.model.UsuariosDto;
import cr.ac.una.securityws.util.CodigoRespuesta;
import cr.ac.una.securityws.util.Request;
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

            Query query = em.createNamedQuery("Usuarios.findByUsuClave", Usuarios.class);
            query.setParameter("usuario", usuario);
            query.setParameter("clave", clave);

            List<Usuarios> usuariosList = query.getResultList();

            // Check if no users were found
            if (usuariosList.isEmpty()) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                        "No existe un usuario con las credenciales ingresadas.", "validateUser Usuario no encontrado");
            }

            // Only one user found
            Usuarios usuarios = usuariosList.get(0);
            UsuariosDto usuariosDto = new UsuariosDto(usuarios);
            for (Roles rol : usuarios.getRoles()) {
                usuariosDto.getRolesDto().add(new RolesDto(rol));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", usuariosDto);

        } catch (NoResultException ex) {
            LOG.log(Level.INFO, "No se encontró ningún usuario con las credenciales proporcionadas.");
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                    "No existe un usuario con las credenciales ingresadas.", "validateUser NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error inesperado al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO,
                    "Ocurrió un error inesperado al consultar el usuario.", "validateUser " + ex.getMessage());
        }
    }

    public Respuesta saveUser(UsuariosDto usuariosDto) {
        try {
            Usuarios usuarios;
            CorreosService correosService = new CorreosService();

            if (usuariosDto.getId() != null && usuariosDto.getId() > 0) {
                usuarios = em.find(Usuarios.class, usuariosDto.getId());
                if (usuarios == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                            "No se encontró el usuario a modificar.", "saveUser NoResultException");
                }

                usuarios.actualizar(usuariosDto);

                // Eliminar roles
                for (RolesDto rolDto : usuariosDto.getRolesEliminados()) {
                    Roles rol = em.find(Roles.class, rolDto.getId());
                    if (rol != null) {
                        rol.getUsuarios().remove(usuarios);
                        usuarios.getRoles().remove(rol);
                        em.merge(rol);
                        em.merge(usuarios);
                    }
                    LOG.log(Level.INFO, "Rol elimnado: {0}", rolDto.getId());
                }

                // Agregar roles
                if (!usuariosDto.getRolesDto().isEmpty()) {
                    for (RolesDto rolDto : usuariosDto.getRolesDto()) {
                        Roles rol = em.find(Roles.class, rolDto.getId());
                        rol.getUsuarios().add(usuarios);
                        usuarios.getRoles().add(rol);
                        LOG.log(Level.INFO, "Rol agregado: {0}", rol.getId());

                    }
                }

                usuarios = em.merge(usuarios);
            } else {
                usuarios = new Usuarios(usuariosDto);
                em.persist(usuarios);
                // Asignar rol de administrador al primer usuario registrado
                if (em.createQuery("SELECT COUNT(u) FROM Usuarios u", Long.class).getSingleResult() == 1) {
                    Roles administradorRole = em.find(Roles.class, 1L); // assume role ID 1 is administrator
                    if (administradorRole != null) {
                        administradorRole.getUsuarios().add(usuarios);
                        usuarios.getRoles().add(administradorRole);
                        em.merge(administradorRole);
                        usuarios = em.merge(usuarios); // Actualiza la lista de roles del usuario
                        LOG.log(Level.INFO, "Rol administrador asignado al primer usuario registrado.");
                    }
                }

                UsuariosDTORest usuarioRest= new UsuariosDTORest();
                
                usuarioRest.setUsuUsuario(usuariosDto.getUsuario());
                usuarioRest.setUsuCorreo(usuariosDto.getCorreo());
                Respuesta respuestaCorreo =
                correosService.enviarCorreoActivacion(usuarioRest);
            }

            // Aseguramos que los cambios se confirmen en la base de datos
            em.flush();

            // Log para confirmar que el usuario fue guardado correctamente
            LOG.log(Level.INFO, "Usuario guardado correctamente: {0}", usuarios);

            // Retornamos la respuesta de éxito
            return new Respuesta(true, CodigoRespuesta.CORRECTO,
                    "El usuario se guardó correctamente", "", "Usuario",
                    new UsuariosDto(usuarios));
        } catch (

        NoResultException ex) {
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

    public Respuesta getUserByMail(String correo) {
        try {

            Query query = em.createNamedQuery("Usuarios.findByCorreo", Usuarios.class);
            query.setParameter("correo", correo);
            Usuarios usuarios = (Usuarios) query.getSingleResult();
            UsuariosDto usuariosDto = new UsuariosDto(usuarios);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", usuariosDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                    "No existe un usuario con el correo ingresado.", "getUserByMail NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.",
                    "getUserByMail NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.",
                    "getUserByMail " + ex.getMessage());
        }
    }

    private void activateUser(UsuariosDto usuarios) {

        try {

            Request request = new Request("CorreosController/correos/enviarActivacion");
            request.post(usuarios);

            if (request.isError()) {
                LOG.log(Level.SEVERE, "Error al activar el usuario: {0}", request.getError());
            }

            UsuariosDto usuario = (UsuariosDto) request.readEntity(UsuariosDto.class);
            LOG.log(Level.INFO, "Usuario activado correctamente: {0}", usuario);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al activar el usuario.", ex);
        }

    }

}
