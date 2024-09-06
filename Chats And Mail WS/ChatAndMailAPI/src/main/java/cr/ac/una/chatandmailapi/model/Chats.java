package cr.ac.una.chatandmailapi.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entidad Chats.
 */
@Entity
@Table(name = "SIS_CHATS")
public class Chats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "CHT_ID")
    private Long chtId;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHT_FECHA")
    private Date chtFecha;

    @Basic(optional = false)
    @Column(name = "CHT_VERSION")
    private Long chtVersion;

    @JoinColumn(name = "CHT_RECEPTOR_ID", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false)
    private Usuarios chtReceptorId;

    @JoinColumn(name = "CHT_EMISOR_ID", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false)
    private Usuarios chtEmisorId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smsChatId")
    private List<Mensajes> sisMensajesList;

    // Constructor vacío
    public Chats() {
    }

    // Constructor que recibe un DTO
    public Chats(ChatsDTO chatsDTO) {
        this.chtId = chatsDTO.getChtId();
        actualizar(chatsDTO);
    }

    // Método para actualizar la entidad a partir de un DTO
    public void actualizar(ChatsDTO chatsDTO) {
        this.chtFecha = chatsDTO.getChtFecha();
        this.chtVersion = chatsDTO.getChtVersion();
        this.chtReceptorId = new Usuarios(chatsDTO.getReceptorId());  // Referencia por ID
        this.chtEmisorId = new Usuarios(chatsDTO.getEmisorId());      // Referencia por ID

        if (chatsDTO.getMensajesList() != null) {
            this.sisMensajesList = new ArrayList<>();
            chatsDTO.getMensajesList().forEach(mensajeDto -> {
                this.sisMensajesList.add(new Mensajes((MensajesDTO) mensajeDto));
            });
        }
    }

    // Getters y Setters
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

    // hashCode, equals y toString
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
        return (this.chtId != null || other.chtId == null) && (this.chtId == null || this.chtId.equals(other.chtId));
    }

    @Override
    public String toString() {
        return "cr.ac.una.chatandmailapi.model.Chats[ chtId=" + chtId + " ]";
    }
}