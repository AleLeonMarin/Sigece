package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;

/**
 * DTO para la entidad Parametros.
 */
public class ParametrosDTO implements Serializable {

    private long parId;
    private String parCorreo;
    private String parClave;
    private long parPuerto;
    private String parServer;
    private String parProtocolo;
    private long parTimeout;
    private long parVersion;

    public ParametrosDTO() {}

    public ParametrosDTO(Parametros parametros) {
        this.parId = parametros.getParId();
        this.parCorreo = parametros.getParCorreo();
        this.parClave = parametros.getParClave();
        this.parPuerto = parametros.getParPuerto();
        this.parServer = parametros.getParServer();
        this.parProtocolo = parametros.getParProtocolo();
        this.parTimeout = parametros.getParTimeout();
        this.parVersion = parametros.getParVersion();
    }

    // Getters y Setters
    public long getParId() {
        return parId;
    }

    public void setParId(long parId) {
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

    public long getParPuerto() {
        return parPuerto;
    }

    public void setParPuerto(long parPuerto) {
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

    public long getParTimeout() {
        return parTimeout;
    }

    public void setParTimeout(long parTimeout) {
        this.parTimeout = parTimeout;
    }

    public long getParVersion() {
        return parVersion;
    }

    public void setParVersion(long parVersion) {
        this.parVersion = parVersion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Long.hashCode(parId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ParametrosDTO other = (ParametrosDTO) obj;
        return this.parId == other.parId;
    }

    @Override
    public String toString() {
        return "ParametrosDTO{" +
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
