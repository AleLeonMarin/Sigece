package cr.ac.una.chatandmailapi.model;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entidad Chats para manejar la tabla de chats en la base de datos.
 */
@Entity
@Table(name = "SIS_CHATS")
@NamedQueries({
    @NamedQuery(name = "Chats.findAll", query = "SELECT c FROM Chats c"),
    @NamedQuery(name = "Chats.findByChtId", query = "SELECT c FROM Chats c WHERE c.chtId = :chtId"),
    @NamedQuery(name = "Chats.findByChtFecha", query = "SELECT c FROM Chats c WHERE c.chtFecha = :chtFecha"),
    @NamedQuery(name = "Chats.findByChtVersion", query = "SELECT c FROM Chats c WHERE c.chtVersion = :chtVersion")
})
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
    @Column(name = "CHT_VERSION")
    private Long chtVersion;

    @JoinColumn(name = "CHT_RECEPTOR_ID", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false)
    private Usuarios chtReceptorId;

    @JoinColumn(name = "CHT_EMISOR_ID", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false)
    private Usuarios chtEmisorId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smsChatId")
    @JsonbTransient  // O @JsonIgnore si usas Jackson
    private List<Mensajes> sisMensajesList;

    public Chats() {
    }

    public Chats(Long chtId) {
        this.chtId = chtId;
    }

    public Chats(Long chtId, Date chtFecha, Long chtVersion) {
        this.chtId = chtId;
        this.chtFecha = chtFecha;
        this.chtVersion = chtVersion;
    }

  public Chats(ChatsDTO chatDto) {
    this.chtFecha = chatDto.getChtFecha();
    this.chtVersion = chatDto.getChtVersion();
    this.chtReceptorId = chatDto.getReceptorId();
    this.chtEmisorId = chatDto.getEmisorId();

    if (chatDto.getMensajesList() != null) {
        this.sisMensajesList = new ArrayList<>();
        for (MensajesDTO mensajeDto : chatDto.getMensajesList()) {
            Mensajes mensaje = new Mensajes(mensajeDto);
            mensaje.setSmsChatId(this);
            this.sisMensajesList.add(mensaje);
        }
    }
}

public void actualizar(ChatsDTO chatsDTO) {
    this.chtFecha = chatsDTO.getChtFecha();
    this.chtVersion = chatsDTO.getChtVersion();
    // Si `chtReceptorId` y `chtEmisorId` son entidades relacionadas, asignamos solo los IDs
    if (chatsDTO.getReceptorId() != null) {
        this.chtReceptorId = new Usuarios();
        this.chtReceptorId=chatsDTO.getReceptorId();
    }
    if (chatsDTO.getEmisorId() != null) {
        this.chtEmisorId = new Usuarios();
        this.chtEmisorId=chatsDTO.getEmisorId();
    }

    // Actualizamos la lista de mensajes, si está presente
    if (chatsDTO.getMensajesList() != null) {
        this.sisMensajesList = new ArrayList<>();
        for (MensajesDTO mensajeDto : chatsDTO.getMensajesList()) {
            this.sisMensajesList.add(new Mensajes(mensajeDto));  // Conversión de DTO a entidad
        }
    }
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

    public Long getChtVersion() {
        return chtVersion;
    }

    public void setChtVersion(Long chtVersion) {
        this.chtVersion = chtVersion;
    }

    public Usuarios getChtReceptorId() {
        return chtReceptorId;
    }

    public void setChtReceptorId(Usuarios chtReceptorId) {
        this.chtReceptorId = chtReceptorId;
    }

    public Usuarios getChtEmisorId() {
        return chtEmisorId;
    }

    public void setChtEmisorId(Usuarios chtEmisorId) {
        this.chtEmisorId = chtEmisorId;
    }

    public List<Mensajes> getSisMensajesList() {
        return sisMensajesList;
    }

    public void setSisMensajesList(List<Mensajes> sisMensajesList) {
        this.sisMensajesList = sisMensajesList;
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
        return !((this.chtId == null && other.chtId != null) || (this.chtId != null && !this.chtId.equals(other.chtId)));
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.Chats[ chtId=" + chtId + " ]";
    }
}