package cr.ac.una.chatsapp.model;

import javafx.beans.property.SimpleStringProperty;
import javassist.Loader;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.Date;

/**
 * DTO para la entidad Mensajes.
 */
public class MensajesDTO implements Serializable {

    private String smsId;
    private String smsTexto;
    private Date smsTiempo;
    private Long smsVersion;
    private ChatsDTO chatId;
    private UsuariosDTO emisorId;

    // Constructor vacío
    public MensajesDTO() {
        this.smsId = "";
        this.smsTexto = "";
        this.smsTiempo = new Date();
        this.chatId = new ChatsDTO();
        this.emisorId = new UsuariosDTO();
    }


    public Long getSmsId() {
        return smsId != null && !smsId.isEmpty() ? Long.parseLong(smsId) : null;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId != null ? smsId.toString() : "";
    }

    public String getSmsTexto() {
        return smsTexto;
    }

    public void setSmsTexto(String smsTexto) {
        this.smsTexto = smsTexto;
    }

    public Date getSmsTiempo() {
        return smsTiempo;
    }

    public void setSmsTiempo(Date smsTiempo) {
        this.smsTiempo = smsTiempo;
    }

    public Long getSmsVersion() {
        return smsVersion;
    }

    public void setSmsVersion(Long smsVersion) {
        this.smsVersion = smsVersion;
    }

    public ChatsDTO getChatId() {
        return chatId;
    }

    public void setChatId(ChatsDTO chatId) {
        this.chatId = chatId;
    }

    public UsuariosDTO getEmisorId() {
        return emisorId;
    }

    public void setEmisorId(UsuariosDTO emisorId) {
        this.emisorId = emisorId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (smsId != null ? smsId.hashCode() : 0);
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
        MensajesDTO other = (MensajesDTO) obj;
        return (this.smsId != null && this.smsId.equals(other.smsId));
    }

    @Override
    public String toString() {
        return "MensajesDTO{" +
                "smsId=" + smsId +
                ", smsTexto='" + smsTexto + '\'' +
                ", smsTiempo=" + smsTiempo +
                ", smsVersion=" + smsVersion +
                ", chatId=" + chatId +
                ", emisorId=" + emisorId +
                '}';
    }
}
