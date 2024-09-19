package cr.ac.una.securityws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import cr.ac.una.securityws.model.Roles;
import cr.ac.una.securityws.model.RolesDto;
import cr.ac.una.securityws.model.Sistemas;
import cr.ac.una.securityws.model.SistemasDto;
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
public class SistemasService {

    private static final Logger LOG = Logger.getLogger(SistemasService.class.getName());
    @PersistenceContext(unitName = "SigeceUnaWsPU")
    private EntityManager em;

    public Respuesta getSistemas() {

        try {
            Query query = em.createNamedQuery("Sistemas.findAll", Sistemas.class);
            List<Sistemas> system = query.getResultList();
            List<SistemasDto> systemsDto = new ArrayList<>();
            for (Sistemas systems : system) {
                SistemasDto systemDto = new SistemasDto(systems);
                systemsDto.add(systemDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Sistemas", systemsDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "", "getSistemas NoResultException", ex);
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE,
                    "Ocurrio un error al consultar los sistemas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "", "getSistemas NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE,
                    "Ocurrio un error al consultar los sistemas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "", "getSistemas NonUniqueResultException");
        }
    }

    public Respuesta deleteSystem(Long id) {
        try {
            Sistemas sistema;
            if (id != null && id > 0) {
                sistema = em.find(Sistemas.class, id);
                if (sistema == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                            "No se encontro el sistema a eliminar.", "deleteSystem NoResultException");
                }

                em.remove(sistema);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el sistema a eliminar.",
                        "deleteSystem NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al eliminar el sistema.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el sistema.",
                    "deleteSystem " + ex.getMessage());
        }
    }

    public Respuesta saveSystem(SistemasDto sistemasDto) {
        try {
            Sistemas systems;

            // Modificar sistema existente
            if (sistemasDto.getId() != null && sistemasDto.getId() > 0) {
                systems = em.find(Sistemas.class, sistemasDto.getId());
                if (systems == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,
                            "No se encontró el sistema a modificar.", "saveSystem NoResultException");
                }

                // Actualiza el sistema con los datos del DTO
                systems.actualizar(sistemasDto);

                // Remover roles eliminados
                for (RolesDto rolDto : sistemasDto.getElminados()) {
                    Roles rol = em.find(Roles.class, rolDto.getId()); // Busca el rol a eliminar
                    if (rol != null) {
                        systems.getRoles().remove(rol); // Remover el rol de la colección del sistema
                    }
                }

                // Agregar o modificar roles asociados
                for (RolesDto rolDto : sistemasDto.getRolesDto()) {
                    if (rolDto.getModificado()) {
                        Roles rol = em.find(Roles.class, rolDto.getId());
                        if (rol != null) {
                            // Si el rol existe, asegúrate de agregarlo correctamente
                            systems.getRoles().add(rol);
                        }
                    } else if (rolDto.getId() == null) {
                        Roles nuevoRol = new Roles(rolDto);
                        em.persist(nuevoRol);
                        systems.getRoles().add(nuevoRol);
                    }
                }

                systems = em.merge(systems); // Merge para actualizar el sistema
            }
            // Crear un nuevo sistema
            else {
                systems = new Sistemas(sistemasDto);
                em.persist(systems); // Persistir el nuevo sistema
            }

            em.flush(); // Asegura que los cambios se escriban a la base de datos
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Sistema", new SistemasDto(systems));

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al guardar el sistema.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al guardar el sistema.",
                    "saveSystem " + ex.getMessage());
        }
    }

    public Respuesta getSystem(Long id) {
        try {
            Query query = em.createNamedQuery("Sistemas.findById", Sistemas.class);
            query.setParameter("id", id);
            Sistemas sistemas = (Sistemas) query.getSingleResult();
            SistemasDto sistemasDto = new SistemasDto(sistemas);

            for (Roles rol : sistemas.getRoles()) {
                sistemasDto.getRolesDto().add(new RolesDto(rol));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Sistema", sistemasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "", "getSystem NoResultException", ex);
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE,
                    "Ocurrio un error al consultar los sistemas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "", "getSystem NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE,
                    "Ocurrio un error al consultar los sistemas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "", "getSystem NonUniqueResultException");
        }
    }

}
