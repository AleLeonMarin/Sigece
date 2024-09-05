package cr.ac.una.chatandmailapi.service;

import cr.ac.una.chatandmailapi.model.Chats;
import cr.ac.una.chatandmailapi.model.ChatsDto;
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

    @PersistenceContext(unitName = "ChatAndMailWsPU")
    private EntityManager em;

    // Obtener un chat por ID
    public Respuesta getChat(Long id) {
        try {
            Query qryChat = em.createNamedQuery("SisChats.findByChtId", Chats.class);
            qryChat.setParameter("chtId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Chat", new ChatsDto((Chats) qryChat.getSingleResult()));

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

    // Obtener todos los chats
    public Respuesta getChats() {
        try {
            Query qryChats = em.createNamedQuery("SisChats.findAll", Chats.class);
            List<Chats> chats = qryChats.getResultList();
            List<ChatsDto> chatsDto = new ArrayList<>();
            for (Chats chat : chats) {
                chatsDto.add(new ChatsDto(chat.getChtId(), chat.getChtFecha(), chat.getChtEmisorId(), chat.getChtReceptorId(), chat.getChtVersion()));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Chats", chatsDto);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los chats.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los chats.", "getChats " + ex.getMessage());
        }
    }

    // Guardar o actualizar un chat
    public Respuesta guardarChat(ChatsDto chatDto) {
        try {
            Chats chat;
            if (chatDto.getChtId() != null && chatDto.getChtId() > 0) {
                chat = em.find(Chats.class, chatDto.getChtId());
                if (chat == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el chat a modificar.", "guardarChat NoResultException");
                }
                chat.setChtFecha(chatDto.getChtFecha());
                chat.setChtEmisorId(chatDto.getChtEmisorId());
                chat.setChtReceptorId(chatDto.getChtReceptorId());
                chat.setChtVersion(chatDto.getChtVersion());
                chat = em.merge(chat);
            } else {
                chat = new Chats(chatDto.getChtId(), chatDto.getChtFecha(), chatDto.getChtEmisorId(), chatDto.getChtReceptorId(), chatDto.getChtVersion());
                em.persist(chat);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Chat", new ChatsDto(chat));

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el chat.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el chat.", "guardarChat " + ex.getMessage());
        }
    }

    // Eliminar un chat
    public Respuesta eliminarChat(Long id) {
        try {
            Chats chat;
            if (id != null && id > 0) {
                chat = em.find(Chats.class, id);
                if (chat == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el chat a eliminar.", "eliminarChat NoResultException");
                }
                em.remove(chat);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el chat a eliminar.", "eliminarChat NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al eliminar el chat.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el chat.", "eliminarChat " + ex.getMessage());
        }
    }

    // Buscar chats por emisor o receptor
    public Respuesta getChatsByUsuario(Long usuarioId) {
        try {
            Query qryChat = em.createQuery("SELECT c FROM Chats c WHERE c.chtEmisorId = :usuarioId OR c.chtReceptorId = :usuarioId", Chats.class);
            qryChat.setParameter("usuarioId", usuarioId);
            List<Chats> chats = qryChat.getResultList();
            List<ChatsDto> chatsDto = new ArrayList<>();
            for (Chats chat : chats) {
                chatsDto.add(new ChatsDto(chat.getChtId(), chat.getChtFecha(), chat.getChtEmisorId(), chat.getChtReceptorId(), chat.getChtVersion()));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Chats", chatsDto);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los chats del usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los chats del usuario.", "getChatsByUsuario " + ex.getMessage());
        }
    }
}
