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

@Entity
@Table(name = "SIS_VARIABLES_CONDICIONALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VariablesCondicionales.findAll", query = "SELECT s FROM VariablesCondicionales s"),
    @NamedQuery(name = "VariablesCondicionales.findByVconId", query = "SELECT s FROM VariablesCondicionales s WHERE s.vconId = :vconId"),
    @NamedQuery(name = "VariablesCondicionales.findByVcondValor", query = "SELECT s FROM VariablesCondicionales s WHERE s.vcondValor = :vcondValor"),
    @NamedQuery(name = "VariablesCondicionales.findByVconVersion", query = "SELECT s FROM VariablesCondicionales s WHERE s.vconVersion = :vconVersion")
})
public class VariablesCondicionales implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "VCON_ID")
    private long vconId;
    
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
    private long vconVersion;
    
    @JoinColumn(name = "VCON_VAR_ID", referencedColumnName = "VAR_ID")
    @ManyToOne(optional = false)
    private Variables vconVarId;

    public VariablesCondicionales() {
    }

    public VariablesCondicionales(long vconId) {
        this.vconId = vconId;
    }

    public VariablesCondicionales(VariablesCondicionalesDTO variablesCondicionalesDto) {
    this.vconId = variablesCondicionalesDto.getVconId();
    actualizar(variablesCondicionalesDto);
}

    
    public void actualizar(VariablesCondicionalesDTO variablesCondicionalesDto) {
    this.vcondValor = variablesCondicionalesDto.getVcondValor();
    this.vcondResultado = variablesCondicionalesDto.getVcondResultado();
    this.vconVersion = variablesCondicionalesDto.getVconVersion();

    if (variablesCondicionalesDto.getVconVarId() != null) {
        Variables variable = new Variables();
        variable.setVarId(variablesCondicionalesDto.getVconVarId().getVarId());
        this.vconVarId = variable;
    }
}


    public long getVconId() {
        return vconId;
    }

    public void setVconId(long vconId) {
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

    public long getVconVersion() {
        return vconVersion;
    }

    public void setVconVersion(long vconVersion) {
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
        hash += (vconId != 0 ? Long.hashCode(vconId) : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof VariablesCondicionales)) {
            return false;
        }
        VariablesCondicionales other = (VariablesCondicionales) object;
        return this.vconId == other.vconId;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.VariablesCondicionales[ vconId=" + vconId + " ]";
    }
}
