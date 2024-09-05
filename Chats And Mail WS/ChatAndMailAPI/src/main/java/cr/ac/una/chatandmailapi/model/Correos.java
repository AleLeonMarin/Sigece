package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kendall Fonseca
 */
@Entity
@Table(name = "SIS_CORREOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Correos.findAll", query = "SELECT s FROM Correos s"),
    @NamedQuery(name = "Correos.findByCorId", query = "SELECT s FROM Correos s WHERE s.corId = :corId"),
    @NamedQuery(name = "Correos.findByCorAsunto", query = "SELECT s FROM Correos s WHERE s.corAsunto = :corAsunto"),
    @NamedQuery(name = "Correos.findByCorDestinatario", query = "SELECT s FROM Correos s WHERE s.corDestinatario = :corDestinatario"),
    @NamedQuery(name = "Correos.findByCorEstado", query = "SELECT s FROM Correos s WHERE s.corEstado = :corEstado"),
    @NamedQuery(name = "Correos.findByCorFecha", query = "SELECT s FROM Correos s WHERE s.corFecha = :corFecha"),
    @NamedQuery(name = "Correos.findByCorNotId", query = "SELECT s FROM Correos s WHERE s.corNotId = :corNotId"),
    @NamedQuery(name = "Correos.findByCorVersion", query = "SELECT s FROM Correos s WHERE s.corVersion = :corVersion")})
public class Correos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COR_ID")
    private Long corId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "COR_ASUNTO")
    private String corAsunto;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "COR_DESTINATARIO")
    private String corDestinatario;

    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "COR_RESULTADO")
    private String corResultado;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "COR_ESTADO")
    private String corEstado;

    @Basic(optional = false)
    @NotNull
    @Column(name = "COR_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date corFecha;

    @Basic(optional = false)
    @NotNull
    @Column(name = "COR_NOT_ID")
    private Long corNotId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "COR_VERSION")
    private Long corVersion;

    public Correos() {
    }

    public Correos(Long corId) {
        this.corId = corId;
    }

    public Correos(Long corId, String corAsunto, String corDestinatario, String corResultado, String corEstado, Date corFecha, Long corNotId, Long corVersion) {
        this.corId = corId;
        this.corAsunto = corAsunto;
        this.corDestinatario = corDestinatario;
        this.corResultado = corResultado;
        this.corEstado = corEstado;
        this.corFecha = corFecha;
        this.corNotId = corNotId;
        this.corVersion = corVersion;
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
    public int hashCode() {
        int hash = 0;
        hash += (corId != null ? corId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Correos)) {
            return false;
        }
        Correos other = (Correos) object;
        if ((this.corId == null && other.corId != null) || (this.corId != null && !this.corId.equals(other.corId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.Correos[ corId=" + corId + " ]";
    }
}