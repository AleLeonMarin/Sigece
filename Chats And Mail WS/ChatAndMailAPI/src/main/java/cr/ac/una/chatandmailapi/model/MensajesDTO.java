package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;
import java.util.Date;


public class MensajesDTO implements Serializable {

    private Long smsId;
    private String smsTexto;
    private Date smsTiempo;
    private Long smsVersion;
    private Chats chatId;
    private Usuarios emisorId;

    public MensajesDTO() {}

    public MensajesDTO(Mensajes mensaje) {
        this.smsId = mensaje.getSmsId();
        this.smsTexto = mensaje.getSmsTexto();
        this.smsTiempo = mensaje.getSmsTiempo();
        this.smsVersion = mensaje.getSmsVersion();
        this.chatId = mensaje.getSmsChatId(); 
        this.emisorId = mensaje.getSmsUsuIdEmisor(); 
    }

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
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

    public Chats getChatId() {
        return chatId;
    }

    public void setChatId(Chats chatId) {
        this.chatId = chatId;
    }

    public Usuarios getEmisorId() {
        return emisorId;
    }

    public void setEmisorId(Usuarios emisorId) {
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
