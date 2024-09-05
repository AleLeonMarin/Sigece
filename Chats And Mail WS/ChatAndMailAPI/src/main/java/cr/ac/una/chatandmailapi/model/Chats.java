/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kendall Fonseca
 */
@Entity
@Table(name = "SIS_CHATS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chats.findAll", query = "SELECT s FROM Chats s"),
    @NamedQuery(name = "Chats.findByChtId", query = "SELECT s FROM Chats s WHERE s.chtId = :chtId"),
    @NamedQuery(name = "Chats.findByChtFecha", query = "SELECT s FROM Chats s WHERE s.chtFecha = :chtFecha"),
    @NamedQuery(name = "Chats.findByChtEmisorId", query = "SELECT s FROM Chats s WHERE s.chtEmisorId = :chtEmisorId"),
    @NamedQuery(name = "Chats.findByChtReceptorId", query = "SELECT s FROM Chats s WHERE s.chtReceptorId = :chtReceptorId"),
    @NamedQuery(name = "Chats.findByChtVersion", query = "SELECT s FROM Chats s WHERE s.chtVersion = :chtVersion")})
public class Chats implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHT_ID")
    private Long chtId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHT_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chtFecha;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHT_EMISOR_ID")
    private Long chtEmisorId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHT_RECEPTOR_ID")
    private Long chtReceptorId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHT_VERSION")
    private Long chtVersion;

    public Chats() {
    }

    public Chats(Long chtId) {
        this.chtId = chtId;
    }

    public Chats(Long chtId, Date chtFecha, Long chtEmisorId, Long chtReceptorId, Long chtVersion) {
        this.chtId = chtId;
        this.chtFecha = chtFecha;
        this.chtEmisorId = chtEmisorId;
        this.chtReceptorId = chtReceptorId;
        this.chtVersion = chtVersion;
    }

    public Long getChtId() {
        return chtId;
    }

    public void setChtId(Long chtId) {
        this.chtId = chtId;
    }

    public Date getChtFecha() {
        return chtFecha;
    }

    public void setChtFecha(Date chtFecha) {
        this.chtFecha = chtFecha;
    }

    public Long getChtEmisorId() {
        return chtEmisorId;
    }

    public void setChtEmisorId(Long chtEmisorId) {
        this.chtEmisorId = chtEmisorId;
    }

    public Long getChtReceptorId() {
        return chtReceptorId;
    }

    public void setChtReceptorId(Long chtReceptorId) {
        this.chtReceptorId = chtReceptorId;
    }

    public Long getChtVersion() {
        return chtVersion;
    }

    public void setChtVersion(Long chtVersion) {
        this.chtVersion = chtVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chtId != null ? chtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Chats)) {
            return false;
        }
        Chats other = (Chats) object;
        if ((this.chtId == null && other.chtId != null) || (this.chtId != null && !this.chtId.equals(other.chtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.SisChats[ chtId=" + chtId + " ]";
    }
}
