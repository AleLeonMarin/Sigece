/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.securityws.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
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
@Table(name = "SIS_ROLES", schema = "SigeceUNA")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Roles.findAll", query = "SELECT s FROM Roles s"),
/*
 * @NamedQuery(name = "SisRoles.findByRolId", query =
 * "SELECT s FROM SisRoles s WHERE s.rolId = :rolId"),
 * 
 * @NamedQuery(name = "SisRoles.findByRolNombre", query =
 * "SELECT s FROM SisRoles s WHERE s.rolNombre = :rolNombre"),
 * 
 * @NamedQuery(name = "SisRoles.findByRolVersion", query =
 * "SELECT s FROM SisRoles s WHERE s.rolVersion = :rolVersion")
 */ })
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "GENERATOR_ROLES_SEQUENCE", sequenceName = "SIS_ROLES_SEQ_01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIS_ROLES_SEQ_01")
    @Column(name = "ROL_ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "ROL_NOMBRE")
    private String nombre;

    @Column(name = "ROL_DESCRIPCION")
    private String descripcion;

    @Version
    @Column(name = "ROL_VERSION")
    private Long version;

    @JoinColumn(name = "SIS_ID", referencedColumnName = "SIS_ID")
    @ManyToOne(optional = false)
    private Sistemas sisId;

    @ManyToMany
    @JoinTable(name = "SIS_USUARIOS_ROLES", joinColumns = @JoinColumn(name = "ROL_ID"), inverseJoinColumns = @JoinColumn(name = "USU_ID"))
    private List<Usuarios> usuarios;

    public Roles() {
    }

    public Roles(Long id) {
        this.id = id;
    }

    public Roles(RolesDto rolesDto) {
        this.id = rolesDto.getId();
        this.nombre = rolesDto.getNombre();
        actualizar(rolesDto);
    }

    public void actualizar(RolesDto rolesDto) {
        this.nombre = rolesDto.getNombre();
        this.sisId = new Sistemas(rolesDto.getSisId());
        this.version = rolesDto.getVersion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuario(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public Sistemas getSisId() {
        return sisId;
    }

    public void setSisId(Sistemas sistema) {
        this.sisId = sistema;
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
        if (!(object instanceof Roles)) {
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
        return "cr.ac.una.securityws.model.SisRoles[ id=" + id + " ]";
    }

}