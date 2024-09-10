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
            List<Sistemas> system = query.getResultList(); // No hacemos cast aquí
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

    public Respuesta deleteSystem (Long id){
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
            if (sistemasDto.getId() != null && sistemasDto.getId() > 0) {
                systems = em.find(Sistemas.class, sistemasDto.getId());
                if (systems == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el usuario a modificar.", "saveUser NoResultException");
                }
                systems.actualizar(sistemasDto);
                for (RolesDto rol : sistemasDto.getElminados()) {
                    systems.getRoles().remove(new Roles(rol.getId()));
                }
                if (!sistemasDto.getRolesDto().isEmpty()) {
                    for (RolesDto rol : sistemasDto.getRolesDto()) {
                        if (rol.getModificado()) {
                            Roles roles = em.find(Roles.class, rol.getId());
                            roles.getSistema();
                            systems.getRoles().add(roles);
                        }
                    }
                }
                systems = em.merge(systems);
            } else {
                systems = new Sistemas(sistemasDto);
                em.persist(systems);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TipoPlanilla", new SistemasDto(systems));
        } catch (Exception ex) {
            //LOG.log(Level.SEVERE, "Ocurrio un error al guardar el tipo de planilla.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el tipo de planilla.", "guardarTipoPlanilla " + ex.getMessage());
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

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Sistemas", sistemasDto);
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
