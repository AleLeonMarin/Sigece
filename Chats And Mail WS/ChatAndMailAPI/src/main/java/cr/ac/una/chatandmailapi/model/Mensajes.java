package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "SIS_MENSAJES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensajes.findAll", query = "SELECT s FROM Mensajes s"),
    @NamedQuery(name = "Mensajes.findBySmsId", query = "SELECT s FROM Mensajes s WHERE s.smsId = :smsId"),
    @NamedQuery(name = "Mensajes.findBySmsTiempo", query = "SELECT s FROM Mensajes s WHERE s.smsTiempo = :smsTiempo"),
    @NamedQuery(name = "Mensajes.findBySmsUsuIdEmisor", query = "SELECT s FROM Mensajes s WHERE s.smsUsuIdEmisor = :smsUsuIdEmisor"),
    @NamedQuery(name = "Mensajes.findBySmsChatId", query = "SELECT s FROM Mensajes s WHERE s.smsChatId = :smsChatId"),
    @NamedQuery(name = "Mensajes.findBySmsVersion", query = "SELECT s FROM Mensajes s WHERE s.smsVersion = :smsVersion")})
public class Mensajes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SMS_ID")
    private Long smsId;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "SMS_TEXTO")
    private String smsTexto;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "SMS_TIEMPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date smsTiempo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "SMS_USU_ID_EMISOR")
    private Long smsUsuIdEmisor;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "SMS_CHAT_ID")
    private Long smsChatId;
    
    
 
    @Basic(optional = false)
    @NotNull
    @Column(name = "SMS_VERSION")
    
    private Long smsVersion;

    public Mensajes() {
    }

    public Mensajes(Long smsId) {
        this.smsId = smsId;
    }

    public Mensajes(Long smsId, String smsTexto, Date smsTiempo, Long smsUsuIdEmisor, Long smsChatId, Long smsVersion) {
        this.smsId = smsId;
        this.smsTexto = smsTexto;
        this.smsTiempo = smsTiempo;
        this.smsUsuIdEmisor = smsUsuIdEmisor;
        this.smsChatId = smsChatId;
        this.smsVersion = smsVersion;
    }

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public String getSmsTexto() {
        return smsTexto;
    }

    public void setSmsTexto(String smsTexto) {
        this.smsTexto = smsTexto;
    }

    public Date getSmsTiempo() {
        return smsTiempo;
    }

    public void setSmsTiempo(Date smsTiempo) {
        this.smsTiempo = smsTiempo;
    }

    public Long getSmsUsuIdEmisor() {
        return smsUsuIdEmisor;
    }

    public void setSmsUsuIdEmisor(Long smsUsuIdEmisor) {
        this.smsUsuIdEmisor = smsUsuIdEmisor;
    }

    public Long getSmsChatId() {
        return smsChatId;
    }

    public void setSmsChatId(Long smsChatId) {
        this.smsChatId = smsChatId;
    }

    public Long getSmsVersion() {
        return smsVersion;
    }

    public void setSmsVersion(Long smsVersion) {
        this.smsVersion = smsVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (smsId != null ? smsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Mensajes)) {
            return false;
        }
        Mensajes other = (Mensajes) object;
        if ((this.smsId == null && other.smsId != null) || (this.smsId != null && !this.smsId.equals(other.smsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.SisMensajes[ smsId=" + smsId + " ]";
    }
}