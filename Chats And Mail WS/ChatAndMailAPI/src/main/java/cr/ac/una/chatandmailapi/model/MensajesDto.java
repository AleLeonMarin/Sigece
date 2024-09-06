package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kendall Fonseca
 */
public class MensajesDTO {
    private Long smsId;
    private String smsTexto;
    private Date smsTiempo;
    private Long smsVersion;
    private Long chatId;
    private Long emisorId;

    // Getters y Setters

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

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getEmisorId() {
        return emisorId;
    }

    public void setEmisorId(Long emisorId) {
        this.emisorId = emisorId;
    }

    // hashCode

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (smsId != null ? smsId.hashCode() : 0);
        return hash;
    }

    // equals

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

    // toString

    @Override
    public String toString() {
        return "MensajesDto{" +
                "smsId=" + smsId +
                ", smsTexto='" + smsTexto + '\'' +
                ", smsTiempo=" + smsTiempo +
                ", smsVersion=" + smsVersion +
                ", chatId=" + chatId +
                ", emisorId=" + emisorId +
                '}';
    }
}

