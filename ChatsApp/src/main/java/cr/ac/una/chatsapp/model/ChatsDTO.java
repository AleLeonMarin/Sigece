package cr.ac.una.chatsapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatsDTO implements Serializable {

    private String chtId;
    private Date chtFecha;
    private Long chtVersion;
    private String emisorId;  // Cambiado a String
    private String receptorId;
    private List<MensajesDTO> mensajesList;

    public ChatsDTO() {
        this.chtId = "";
        this.chtFecha = new Date();
        this.chtVersion = 0L;
        this.emisorId = "";
        this.receptorId = "";
        this.mensajesList = new ArrayList<>();
    }

    public String getChtId() {
        return chtId;
    }

    public void setChtId(String chtId) {
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

    public String getEmisorId() {
        return emisorId;
    }

    public void setEmisorId(String emisorId) {
        this.emisorId = emisorId;
    }

    public String getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(String receptorId) {
        this.receptorId = receptorId;
    }

    public List<MensajesDTO> getMensajesList() {
        if (mensajesList == null) {
            mensajesList = new ArrayList<>();
        }
        return mensajesList;
    }


    public void setMensajesList(List<MensajesDTO> mensajesList) {
        this.mensajesList = mensajesList;
    }
}
