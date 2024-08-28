package cr.ac.una.chatandmailapi.model;

import java.util.Date;

/**
 * DTO (Data Transfer Object) para la entidad Correos.
 */
public class CorreosDto {

    private Long corId;
    private String corAsunto;
    private String corDestinatario;
    private String corResultado;
    private String corEstado;
    private Date corFecha;
    private Long corNotId;
    private Long corVersion;

    public CorreosDto() {
    }

    public CorreosDto(Long corId, String corAsunto, String corDestinatario, String corResultado, String corEstado, Date corFecha, Long corNotId, Long corVersion) {
        this.corId = corId;
        this.corAsunto = corAsunto;
        this.corDestinatario = corDestinatario;
        this.corResultado = corResultado;
        this.corEstado = corEstado;
        this.corFecha = corFecha;
        this.corNotId = corNotId;
        this.corVersion = corVersion;
    }

    // Getters y Setters
    public Long getCorId() {
        return corId;
    }

    public void setCorId(Long corId) {
        this.corId = corId;
    }

    public String getCorAsunto() {
        return corAsunto;
    }

    public void setCorAsunto(String corAsunto) {
        this.corAsunto = corAsunto;
    }

    public String getCorDestinatario() {
        return corDestinatario;
    }

    public void setCorDestinatario(String corDestinatario) {
        this.corDestinatario = corDestinatario;
    }

    public String getCorResultado() {
        return corResultado;
    }

    public void setCorResultado(String corResultado) {
        this.corResultado = corResultado;
    }

    public String getCorEstado() {
        return corEstado;
    }

    public void setCorEstado(String corEstado) {
        this.corEstado = corEstado;
    }

    public Date getCorFecha() {
        return corFecha;
    }

    public void setCorFecha(Date corFecha) {
        this.corFecha = corFecha;
    }

    public Long getCorNotId() {
        return corNotId;
    }

    public void setCorNotId(Long corNotId) {
        this.corNotId = corNotId;
    }

    public Long getCorVersion() {
        return corVersion;
    }

    public void setCorVersion(Long corVersion) {
        this.corVersion = corVersion;
    }

    @Override
    public String toString() {
        return "CorreosDto{" +
                "corId=" + corId +
                ", corAsunto='" + corAsunto + '\'' +
                ", corDestinatario='" + corDestinatario + '\'' +
                ", corResultado='" + corResultado + '\'' +
                ", corEstado='" + corEstado + '\'' +
                ", corFecha=" + corFecha +
                ", corNotId=" + corNotId +
                ", corVersion=" + corVersion +
                '}';
    }
}