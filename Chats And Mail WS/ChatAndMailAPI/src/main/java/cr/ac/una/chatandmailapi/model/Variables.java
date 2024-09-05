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
@Table(name = "SIS_VARIABLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Variables.findAll", query = "SELECT s FROM Variables s"),
    @NamedQuery(name = "Variables.findByVarId", query = "SELECT s FROM Variables s WHERE s.varId = :varId"),
    @NamedQuery(name = "Variables.findByVarNombre", query = "SELECT s FROM Variables s WHERE s.varNombre = :varNombre"),
    @NamedQuery(name = "Variables.findByTipo", query = "SELECT s FROM Variables s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "Variables.findByVarNotId", query = "SELECT s FROM Variables s WHERE s.varNotId = :varNotId"),
    @NamedQuery(name = "Variables.findByVarVersion", query = "SELECT s FROM Variables s WHERE s.varVersion = :varVersion")})
public class Variables implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "VAR_ID")
    private Long varId;

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
    private Long varNotId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "VAR_VERSION")
    private Long varVersion;

    public Variables() {
    }

    public Variables(Long varId) {
        this.varId = varId;
    }

    public Variables(Long varId, String varNombre, String tipo, Long varNotId, Long varVersion) {
        this.varId = varId;
        this.varNombre = varNombre;
        this.tipo = tipo;
        this.varNotId = varNotId;
        this.varVersion = varVersion;
    }

    public Long getVarId() {
        return varId;
    }

    public void setVarId(Long varId) {
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

    public Long getVarNotId() {
        return varNotId;
    }

    public void setVarNotId(Long varNotId) {
        this.varNotId = varNotId;
    }

    public Long getVarVersion() {
        return varVersion;
    }

    public void setVarVersion(Long varVersion) {
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
        if (!(object instanceof Variables)) {
            return false;
        }
        Variables other = (Variables) object;
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
