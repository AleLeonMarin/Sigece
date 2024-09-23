package cr.ac.una.chatandmailapi.service;

import cr.ac.una.chatandmailapi.model.Chats;
import cr.ac.una.chatandmailapi.model.ChatsDTO;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class ChatsService {

    private static final Logger LOG = Logger.getLogger(ChatsService.class.getName());

    @PersistenceContext(unitName = "SigeceUnaWsPU")
    private EntityManager em;

    public Respuesta getChat(Long id) {
        try {
            Query qryChat = em.createNamedQuery("Chats.findByChtId", Chats.class);
            qryChat.setParameter("chtId", id);

            Chats chat = (Chats) qryChat.getSingleResult();
            em.refresh(chat);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Chat", new ChatsDTO(chat));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un chat con el código ingresado.", "getChat NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el chat.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el chat.", "getChat NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el chat.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el chat.", "getChat " + ex.getMessage());
        }
    }

    public Respuesta getChats() {
        try {
            Query qryChats = em.createNamedQuery("Chats.findAll", Chats.class);

            List<Chats> chats = qryChats.getResultList();

            for (Chats chat : chats) {
                em.refresh(chat);
            }

            List<ChatsDTO> chatsDto = new ArrayList<>();
            for (Chats chat : chats) {
                chatsDto.add(new ChatsDTO(chat));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Chats", chatsDto);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los chats.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los chats.", "getChats " + ex.getMessage());
        }
    }

    public Respuesta guardarChat(ChatsDTO chatDto) {
        try {
            Chats chat;
            if (chatDto.getChtId() != null && chatDto.getChtId() > 0) {

                chat = em.find(Chats.class, chatDto.getChtId());
                if (chat == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el chat a modificar.", "guardarChat NoResultException");
                }
                chat.actualizar(chatDto);
                chat = em.merge(chat);
            } else {

                chat = new Chats(chatDto);
                em.persist(chat);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Chat", new ChatsDTO(chat));
        } catch (Exception ex) {
            Logger.getLogger(ChatsService.class.getName()).log(Level.SEVERE, "Ocurrió un error al guardar el chat.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al guardar el chat.", "guardarChat " + ex.getMessage());
        }
    }

    public Respuesta eliminarChat(Long id) {
        try {
            Chats chat = em.find(Chats.class, id);
            if (chat == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el chat a eliminar.", "eliminarChat NoResultException");
            }
            em.remove(chat);
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "Chat eliminado correctamente.", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al eliminar el chat.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al eliminar el chat.", "eliminarChat " + ex.getMessage());
        }
    }

    public Respuesta getChatsByUsuario(Long usuarioId) {
        try {

            Query qryChat = em.createQuery("SELECT c FROM Chats c WHERE c.chtEmisorId.usuId = :usuarioId OR c.chtReceptorId.usuId = :usuarioId", Chats.class);
            qryChat.setParameter("usuarioId", usuarioId);

 
            List<Chats> chats = qryChat.getResultList();

 
            List<ChatsDTO> chatsDto = new ArrayList<>();
            for (Chats chat : chats) {
                chatsDto.add(new ChatsDTO(chat)); 
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Chats", chatsDto);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los chats del usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los chats del usuario.", "getChatsByUsuario " + ex.getMessage());
        }
    }

    public Respuesta getChatsEntreUsuarios(Long idEmisor, Long idReceptor) {
        try {

            Query qryChats = em.createQuery("SELECT c FROM Chats c WHERE (c.chtEmisorId.usuId = :idEmisor AND c.chtReceptorId.usuId = :idReceptor) "
                    + "OR (c.chtEmisorId.usuId = :idReceptor AND c.chtReceptorId.usuId = :idEmisor)", Chats.class);
            qryChats.setParameter("idEmisor", idEmisor);
            qryChats.setParameter("idReceptor", idReceptor);

            List<Chats> chats = qryChats.getResultList();

            for (Chats chat : chats) {
                em.refresh(chat);
            }

            List<ChatsDTO> chatsDto = new ArrayList<>();
            for (Chats chat : chats) {
                chatsDto.add(new ChatsDTO(chat));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Chats", chatsDto);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrió un error al consultar los chats entre usuarios.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrió un error al consultar los chats entre usuarios.", "getChatsEntreUsuarios " + ex.getMessage());
        }
    }

}
