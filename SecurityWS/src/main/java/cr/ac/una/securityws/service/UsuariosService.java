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
            Query query = em.createNamedQuery("Usuarios.findByUsuClave", Usuarios.class);
            query.setParameter("usuario", usuario);
            query.setParameter("clave", clave);
            Usuarios usuarios = (Usuarios) query.getSingleResult();
            UsuariosDto usuariosDto = new UsuariosDto(usuarios);

            for (Roles rol : usuarios.getRoles()) {
                usuariosDto.getRolesDto().add(new RolesDto(rol));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", usuariosDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                    "No existe un usuario con las credenciales ingresadas.", "validateUser NoResultException");
        } catch (NonUniqueResultException ex) {
             LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tipo de planilla.",
             ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO,
                    "Ocurrio un error al consultar el usuario.", "validateUser NonUniqueResultException");
        } catch (Exception ex) {
             LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO,
                    "Ocurrio un error al consultar el usuario.", "validateUser " + ex.getMessage());
        }
    }

    public Respuesta saveUser(UsuariosDto usuariosDto) {
        try {
            Usuarios usuarios;

            if (usuariosDto.getId() != null && usuariosDto.getId() > 0) {
                usuarios = em.find(Usuarios.class, usuariosDto.getId());
                if (usuarios == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                            "No se encontro el usuario a modificar.", "saveUser NoResultException");
                }

                usuarios.actualizar(usuariosDto);
                usuarios = em.merge(usuarios);
            } else {
                usuarios = new Usuarios(usuariosDto);
                em.persist(usuarios);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "El usuario se guardo correctamente", "", "Usuario",
                    new UsuariosDto(usuarios));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el usuario.",
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
            List<Usuarios> usuarios = (List<Usuarios>) query.getResultList();
            List<UsuariosDto> usuariosDto = new ArrayList<>();
            for (Usuarios usuario : usuarios) {
                usuariosDto.add(new UsuariosDto(usuario));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuarios", usuariosDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "","getAllUsers NoResultException",ex);
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar los usuarios.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "","getAllUsers NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar los usuarios.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "","getAllUsers NonUniqueResultException");
        }
    }

}
