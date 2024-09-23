package cr.ac.una.chatandmailapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.io.Serializable;


@JsonbPropertyOrder({
        "parId", "parCorreo", "parClave", "parPuerto", "parServer",
        "parProtocolo", "parTimeout", "parVersion"
})
@Schema(description = "Esta clase contiene la información de los parámetros")
public class ParametrosDTO implements Serializable {

    @Schema(description = "Identificador de los parámetros", example = "1")
    private long parId;
    @Schema(description = "Correo de los parámetros", example = "sigece@gmail.com"  )
    private String parCorreo;
    @Schema(description = "Clave de los parámetros", example = "123456")
    private String parClave;
    @Schema(description = "Puerto de los parámetros", example = "587")
    private long parPuerto;
    @Schema(description = "Servidor de los parámetros", example = "smtp.gmail.com")
    private String parServer;
    @Schema(description = "Protocolo de los parámetros", example = "smtp")
    private String parProtocolo;
    @Schema(description = "Timeout de los parámetros", example = "10000")
    private long parTimeout;
    @Schema(description = "Versión de los parámetros", example = "1")
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
