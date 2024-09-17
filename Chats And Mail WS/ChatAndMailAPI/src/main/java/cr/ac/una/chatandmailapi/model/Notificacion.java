package cr.ac.una.chatandmailapi.model;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SIS_NOTIFICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT s FROM Notificacion s"),
    @NamedQuery(name = "Notificacion.findByNotId", query = "SELECT s FROM Notificacion s WHERE s.notId = :notId"),
    @NamedQuery(name = "Notificacion.findByNotNombre", query = "SELECT s FROM Notificacion s WHERE s.notNombre = :notNombre"),
    @NamedQuery(name = "Notificacion.findByNotVersion", query = "SELECT s FROM Notificacion s WHERE s.notVersion = :notVersion")
})
public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sis_notificacion_seq")
    @SequenceGenerator(name = "sis_notificacion_seq", sequenceName = "SIS_NOTIFICACION_SEQ01", allocationSize = 1)
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "varNotId")
    private List<Variables> sisVariablesList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "corNotId")
    @XmlTransient
    @JsonbTransient 
    private List<Correos> sisCorreosList;

    public Notificacion() {
    }

    public Notificacion(Long notId) {
        this.notId = notId;
    }
    
    public Notificacion(NotificacionDTO notificacionDto) {
    this.notId = notificacionDto.getNotId();
    actualizar(notificacionDto);
}

public void actualizar(NotificacionDTO notificacionDto) {
    this.notNombre = notificacionDto.getNotNombre();
    this.notPlantilla = notificacionDto.getNotPlantilla();
    this.notVersion = notificacionDto.getNotVersion();
    
    if (notificacionDto.getSisVariablesList() != null) {
        this.sisVariablesList = new ArrayList<>(notificacionDto.getSisVariablesList());
    }

    if (notificacionDto.getSisCorreosList() != null) {
        this.sisCorreosList = new ArrayList<>(notificacionDto.getSisCorreosList());
    }
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

    @XmlTransient
    public List<Variables> getSisVariablesList() {
        return sisVariablesList;
    }

    public void setSisVariablesList(List<Variables> sisVariablesList) {
        this.sisVariablesList = sisVariablesList;
    }

    @XmlTransient
    public List<Correos> getSisCorreosList() {
        return sisCorreosList;
    }

    public void setSisCorreosList(List<Correos> sisCorreosList) {
        this.sisCorreosList = sisCorreosList;
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
        return "cr.ac.una.chatandmailapi.model.Notificacion[ notId=" + notId + " ]";
    }
}
