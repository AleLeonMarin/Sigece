package cr.ac.una.securityws.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aleon
 */
@Entity
@Table(name = "SIS_SISTEMAS")
@NamedQueries({
    @NamedQuery(name = "SisSistemas.findAll", query = "SELECT s FROM SisSistemas s"),
    @NamedQuery(name = "SisSistemas.findBySisId", query = "SELECT s FROM SisSistemas s WHERE s.sisId = :sisId"),
    @NamedQuery(name = "SisSistemas.findBySisNombre", query = "SELECT s FROM SisSistemas s WHERE s.sisNombre = :sisNombre"),
    @NamedQuery(name = "SisSistemas.findBySisVersion", query = "SELECT s FROM SisSistemas s WHERE s.sisVersion = :sisVersion")})
public class Sistemas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "SIS_SISTEMAS_ID_GENERATOR", sequenceName = "SIS_SISTEMAS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIS_SISTEMAS_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "SIS_ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "SIS_NOMBRE")
    private String nombre;
    @Version
    @Column(name = "SIS_VERSION")
    private Long version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolSisId")
    private List<Roles> roles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "srsSisId")
    private List<SistemasRolesUsuarios> sistemasRolesUsuarios = new ArrayList<>();

    public Sistemas() {
    }

    public Sistemas(Long id) {
        this.id = id;
    }

   public Sistemas(SistemasDto sistemasDto) {
        this.id = sistemasDto.getId();
        this.nombre = sistemasDto.getNombre();
        actualizarSistemas(sistemasDto);
    }

    public void actualizarSistemas(SistemasDto sistemasDto) {
        this.nombre = sistemasDto.getNombre();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getVersion() {
        return version;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public List<SistemasRolesUsuarios> getSistemasRolesUsuarios() {
        return sistemasRolesUsuarios;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public void setSistemasRolesUsuarios(List<SistemasRolesUsuarios> sistemasRolesUsuarios) {
        this.sistemasRolesUsuarios = sistemasRolesUsuarios;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Usuarios)) {
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
        return "cr.ac.una.securityws.model.Sistemas[ sis_id =" + id + " ]";
    }
    
}
