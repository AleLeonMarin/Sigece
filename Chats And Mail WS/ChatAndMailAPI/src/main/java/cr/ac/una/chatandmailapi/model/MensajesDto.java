package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kendall Fonseca
 */
public class MensajesDTO implements Serializable {

    private Long smsId;
    private String smsTexto;
    private Date smsTiempo;
    private Long smsVersion;
    private Long smsChatId;
    private Long smsUsuIdEmisor;

    public MensajesDTO() {
    }

    public MensajesDTO(Long smsId, String smsTexto, Date smsTiempo, Long smsVersion, Long smsChatId, Long smsUsuIdEmisor) {
        this.smsId = smsId;
        this.smsTexto = smsTexto;
        this.smsTiempo = smsTiempo;
        this.smsVersion = smsVersion;
        this.smsChatId = smsChatId;
        this.smsUsuIdEmisor = smsUsuIdEmisor;
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

    public Long getSmsChatId() {
        return smsChatId;
    }

    public void setSmsChatId(Long smsChatId) {
        this.smsChatId = smsChatId;
    }

    public Long getSmsUsuIdEmisor() {
        return smsUsuIdEmisor;
    }

    public void setSmsUsuIdEmisor(Long smsUsuIdEmisor) {
        this.smsUsuIdEmisor = smsUsuIdEmisor;
    }

    @Override
    public String toString() {
        return "MensajesDTO{" +
                "smsId=" + smsId +
                ", smsTexto='" + smsTexto + '\'' +
                ", smsTiempo=" + smsTiempo +
                ", smsVersion=" + smsVersion +
                ", smsChatId=" + smsChatId +
                ", smsUsuIdEmisor=" + smsUsuIdEmisor +
                '}';
    }
}
