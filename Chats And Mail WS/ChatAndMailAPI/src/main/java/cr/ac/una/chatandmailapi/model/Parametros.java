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
    @NamedQuery(name = "Parametros.findByParVersion", query = "SELECT s FROM Parametros s WHERE s.parVersion = :parVersion")
})
public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAR_ID")
    private long parId;
    
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
    private long parPuerto;
    
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
    private long parTimeout;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAR_VERSION")
    private long parVersion;

    public Parametros() {
    }

    public Parametros(long parId) {
        this.parId = parId;
    }
    
    public Parametros(ParametrosDTO parametrosDto) {
    this.parId = parametrosDto.getParId();
    actualizar(parametrosDto);
}


  
    public void actualizar(ParametrosDTO parametrosDto) {
    this.parCorreo = parametrosDto.getParCorreo();
    this.parClave = parametrosDto.getParClave();
    this.parPuerto = parametrosDto.getParPuerto();
    this.parServer = parametrosDto.getParServer();
    this.parProtocolo = parametrosDto.getParProtocolo();
    this.parTimeout = parametrosDto.getParTimeout();
    this.parVersion = parametrosDto.getParVersion();
}


    public long getParId() {
        return parId;
    }

    public void setParId(long parId) {
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

    public long getParPuerto() {
        return parPuerto;
    }

    public void setParPuerto(long parPuerto) {
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

    public long getParTimeout() {
        return parTimeout;
    }

    public void setParTimeout(long parTimeout) {
        this.parTimeout = parTimeout;
    }

    public long getParVersion() {
        return parVersion;
    }

    public void setParVersion(long parVersion) {
        this.parVersion = parVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parId != 0 ? Long.hashCode(parId) : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        return this.parId == other.parId;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.Parametros[ parId=" + parId + " ]";
    }
}
