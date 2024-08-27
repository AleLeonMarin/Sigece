/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Kendall Fonseca
 */
@Entity
@Table(name = "SIS_PARAMETROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisParametros.findAll", query = "SELECT s FROM SisParametros s"),
    @NamedQuery(name = "SisParametros.findByParId", query = "SELECT s FROM SisParametros s WHERE s.parId = :parId"),
    @NamedQuery(name = "SisParametros.findByParCorreo", query = "SELECT s FROM SisParametros s WHERE s.parCorreo = :parCorreo"),
    @NamedQuery(name = "SisParametros.findByParClave", query = "SELECT s FROM SisParametros s WHERE s.parClave = :parClave"),
    @NamedQuery(name = "SisParametros.findByParPuerto", query = "SELECT s FROM SisParametros s WHERE s.parPuerto = :parPuerto"),
    @NamedQuery(name = "SisParametros.findByParServer", query = "SELECT s FROM SisParametros s WHERE s.parServer = :parServer"),
    @NamedQuery(name = "SisParametros.findByParProtocolo", query = "SELECT s FROM SisParametros s WHERE s.parProtocolo = :parProtocolo"),
    @NamedQuery(name = "SisParametros.findByParTimeout", query = "SELECT s FROM SisParametros s WHERE s.parTimeout = :parTimeout"),
    @NamedQuery(name = "SisParametros.findByParVersion", query = "SELECT s FROM SisParametros s WHERE s.parVersion = :parVersion")})
public class SisParametros implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAR_ID")
    private BigDecimal parId;
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
    private BigInteger parPuerto;
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
    private BigInteger parTimeout;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAR_VERSION")
    private BigInteger parVersion;

    public SisParametros() {
    }

    public SisParametros(BigDecimal parId) {
        this.parId = parId;
    }

    public SisParametros(BigDecimal parId, String parCorreo, String parClave, BigInteger parPuerto, String parServer, String parProtocolo, BigInteger parTimeout, BigInteger parVersion) {
        this.parId = parId;
        this.parCorreo = parCorreo;
        this.parClave = parClave;
        this.parPuerto = parPuerto;
        this.parServer = parServer;
        this.parProtocolo = parProtocolo;
        this.parTimeout = parTimeout;
        this.parVersion = parVersion;
    }

    public BigDecimal getParId() {
        return parId;
    }

    public void setParId(BigDecimal parId) {
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

    public BigInteger getParPuerto() {
        return parPuerto;
    }

    public void setParPuerto(BigInteger parPuerto) {
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

    public BigInteger getParTimeout() {
        return parTimeout;
    }

    public void setParTimeout(BigInteger parTimeout) {
        this.parTimeout = parTimeout;
    }

    public BigInteger getParVersion() {
        return parVersion;
    }

    public void setParVersion(BigInteger parVersion) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SisParametros)) {
            return false;
        }
        SisParametros other = (SisParametros) object;
        if ((this.parId == null && other.parId != null) || (this.parId != null && !this.parId.equals(other.parId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.SisParametros[ parId=" + parId + " ]";
    }
    
}
