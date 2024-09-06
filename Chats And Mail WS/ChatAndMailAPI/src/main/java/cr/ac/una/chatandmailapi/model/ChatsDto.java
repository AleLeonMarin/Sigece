package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kendall Fonseca
 */
public class ChatsDTO implements Serializable {

    private Long chtId;
    private Date chtFecha;
    private Long chtVersion;
    private Long chtReceptorId;
    private Long chtEmisorId;
    private List<MensajesDTO> mensajesList;

    public ChatsDTO() {
    }

    public ChatsDTO(Long chtId, Date chtFecha, Long chtVersion, Long chtReceptorId, Long chtEmisorId) {
        this.chtId = chtId;
        this.chtFecha = chtFecha;
        this.chtVersion = chtVersion;
        this.chtReceptorId = chtReceptorId;
        this.chtEmisorId = chtEmisorId;
    }

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

    public Long getChtReceptorId() {
        return chtReceptorId;
    }

    public void setChtReceptorId(Long chtReceptorId) {
        this.chtReceptorId = chtReceptorId;
    }

    public Long getChtEmisorId() {
        return chtEmisorId;
    }

    public void setChtEmisorId(Long chtEmisorId) {
        this.chtEmisorId = chtEmisorId;
    }

    public List<MensajesDTO> getMensajesList() {
        return mensajesList;
    }

    public void setMensajesList(List<MensajesDTO> mensajesList) {
        this.mensajesList = mensajesList;
    }

    @Override
    public String toString() {
        return "ChatsDTO{" +
                "chtId=" + chtId +
                ", chtFecha=" + chtFecha +
                ", chtVersion=" + chtVersion +
                ", chtReceptorId=" + chtReceptorId +
                ", chtEmisorId=" + chtEmisorId +
                ", mensajesList=" + mensajesList +
                '}';
    }
}
