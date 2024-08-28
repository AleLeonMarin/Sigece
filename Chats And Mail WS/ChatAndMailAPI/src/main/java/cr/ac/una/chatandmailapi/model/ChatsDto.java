package cr.ac.una.chatandmailapi.model;

import java.util.Date;

/**
 * DTO for Chats entity
 */
public class ChatsDto {

    private Long chtId;
    private Date chtFecha;
    private Long chtEmisorId;
    private Long chtReceptorId;
    private Long chtVersion;

    public ChatsDto() {
    }

    public ChatsDto(Long chtId, Date chtFecha, Long chtEmisorId, Long chtReceptorId, Long chtVersion) {
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
    public String toString() {
        return "ChatsDto{" +
                "chtId=" + chtId +
                ", chtFecha=" + chtFecha +
                ", chtEmisorId=" + chtEmisorId +
                ", chtReceptorId=" + chtReceptorId +
                ", chtVersion=" + chtVersion +
                '}';
    }
}
