package cr.ac.una.securityws.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author aleon
 */
@Entity
@Table(name = "SIS_ROLES")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "SisRoles.findAll", query = "SELECT s FROM SisRoles s"),
        @NamedQuery(name = "SisRoles.findByRolId", query = "SELECT s FROM SisRoles s WHERE s.rolId = :rolId"),
        @NamedQuery(name = "SisRoles.findByRolNombre", query = "SELECT s FROM SisRoles s WHERE s.rolNombre = :rolNombre"),
        @NamedQuery(name = "SisRoles.findByRolVersion", query = "SELECT s FROM SisRoles s WHERE s.rolVersion = :rolVersion") })
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "SIS_ROLES_ID_GENERATOR", sequenceName = "SIS_ROLES_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIS_ROLES_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ROL_ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "ROL_NOMBRE")
    private String nombre;
    @Version
    @Column(name = "ROL_VERSION")
    private Long version;
    @JoinColumn(name = "ROL_SIS_ID", referencedColumnName = "SIS_ID")
    @ManyToOne(optional = false)
    private Sistemas sis_id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "srsRolId")
    private List<SistemasRolesUsuarios> sistemasRolesUsuarios;

    public Roles() {
    }

    public Roles(Long id) {
        this.id = id;
    }

    public Roles(RolesDto rolesDto) {
        this.id = rolesDto.getId();
        actualizarRoles(rolesDto);
    }

    public void actualizarRoles(RolesDto rolesDto) {
        this.nombre = rolesDto.getNombre();
        this.version = rolesDto.getVersion();
        this.sis_id = new Sistemas(rolesDto.getSistemas());
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

    public Sistemas getSis_id() {
        return sis_id;
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

    public void setSis_id(Sistemas sis_id) {
        this.sis_id = sis_id;
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
        Roles other = (Roles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        return true;
    }

    @Override

    public String toString() {
        return "cr.ac.una.securityws.model.Roles[ rol_id =" + id + " ]";
    }

}
