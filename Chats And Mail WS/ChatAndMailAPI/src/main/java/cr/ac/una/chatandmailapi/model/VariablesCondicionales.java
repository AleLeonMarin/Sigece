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
@Table(name = "SIS_VARIABLES_CONDICIONALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SisVariablesCondicionales.findAll", query = "SELECT s FROM SisVariablesCondicionales s"),
    @NamedQuery(name = "SisVariablesCondicionales.findByVconId", query = "SELECT s FROM SisVariablesCondicionales s WHERE s.vconId = :vconId"),
    @NamedQuery(name = "SisVariablesCondicionales.findByVcondValor", query = "SELECT s FROM SisVariablesCondicionales s WHERE s.vcondValor = :vcondValor"),
    @NamedQuery(name = "SisVariablesCondicionales.findByVconVarId", query = "SELECT s FROM SisVariablesCondicionales s WHERE s.vconVarId = :vconVarId"),
    @NamedQuery(name = "SisVariablesCondicionales.findByVconVersion", query = "SELECT s FROM SisVariablesCondicionales s WHERE s.vconVersion = :vconVersion")})
public class VariablesCondicionales implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "VCON_ID")
    private Long vconId;

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
    @Column(name = "VCON_VAR_ID")
    private Long vconVarId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "VCON_VERSION")
    private Long vconVersion;

    public VariablesCondicionales() {
    }

    public VariablesCondicionales(Long vconId) {
        this.vconId = vconId;
    }

    public VariablesCondicionales(Long vconId, String vcondValor, Long vconVarId, Long vconVersion) {
        this.vconId = vconId;
        this.vcondValor = vcondValor;
        this.vconVarId = vconVarId;
        this.vconVersion = vconVersion;
    }

    public Long getVconId() {
        return vconId;
    }

    public void setVconId(Long vconId) {
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

    public Long getVconVarId() {
        return vconVarId;
    }

    public void setVconVarId(Long vconVarId) {
        this.vconVarId = vconVarId;
    }

    public Long getVconVersion() {
        return vconVersion;
    }

    public void setVconVersion(Long vconVersion) {
        this.vconVersion = vconVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vconId != null ? vconId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
        return "cr.ac.una.chatandmailapi.model.VariablesCondicionales[ vconId=" + vconId + " ]";
    }
}
