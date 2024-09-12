package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO para la entidad Correos.
 */
public class CorreosDTO implements Serializable {

    private Long corId;
    private String corAsunto;
    private String corDestinatario;
    private String corResultado;
    private String corEstado;
    private Date corFecha;
    private Long corVersion;
    private Notificacion corNotId;

    public CorreosDTO() {}

    public CorreosDTO(Correos correo) {
        this.corId = correo.getCorId();
        this.corAsunto = correo.getCorAsunto();
        this.corDestinatario = correo.getCorDestinatario();
        this.corResultado = correo.getCorResultado();
        this.corEstado = correo.getCorEstado();
        this.corFecha = correo.getCorFecha();
        this.corVersion = correo.getCorVersion();
        this.corNotId = correo.getCorNotId();
    }



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

    public Long getCorVersion() {
        return corVersion;
    }

    public void setCorVersion(Long corVersion) {
        this.corVersion = corVersion;
    }

    public Notificacion getCorNotId() {
        return corNotId;
    }

    public void setCorNotId(Notificacion corNotId) {
        this.corNotId = corNotId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (corId != null ? corId.hashCode() : 0);
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
        CorreosDTO other = (CorreosDTO) obj;
        return (this.corId != null && this.corId.equals(other.corId));
    }

    @Override
    public String toString() {
        return "CorreosDTO{" +
                "corId=" + corId +
                ", corAsunto='" + corAsunto + '\'' +
                ", corDestinatario='" + corDestinatario + '\'' +
                ", corResultado='" + corResultado + '\'' +
                ", corEstado='" + corEstado + '\'' +
                ", corFecha=" + corFecha +
                ", corVersion=" + corVersion +
                ", corNotId=" + corNotId +
                '}';
    }
}
