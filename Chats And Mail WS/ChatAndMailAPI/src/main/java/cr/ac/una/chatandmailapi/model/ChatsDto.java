

/**
 *
 * @author Kendall Fonseca
 */
package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kendall Fonseca
 */
public class ChatsDTO{
    private Long chtId;
    private Date chtFecha;
    private Long chtVersion;
    private Long receptorId;
    private Long emisorId;
    private List<MensajesDTO> mensajesList;  // Asumiendo que Mensajes tiene su propio DTO

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

    public List<MensajesDto> getMensajesList() {
        return mensajesList;
    }

    public void setMensajesList(List<MensajesDto> mensajesList) {
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
        return "ChatsDto{" +
                "chtId=" + chtId +
                ", chtFecha=" + chtFecha +
                ", chtVersion=" + chtVersion +
                ", receptorId=" + receptorId +
                ", emisorId=" + emisorId +
                ", mensajesList=" + mensajesList +
                '}';
    }
}
