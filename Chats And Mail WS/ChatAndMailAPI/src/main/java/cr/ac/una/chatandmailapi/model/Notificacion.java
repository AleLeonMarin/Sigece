package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Kendall Fonseca
 */
@Entity
@Table(name = "SIS_NOTIFICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT s FROM Notificacion s"),
    @NamedQuery(name = "Notificacion.findByNotId", query = "SELECT s FROM Notificacion s WHERE s.notId = :notId"),
    @NamedQuery(name = "Notificacion.findByNotNombre", query = "SELECT s FROM Notificacion s WHERE s.notNombre = :notNombre"),
    @NamedQuery(name = "Notificacion.findByNotVersion", query = "SELECT s FROM Notificacion s WHERE s.notVersion = :notVersion")})
public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOT_ID")
    private Long notId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "NOT_NOMBRE")
    private String notNombre;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "NOT_PLANTILLA")
    private String notPlantilla;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOT_VERSION")
    private Long notVersion;

    public Notificacion() {
    }

    public Notificacion(Long notId) {
        this.notId = notId;
    }

    public Notificacion(Long notId, String notNombre, String notPlantilla, Long notVersion) {
        this.notId = notId;
        this.notNombre = notNombre;
        this.notPlantilla = notPlantilla;
        this.notVersion = notVersion;
    }

    public Long getNotId() {
        return notId;
    }

    public void setNotId(Long notId) {
        this.notId = notId;
    }

    public String getNotNombre() {
        return notNombre;
    }

    public void setNotNombre(String notNombre) {
        this.notNombre = notNombre;
    }

    public String getNotPlantilla() {
        return notPlantilla;
    }

    public void setNotPlantilla(String notPlantilla) {
        this.notPlantilla = notPlantilla;
    }

    public Long getNotVersion() {
        return notVersion;
    }

    public void setNotVersion(Long notVersion) {
        this.notVersion = notVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notId != null ? notId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.notId == null && other.notId != null) || (this.notId != null && !this.notId.equals(other.notId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.SisNotificacion[ notId=" + notId + " ]";
    }
    
}
