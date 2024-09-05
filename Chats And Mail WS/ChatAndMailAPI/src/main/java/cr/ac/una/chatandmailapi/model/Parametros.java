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
@Table(name = "SIS_PARAMETROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametros.findAll", query = "SELECT s FROM Parametros s"),
    @NamedQuery(name = "Parametros.findByParId", query = "SELECT s FROM Parametros s WHERE s.parId = :parId"),
    @NamedQuery(name = "Parametros.findByParCorreo", query = "SELECT s FROM Parametros s WHERE s.parCorreo = :parCorreo"),
    @NamedQuery(name = "Parametros.findByParClave", query = "SELECT s FROM Parametros s WHERE s.parClave = :parClave"),
    @NamedQuery(name = "Parametros.findByParPuerto", query = "SELECT s FROM Parametros s WHERE s.parPuerto = :parPuerto"),
    @NamedQuery(name = "Parametros.findByParServer", query = "SELECT s FROM Parametros s WHERE s.parServer = :parServer"),
    @NamedQuery(name = "Parametros.findByParProtocolo", query = "SELECT s FROM Parametros s WHERE s.parProtocolo = :parProtocolo"),
    @NamedQuery(name = "Parametros.findByParTimeout", query = "SELECT s FROM Parametros s WHERE s.parTimeout = :parTimeout"),
    @NamedQuery(name = "Parametros.findByParVersion", query = "SELECT s FROM Parametros s WHERE s.parVersion = :parVersion")})
public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAR_ID")
    private Long parId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "PAR_CORREO")
    private String parCorreo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "PAR_CLAVE")
    private String parClave;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAR_PUERTO")
    private Long parPuerto;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "PAR_SERVER")
    private String parServer;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "PAR_PROTOCOLO")
    private String parProtocolo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAR_TIMEOUT")
    private Long parTimeout;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAR_VERSION")
    private Long parVersion;

    public Parametros() {
    }

    public Parametros(Long parId) {
        this.parId = parId;
    }

    public Parametros(Long parId, String parCorreo, String parClave, Long parPuerto, String parServer, String parProtocolo, Long parTimeout, Long parVersion) {
        this.parId = parId;
        this.parCorreo = parCorreo;
        this.parClave = parClave;
        this.parPuerto = parPuerto;
        this.parServer = parServer;
        this.parProtocolo = parProtocolo;
        this.parTimeout = parTimeout;
        this.parVersion = parVersion;
    }

    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParCorreo() {
        return parCorreo;
    }

    public void setParCorreo(String parCorreo) {
        this.parCorreo = parCorreo;
    }

    public String getParClave() {
        return parClave;
    }

    public void setParClave(String parClave) {
        this.parClave = parClave;
    }

    public Long getParPuerto() {
        return parPuerto;
    }

    public void setParPuerto(Long parPuerto) {
        this.parPuerto = parPuerto;
    }

    public String getParServer() {
        return parServer;
    }

    public void setParServer(String parServer) {
        this.parServer = parServer;
    }

    public String getParProtocolo() {
        return parProtocolo;
    }

    public void setParProtocolo(String parProtocolo) {
        this.parProtocolo = parProtocolo;
    }

    public Long getParTimeout() {
        return parTimeout;
    }

    public void setParTimeout(Long parTimeout) {
        this.parTimeout = parTimeout;
    }

    public Long getParVersion() {
        return parVersion;
    }

    public void setParVersion(Long parVersion) {
        this.parVersion = parVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parId != null ? parId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        if ((this.parId == null && other.parId != null) || (this.parId != null && !this.parId.equals(other.parId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.SisParametros[ parId=" + parId + " ]";
    }
    
}
