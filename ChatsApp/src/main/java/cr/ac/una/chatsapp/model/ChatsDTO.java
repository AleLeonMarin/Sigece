package cr.ac.una.chatsapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatsDTO implements Serializable {

    private String chtId;
    private Date chtFecha;
    private Long chtVersion;
    private UsuariosDTO emisorId;  // Cambiado a UsuariosDTO
    private UsuariosDTO receptorId;
    private List<MensajesDTO> mensajesList;

    public ChatsDTO() {
        this.chtId = "";
        this.chtFecha = new Date();
        this.chtVersion = 0L;
        this.emisorId = new UsuariosDTO();
        this.receptorId = new UsuariosDTO();
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

    public UsuariosDTO getEmisorId() {
        return emisorId;
    }

    public void setEmisorId(UsuariosDTO emisorId) {
        this.emisorId = emisorId;
    }

    public UsuariosDTO getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(UsuariosDTO receptorId) {
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
