/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Kendall Fonseca
 */
@Entity
@Table(name = "SIS_VARIABLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisVariables.findAll", query = "SELECT s FROM SisVariables s"),
    @NamedQuery(name = "SisVariables.findByVarId", query = "SELECT s FROM SisVariables s WHERE s.varId = :varId"),
    @NamedQuery(name = "SisVariables.findByVarNombre", query = "SELECT s FROM SisVariables s WHERE s.varNombre = :varNombre"),
    @NamedQuery(name = "SisVariables.findByTipo", query = "SELECT s FROM SisVariables s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "SisVariables.findByVarNotId", query = "SELECT s FROM SisVariables s WHERE s.varNotId = :varNotId"),
    @NamedQuery(name = "SisVariables.findByVarVersion", query = "SELECT s FROM SisVariables s WHERE s.varVersion = :varVersion")})
public class SisVariables implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "VAR_ID")
    private BigDecimal varId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "VAR_NOMBRE")
    private String varNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "TIPO")
    private String tipo;
    @Lob
    @Column(name = "VAR_VALOR")
    private String varValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VAR_NOT_ID")
    private BigInteger varNotId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VAR_VERSION")
    private BigInteger varVersion;

    public SisVariables() {
    }

    public SisVariables(BigDecimal varId) {
        this.varId = varId;
    }

    public SisVariables(BigDecimal varId, String varNombre, String tipo, BigInteger varNotId, BigInteger varVersion) {
        this.varId = varId;
        this.varNombre = varNombre;
        this.tipo = tipo;
        this.varNotId = varNotId;
        this.varVersion = varVersion;
    }

    public BigDecimal getVarId() {
        return varId;
    }

    public void setVarId(BigDecimal varId) {
        this.varId = varId;
    }

    public String getVarNombre() {
        return varNombre;
    }

    public void setVarNombre(String varNombre) {
        this.varNombre = varNombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVarValor() {
        return varValor;
    }

    public void setVarValor(String varValor) {
        this.varValor = varValor;
    }

    public BigInteger getVarNotId() {
        return varNotId;
    }

    public void setVarNotId(BigInteger varNotId) {
        this.varNotId = varNotId;
    }

    public BigInteger getVarVersion() {
        return varVersion;
    }

    public void setVarVersion(BigInteger varVersion) {
        this.varVersion = varVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (varId != null ? varId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SisVariables)) {
            return false;
        }
        SisVariables other = (SisVariables) object;
        if ((this.varId == null && other.varId != null) || (this.varId != null && !this.varId.equals(other.varId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.SisVariables[ varId=" + varId + " ]";
    }
    
}
