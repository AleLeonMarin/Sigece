/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.securityws.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aleon
 */
@Entity
@Table(name = "SIS_SISTEMAS" , schema = "SigeceUNA")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Sistemas.findAll", query = "SELECT s FROM Sistemas s"),
/*
 * @NamedQuery(name = "SisSistemas.findBySisId", query =
 * "SELECT s FROM SisSistemas s WHERE s.sisId = :sisId"),
 * 
 * @NamedQuery(name = "SisSistemas.findBySisNombre", query =
 * "SELECT s FROM SisSistemas s WHERE s.sisNombre = :sisNombre"),
 * 
 * @NamedQuery(name = "SisSistemas.findBySisVersion", query =
 * "SELECT s FROM SisSistemas s WHERE s.sisVersion = :sisVersion")
 */ })
public class Sistemas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "GENERATOR_SISTEMAS_SEQUENCE", sequenceName = "SIS_SISTEMAS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_SISTEMAS_SEQUENCE")
    @Column(name = "SIS_ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "SIS_NOMBRE")
    private String nombre;

    @Version
    @Column(name = "SIS_VERSION")
    private Long version;

    @OneToMany(mappedBy = "sistema", fetch = FetchType.LAZY)
    private List<Roles> roles;

    public Sistemas() {
    }

    public Sistemas(Long id) {
        this.id = id;
    }

    public Sistemas(SistemasDto sistemasDto) {
        this.id = sistemasDto.getId();
        this.nombre = sistemasDto.getNombre();
        actualizar(sistemasDto);
    }

    public void actualizar(SistemasDto sistemasDto) {
        this.nombre = sistemasDto.getNombre();
        this.version = sistemasDto.getVersion();
    }

    public Long getId() {
        return id;
    }

    public void setSisId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setSisNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @XmlTransient
    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sistemas)) {
            return false;
        }
        Sistemas other = (Sistemas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.securityws.model.SisSistemas[ id=" + id + " ]";
    }

}
