/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
@Table(name = "SIS_VARIABLES_CONDICIONALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisVariablesCondicionales.findAll", query = "SELECT s FROM SisVariablesCondicionales s"),
    @NamedQuery(name = "SisVariablesCondicionales.findByVconId", query = "SELECT s FROM SisVariablesCondicionales s WHERE s.vconId = :vconId"),
    @NamedQuery(name = "SisVariablesCondicionales.findByVcondValor", query = "SELECT s FROM SisVariablesCondicionales s WHERE s.vcondValor = :vcondValor"),
    @NamedQuery(name = "SisVariablesCondicionales.findByVconVersion", query = "SELECT s FROM SisVariablesCondicionales s WHERE s.vconVersion = :vconVersion")})
public class VariablesCondicionales implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "VCON_ID")
    private BigDecimal vconId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "VCOND_VALOR")
    private String vcondValor;
    @Lob
    @Column(name = "VCOND_RESULTADO")
    private String vcondResultado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VCON_VERSION")
    private BigInteger vconVersion;
    @JoinColumn(name = "VCON_VAR_ID", referencedColumnName = "VAR_ID")
    @ManyToOne(optional = false)
    private Variables vconVarId;

    public VariablesCondicionales() {
    }

    public VariablesCondicionales(BigDecimal vconId) {
        this.vconId = vconId;
    }

    public VariablesCondicionales(BigDecimal vconId, String vcondValor, BigInteger vconVersion) {
        this.vconId = vconId;
        this.vcondValor = vcondValor;
        this.vconVersion = vconVersion;
    }

    public BigDecimal getVconId() {
        return vconId;
    }

    public void setVconId(BigDecimal vconId) {
        this.vconId = vconId;
    }

    public String getVcondValor() {
        return vcondValor;
    }

    public void setVcondValor(String vcondValor) {
        this.vcondValor = vcondValor;
    }

    public String getVcondResultado() {
        return vcondResultado;
    }

    public void setVcondResultado(String vcondResultado) {
        this.vcondResultado = vcondResultado;
    }

    public BigInteger getVconVersion() {
        return vconVersion;
    }

    public void setVconVersion(BigInteger vconVersion) {
        this.vconVersion = vconVersion;
    }

    public Variables getVconVarId() {
        return vconVarId;
    }

    public void setVconVarId(Variables vconVarId) {
        this.vconVarId = vconVarId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vconId != null ? vconId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VariablesCondicionales)) {
            return false;
        }
        VariablesCondicionales other = (VariablesCondicionales) object;
        if ((this.vconId == null && other.vconId != null) || (this.vconId != null && !this.vconId.equals(other.vconId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.SisVariablesCondicionales[ vconId=" + vconId + " ]";
    }
    
}
