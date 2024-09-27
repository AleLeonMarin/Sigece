package cr.ac.una.chatandmailapi.service;

import cr.ac.una.chatandmailapi.model.Usuarios;
import cr.ac.una.chatandmailapi.model.UsuariosDTO;
import cr.ac.una.chatandmailapi.util.CodigoRespuesta;
import cr.ac.una.chatandmailapi.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class UsuariosService {
    
     private static final Logger LOG = Logger.getLogger(UsuariosService.class.getName());

    @PersistenceContext(unitName = "SigeceUnaWsPU")
    private EntityManager em;
    
     public Respuesta getUsuario(Long id) {
    try {
        Query qryUsuario = em.createNamedQuery("Usuarios.findByUsuId", Usuarios.class);
        qryUsuario.setParameter("usuId", id);

        Usuarios usuario = (Usuarios) qryUsuario.getSingleResult();
        if (usuario == null) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el usuario con el ID proporcionado.", "getUsuario NoResultException");
        }
        return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new UsuariosDTO(usuario));
    } catch (NoResultException ex) {
        return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el usuario con el ID proporcionado.", "getUsuario NoResultException");
    } catch (NonUniqueResultException ex) {
        LOG.log(Level.SEVERE, "Se encontró más de un resultado para el ID proporcionado.", ex);
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Se encontró más de un resultado para el ID proporcionado.", "getUsuario NonUniqueResultException");
    } catch (Exception ex) {
        LOG.log(Level.SEVERE, "Ocurrió un error al consultar el usuario.", ex);
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al consultar el usuario.", "getUsuario " + ex.getMessage());
    }
}
public Respuesta getAllUsuarios() {
    try {
        Query qryUsuarios = em.createNamedQuery("Usuarios.findAll", Usuarios.class);
        List<Usuarios> usuarios = qryUsuarios.getResultList();
        List<UsuariosDTO> usuariosDto = new ArrayList<>();

        for (Usuarios usuario : usuarios) {
           UsuariosDTO usuarioDTO = new UsuariosDTO(usuario);
        if (usuario.getUsuFoto() != null) {
                usuarioDTO.setUsuFotoBase64(Base64.getEncoder().encodeToString(usuario.getUsuFoto()));
            }


            usuariosDto.add(usuarioDTO);
        }

        return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuarios", usuariosDto);

    } catch (NoResultException ex) {
        return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontraron usuarios.", "getAllUsuarios NoResultException");
    } catch (Exception ex) {
        LOG.log(Level.SEVERE, "Ocurrió un error al consultar los usuarios.", ex);
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al consultar los usuarios.", "getAllUsuarios " + ex.getMessage());
    }
}


public Respuesta activarUsuario(String nombreUsuario) {
    try {
        Query query = em.createQuery("SELECT u FROM Usuarios u WHERE u.usuUsuario = :usuUsuario", Usuarios.class);
        query.setParameter("usuUsuario", nombreUsuario);

        Usuarios usuario = (Usuarios) query.getSingleResult();
        if (usuario == null) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el usuario con el nombre proporcionado.", "activarUsuario NoResultException");
        }

        // Cambiar el estado del usuario a 'Activo' (por ejemplo, dependiendo de la lógica de activación)
        usuario.setUsuEstado("A");  // Cambia el estado a Activo
        em.merge(usuario);  // Persistir el cambio en la base de datos
        em.flush();  // Asegurar que los cambios se envíen

        return new Respuesta(true, CodigoRespuesta.CORRECTO, "Usuario activado exitosamente.", "", "Usuario", new UsuariosDTO(usuario));
    } catch (NoResultException ex) {
        return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el usuario con el nombre proporcionado.", "activarUsuario NoResultException");
    } catch (Exception ex) {
        LOG.log(Level.SEVERE, "Ocurrió un error al activar el usuario.", ex);
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al activar el usuario.", "activarUsuario " + ex.getMessage());
    }
}


}


     

