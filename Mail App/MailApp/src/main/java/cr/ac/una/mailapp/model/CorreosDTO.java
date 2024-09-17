package cr.ac.una.mailapp.model;

import java.io.Serializable;
import java.util.Date;

public class CorreosDTO implements Serializable {
    private Long corId;
    private String corDestinatario;
    private String corAsunto;
    private String corResultado;
    private Date corFecha;
    private NotificacionDTO corNotId;
    private Long corVersion;

    public CorreosDTO() {}

    public CorreosDTO(Long corId, String corDestinatario, String corAsunto, String corResultado, Date corFecha, NotificacionDTO corNotId, Long corVersion) {
        this.corId = corId;
        this.corDestinatario = corDestinatario;
        this.corAsunto = corAsunto;
        this.corResultado = corResultado;
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

    public String getCorDestinatario() {
        return corDestinatario;
    }

    public void setCorDestinatario(String corDestinatario) {
        this.corDestinatario = corDestinatario;
    }

    public String getCorAsunto() {
        return corAsunto;
    }

    public void setCorAsunto(String corAsunto) {
        this.corAsunto = corAsunto;
    }

    public String getCorResultado() {
        return corResultado;
    }

    public void setCorResultado(String corResultado) {
        this.corResultado = corResultado;
    }

    public Date getCorFecha() {
        return corFecha;
    }

    public void setCorFecha(Date corFecha) {
        this.corFecha = corFecha;
    }

    public NotificacionDTO getCorNotId() {
        return corNotId;
    }

    public void setCorNotId(NotificacionDTO corNotId) {
        this.corNotId = corNotId;
    }

    public Long getCorVersion() {
        return corVersion;
    }

    public void setCorVersion(Long corVersion) {
        this.corVersion = corVersion;
    }

    @Override
    public int hashCode() {
        return corId != null ? corId.hashCode() : 0;
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
        return corId != null ? corId.equals(other.corId) : other.corId == null;
    }

    @Override
    public String toString() {
        return "CorreosDTO{" +
                "corId=" + corId +
                ", corDestinatario='" + corDestinatario + '\'' +
                ", corAsunto='" + corAsunto + '\'' +
                ", corResultado='" + corResultado + '\'' +
                ", corFecha=" + corFecha +
                ", corNotId=" + corNotId +
                ", corVersion=" + corVersion +
                '}';
    }
}
