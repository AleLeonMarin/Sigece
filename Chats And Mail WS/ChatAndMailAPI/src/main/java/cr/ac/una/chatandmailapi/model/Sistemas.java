package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "SIS_SISTEMAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisSistemas.findAll", query = "SELECT s FROM SisSistemas s"),
    @NamedQuery(name = "SisSistemas.findBySisId", query = "SELECT s FROM SisSistemas s WHERE s.sisId = :sisId"),
    @NamedQuery(name = "SisSistemas.findBySisNombre", query = "SELECT s FROM SisSistemas s WHERE s.sisNombre = :sisNombre"),
    @NamedQuery(name = "SisSistemas.findBySisVersion", query = "SELECT s FROM SisSistemas s WHERE s.sisVersion = :sisVersion")})
public class Sistemas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIS_ID")
    private Long sisId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "SIS_NOMBRE")
    private String sisNombre;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIS_VERSION")
    private Long sisVersion;

    public Sistemas() {
    }

    public Sistemas(Long sisId) {
        this.sisId = sisId;
    }

    public Sistemas(Long sisId, String sisNombre, Long sisVersion) {
        this.sisId = sisId;
        this.sisNombre = sisNombre;
        this.sisVersion = sisVersion;
    }

    public Long getSisId() {
        return sisId;
    }

    public void setSisId(Long sisId) {
        this.sisId = sisId;
    }

    public String getSisNombre() {
        return sisNombre;
    }

    public void setSisNombre(String sisNombre) {
        this.sisNombre = sisNombre;
    }

    public Long getSisVersion() {
        return sisVersion;
    }

    public void setSisVersion(Long sisVersion) {
        this.sisVersion = sisVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sisId != null ? sisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sistemas)) {
            return false;
        }
        Sistemas other = (Sistemas) object;
        if ((this.sisId == null && other.sisId != null) || (this.sisId != null && !this.sisId.equals(other.sisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.SisSistemas[ sisId=" + sisId + " ]";
    }
    
}
