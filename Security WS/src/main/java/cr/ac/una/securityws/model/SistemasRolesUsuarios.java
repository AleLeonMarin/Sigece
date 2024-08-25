/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.securityws.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author aleon
 */
@Entity
@Table(name = "SIS_SISTEMAS_ROLES_USUARIOS")
@NamedQueries({
    @NamedQuery(name = "SisSistemasRolesUsuarios.findAll", query = "SELECT s FROM SisSistemasRolesUsuarios s"),
    @NamedQuery(name = "SisSistemasRolesUsuarios.findBySrsId", query = "SELECT s FROM SisSistemasRolesUsuarios s WHERE s.srsId = :srsId"),
    @NamedQuery(name = "SisSistemasRolesUsuarios.findBySrsNombre", query = "SELECT s FROM SisSistemasRolesUsuarios s WHERE s.srsNombre = :srsNombre"),
    @NamedQuery(name = "SisSistemasRolesUsuarios.findBySrsVersion", query = "SELECT s FROM SisSistemasRolesUsuarios s WHERE s.srsVersion = :srsVersion")})
public class SistemasRolesUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "SIS_SISTEMAS_ROLES_USUARIOS_ID_GENERATOR", sequenceName = "SIS_SISTEMAS_ROLES_USUARIOS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIS_SISTEMAS_ROLES_USUARIOS_ID_GENERATOR")
    @Column(name = "SRS_ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "SRS_NOMBRE")
    private String nombre;
    @JoinColumn(name = "SRS_ROL_ID", referencedColumnName = "ROL_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Roles rolID;
    @JoinColumn(name = "SRS_SIS_ID", referencedColumnName = "SIS_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sistemas sisID;
    @JoinColumn(name = "SRS_USU_ID", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuID;
    @Version
    @Column(name = "SRS_VERSION")
    private Long version;

    public SistemasRolesUsuarios() {
    }


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Roles getRolID() {
        return rolID;
    }

    public Sistemas getSisID() {
        return sisID;
    }

    public Usuarios getUsuID() {
        return usuID;
    }

    public Long getVersion() {
        return version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRolID(Roles rolID) {
        this.rolID = rolID;
    }

    public void setSisID(Sistemas sisID) {
        this.sisID = sisID;
    }

    public void setSrsUsuId(Usuarios usuID) {
        this.usuID = usuID;
    }

    public void setVersion(Long version) {
        this.version = version;
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
        SistemasRolesUsuarios other = (SistemasRolesUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }

        return true;
    }

    @Override

    public String toString() {
        return "cr.ac.una.securityws.model.SistemasRolesUsuarios[ srs_id =" + id + " ]";
    }
    
    
    
}
