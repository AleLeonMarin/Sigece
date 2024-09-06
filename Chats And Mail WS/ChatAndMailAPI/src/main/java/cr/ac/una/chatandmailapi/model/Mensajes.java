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
    @Column(name = "SMS_VERSION")
    private Long smsVersion;
    
    @JoinColumn(name = "SMS_CHAT_ID", referencedColumnName = "CHT_ID")
    @ManyToOne(optional = false)
    private Chats smsChatId;
    
    @JoinColumn(name = "SMS_USU_ID_EMISOR", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false)
    private Usuarios smsUsuIdEmisor;

    // Constructor vacío
    public Mensajes() {
    }

    // Constructor que recibe el DTO
    public Mensajes(MensajesDTO mensajesDto) {
        this.smsId = mensajesDto.getSmsId();
        actualizar(mensajesDto);
    }

    // Método actualizar basado en el DTO
    public void actualizar(MensajesDTO mensajesDto) {
    this.smsTexto = mensajesDto.getSmsTexto();
    this.smsTiempo = mensajesDto.getSmsTiempo();
    this.smsVersion = mensajesDto.getSmsVersion();

    // Asignar `smsChatId` creando un objeto `Chats` con el ID del chat
    if (mensajesDto.getChatId() != null) {
        this.smsChatId = new Chats();
        this.smsChatId.setChtId(mensajesDto.getChatId());  // Asigna solo el ID del chat
    }

    // Asignar `smsUsuIdEmisor` creando un objeto `Usuarios` con el ID del emisor
    if (mensajesDto.getEmisorId() != null) {
        this.smsUsuIdEmisor = new Usuarios();
        this.smsUsuIdEmisor.setUsuId(mensajesDto.getEmisorId());  // Asigna solo el ID del emisor
    }
}


    // Getters y Setters

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

    public Long getSmsVersion() {
        return smsVersion;
    }

    public void setSmsVersion(Long smsVersion) {
        this.smsVersion = smsVersion;
    }

    public Chats getSmsChatId() {
        return smsChatId;
    }

    public void setSmsChatId(Chats smsChatId) {
        this.smsChatId = smsChatId;
    }

    public Usuarios getSmsUsuIdEmisor() {
        return smsUsuIdEmisor;
    }

    public void setSmsUsuIdEmisor(Usuarios smsUsuIdEmisor) {
        this.smsUsuIdEmisor = smsUsuIdEmisor;
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
        return (this.smsId != null || other.smsId == null) && (this.smsId == null || this.smsId.equals(other.smsId));
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.SisMensajes[ smsId=" + smsId + " ]";
    }
}
