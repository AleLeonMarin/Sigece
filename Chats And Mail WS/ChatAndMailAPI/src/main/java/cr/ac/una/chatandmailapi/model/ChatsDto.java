package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DTO para la entidad Chats.
 */
public class ChatsDTO implements Serializable {

    private Long chtId;
    private Date chtFecha;
    private Long chtVersion;
    private Long receptorId;
    private Long emisorId;
    private List<MensajesDTO> mensajesList;

    // Constructor vacío
    public ChatsDTO() {}

    // Constructor que recibe la entidad Chats
    public ChatsDTO(Chats chat) {
        this.chtId = chat.getChtId();
        this.chtFecha = chat.getChtFecha();
        this.chtVersion = chat.getChtVersion();
        this.receptorId = chat.getChtReceptorId().getUsuId();  // Asignación del receptor por ID
        this.emisorId = chat.getChtEmisorId().getUsuId();      // Asignación del emisor por ID

        // Convertir la lista de Mensajes a MensajesDTO
        if (chat.getSisMensajesList() != null) {
            this.mensajesList = new ArrayList<>();
            for (Mensajes mensaje : chat.getSisMensajesList()) {
                this.mensajesList.add(new MensajesDTO(mensaje));  // Conversión de cada mensaje
            }
        }
    }

    // Getters y Setters
    public Long getChtId() {
        return chtId;
    }

    public void setChtId(Long chtId) {
        this.chtId = chtId;
    }

    public Date getChtFecha() {
        return chtFecha;
    }

    public void setChtFecha(Date chtFecha) {
        this.chtFecha = chtFecha;
    }

    public Long getChtVersion() {
        return chtVersion;
    }

    public void setChtVersion(Long chtVersion) {
        this.chtVersion = chtVersion;
    }

    public Long getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(Long receptorId) {
        this.receptorId = receptorId;
    }

    public Long getEmisorId() {
        return emisorId;
    }

    public void setEmisorId(Long emisorId) {
        this.emisorId = emisorId;
    }

    public List<MensajesDTO> getMensajesList() {
        return mensajesList;
    }

    public void setMensajesList(List<MensajesDTO> mensajesList) {
        this.mensajesList = mensajesList;
    }

    // hashCode, equals, toString

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (chtId != null ? chtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChatsDTO other = (ChatsDTO) obj;
        return (this.chtId != null && this.chtId.equals(other.chtId));
    }

    @Override
    public String toString() {
        return "ChatsDTO{" +
                "chtId=" + chtId +
                ", chtFecha=" + chtFecha +
                ", chtVersion=" + chtVersion +
                ", receptorId=" + receptorId +
                ", emisorId=" + emisorId +
                ", mensajesList=" + mensajesList +
                '}';
    }
}
