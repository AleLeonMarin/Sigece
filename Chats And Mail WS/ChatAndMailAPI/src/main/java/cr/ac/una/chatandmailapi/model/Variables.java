package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SIS_VARIABLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Variables.findAll", query = "SELECT s FROM Variables s"),
    @NamedQuery(name = "Variables.findByVarId", query = "SELECT s FROM Variables s WHERE s.varId = :varId"),
    @NamedQuery(name = "Variables.findByVarNombre", query = "SELECT s FROM Variables s WHERE s.varNombre = :varNombre"),
    @NamedQuery(name = "Variables.findByTipo", query = "SELECT s FROM Variables s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "Variables.findByVarVersion", query = "SELECT s FROM Variables s WHERE s.varVersion = :varVersion")
})
public class Variables implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sis_variables_seq")
    @SequenceGenerator(name = "sis_variables_seq", sequenceName = "SIS_VARIABLES_SEQ01", allocationSize = 1)
    @Column(name = "VAR_ID")
    private long varId;
    
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
    @Column(name = "VAR_VERSION")
    private long varVersion;
    
    @JoinColumn(name = "VAR_NOT_ID", referencedColumnName = "NOT_ID")
    @ManyToOne(optional = false)
    private Notificacion varNotId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vconVarId")
    private List<VariablesCondicionales> sisVariablesCondicionalesList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mediaVarId")
    private List<VariablesMultimedia> sisVariablesMultimediaList;

    public Variables() {
    }

    public Variables(long varId) {
        this.varId = varId;
    }       
    
    public Variables(VariablesDTO variablesDto) {
    this.varId = variablesDto.getVarId();
    actualizar(variablesDto);
}

    
    public void actualizar(VariablesDTO variablesDto) {
        this.varNombre = variablesDto.getVarNombre();
        this.tipo = variablesDto.getTipo();
        this.varValor = variablesDto.getVarValor();
        this.varVersion = variablesDto.getVarVersion();

    if (variablesDto.getVarNotId() != null) {
        Notificacion notificacion = new Notificacion();
        notificacion.setNotId(variablesDto.getVarNotId().getNotId());
        this.varNotId = notificacion;
    }
}


    public long getVarId() {
        return varId;
    }

    public void setVarId(long varId) {
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

    public long getVarVersion() {
        return varVersion;
    }

    public void setVarVersion(long varVersion) {
        this.varVersion = varVersion;
    }

    public Notificacion getVarNotId() {
        return varNotId;
    }

    public void setVarNotId(Notificacion varNotId) {
        this.varNotId = varNotId;
    }

    @XmlTransient
    public List<VariablesCondicionales> getSisVariablesCondicionalesList() {
        return sisVariablesCondicionalesList;
    }

    public void setSisVariablesCondicionalesList(List<VariablesCondicionales> sisVariablesCondicionalesList) {
        this.sisVariablesCondicionalesList = sisVariablesCondicionalesList;
    }

    @XmlTransient
    public List<VariablesMultimedia> getSisVariablesMultimediaList() {
        return sisVariablesMultimediaList;
    }

    public void setSisVariablesMultimediaList(List<VariablesMultimedia> sisVariablesMultimediaList) {
        this.sisVariablesMultimediaList = sisVariablesMultimediaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (varId != 0 ? Long.hashCode(varId) : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Variables)) {
            return false;
        }
        Variables other = (Variables) object;
        return this.varId == other.varId;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.Variables[ varId=" + varId + " ]";
    }
}
