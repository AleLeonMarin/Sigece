
package cr.ac.una.chatandmailapi.model;

import java.util.Date;

public class MensajesDto {

    private Long smsId;
    private String smsTexto;
    private Date smsTiempo;
    private Long smsUsuIdEmisor;
    private Long smsChatId;
    private Long smsVersion;

    public MensajesDto() {
        // Constructor por defecto
        this.smsTiempo = new Date();  // Inicializa la fecha actual
    }


    public MensajesDto(Long smsId, String smsTexto, Date smsTiempo, Long smsUsuIdEmisor, Long smsChatId, Long smsVersion) {
        this.smsId = smsId;
        this.smsTexto = smsTexto;
        this.smsTiempo = smsTiempo;
        this.smsUsuIdEmisor = smsUsuIdEmisor;
        this.smsChatId = smsChatId;
        this.smsVersion = smsVersion;
    }
    
  
    // Constructor que recibe la entidad de Mensajes
    public MensajesDto(Mensajes mensaje) {
        this();  // Llama al constructor por defecto
        this.smsId = mensaje.getSmsId();
        this.smsTexto = mensaje.getSmsTexto();
        this.smsTiempo = mensaje.getSmsTiempo();
        this.smsUsuIdEmisor = mensaje.getSmsUsuIdEmisor();
        this.smsChatId = mensaje.getSmsChatId();
        this.smsVersion = mensaje.getSmsVersion();
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

    public Long getSmsUsuIdEmisor() {
        return smsUsuIdEmisor;
    }

    public void setSmsUsuIdEmisor(Long smsUsuIdEmisor) {
        this.smsUsuIdEmisor = smsUsuIdEmisor;
    }

    public Long getSmsChatId() {
        return smsChatId;
    }

    public void setSmsChatId(Long smsChatId) {
        this.smsChatId = smsChatId;
    }

    public Long getSmsVersion() {
        return smsVersion;
    }

    public void setSmsVersion(Long smsVersion) {
        this.smsVersion = smsVersion;
    }
}