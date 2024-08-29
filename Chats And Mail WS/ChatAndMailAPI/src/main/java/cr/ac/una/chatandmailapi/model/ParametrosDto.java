package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;

/**
 * DTO for Parametros entity
 * 
 * @author Kendall Fonseca
 */
public class ParametrosDto implements Serializable {

    private Long parId;
    private String parCorreo;
    private String parClave;
    private Long parPuerto;
    private String parServer;
    private String parProtocolo;
    private Long parTimeout;
    private Long parVersion;

    public ParametrosDto() {
    }

    public ParametrosDto(Long parId, String parCorreo, String parClave, Long parPuerto, String parServer, String parProtocolo, Long parTimeout, Long parVersion) {
        this.parId = parId;
        this.parCorreo = parCorreo;
        this.parClave = parClave;
        this.parPuerto = parPuerto;
        this.parServer = parServer;
        this.parProtocolo = parProtocolo;
        this.parTimeout = parTimeout;
        this.parVersion = parVersion;
    }

    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParCorreo() {
        return parCorreo;
    }

    public void setParCorreo(String parCorreo) {
        this.parCorreo = parCorreo;
    }

    public String getParClave() {
        return parClave;
    }

    public void setParClave(String parClave) {
        this.parClave = parClave;
    }

    public Long getParPuerto() {
        return parPuerto;
    }

    public void setParPuerto(Long parPuerto) {
        this.parPuerto = parPuerto;
    }

    public String getParServer() {
        return parServer;
    }

    public void setParServer(String parServer) {
        this.parServer = parServer;
    }

    public String getParProtocolo() {
        return parProtocolo;
    }

    public void setParProtocolo(String parProtocolo) {
        this.parProtocolo = parProtocolo;
    }

    public Long getParTimeout() {
        return parTimeout;
    }

    public void setParTimeout(Long parTimeout) {
        this.parTimeout = parTimeout;
    }

    public Long getParVersion() {
        return parVersion;
    }

    public void setParVersion(Long parVersion) {
        this.parVersion = parVersion;
    }

    @Override
    public String toString() {
        return "ParametrosDto{" +
                "parId=" + parId +
                ", parCorreo='" + parCorreo + '\'' +
                ", parClave='" + parClave + '\'' +
                ", parPuerto=" + parPuerto +
                ", parServer='" + parServer + '\'' +
                ", parProtocolo='" + parProtocolo + '\'' +
                ", parTimeout=" + parTimeout +
                ", parVersion=" + parVersion +
                '}';
    }
}
