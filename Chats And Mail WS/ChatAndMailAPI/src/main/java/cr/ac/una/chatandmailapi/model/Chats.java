package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kendall Fonseca
 */
@Entity
@Table(name = "SIS_CHATS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisChats.findAll", query = "SELECT s FROM SisChats s"),
    @NamedQuery(name = "SisChats.findByChtId", query = "SELECT s FROM SisChats s WHERE s.chtId = :chtId"),
    @NamedQuery(name = "SisChats.findByChtFecha", query = "SELECT s FROM SisChats s WHERE s.chtFecha = :chtFecha"),
    @NamedQuery(name = "SisChats.findByChtVersion", query = "SELECT s FROM SisChats s WHERE s.chtVersion = :chtVersion")})
public class Chats implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHT_ID")
    private Long chtId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHT_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chtFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHT_VERSION")
    private Long chtVersion;
    @JoinColumn(name = "CHT_RECEPTOR_ID", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false)
    private Usuarios chtReceptorId;
    @JoinColumn(name = "CHT_EMISOR_ID", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false)
    private Usuarios chtEmisorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smsChatId")
    private List<Mensajes> sisMensajesList;

    public Chats() {
    }

    public Chats(Long chtId) {
        this.chtId = chtId;
    }

    public Chats(Long chtId, Date chtFecha, Long chtVersion) {
        this.chtId = chtId;
        this.chtFecha = chtFecha;
        this.chtVersion = chtVersion;
    }

    public Long getChtId() {
        return chtId;
    }

    public void setChtId(Long chtId) {
        this.chtId = chtId;
    }

    public Date getChtFecha() {
        return chtFecha;
    }

    public void setChtFecha(Date chtFecha) {
        this.chtFecha = chtFecha;
    }

    public Long getChtVersion() {
        return chtVersion;
    }

    public void setChtVersion(Long chtVersion) {
        this.chtVersion = chtVersion;
    }

    public Usuarios getChtReceptorId() {
        return chtReceptorId;
    }

    public void setChtReceptorId(Usuarios chtReceptorId) {
        this.chtReceptorId = chtReceptorId;
    }

    public Usuarios getChtEmisorId() {
        return chtEmisorId;
    }

    public void setChtEmisorId(Usuarios chtEmisorId) {
        this.chtEmisorId = chtEmisorId;
    }

    @XmlTransient
    public List<Mensajes> getSisMensajesList() {
        return sisMensajesList;
    }

    public void setSisMensajesList(List<Mensajes> sisMensajesList) {
        this.sisMensajesList = sisMensajesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chtId != null ? chtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Chats)) {
            return false;
        }
        Chats other = (Chats) object;
        return !((this.chtId == null && other.chtId != null) || (this.chtId != null && !this.chtId.equals(other.chtId)));
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.SisChats[ chtId=" + chtId + " ]";
    }
    
}
