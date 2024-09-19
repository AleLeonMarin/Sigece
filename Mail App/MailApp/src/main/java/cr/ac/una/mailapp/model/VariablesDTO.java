package cr.ac.una.mailapp.model;

import jakarta.json.bind.annotation.JsonbTransient;

import java.io.Serializable;

public class VariablesDTO implements Serializable {
    private Long varId;
    private String varNombre;
    private String tipo;
    private String varValor;
    private long varVersion;
    @JsonbTransient
    private NotificacionDTO varNotId;

    public VariablesDTO() {
        this.varId = 0L;
        this.varNombre = "";
        this.tipo = "";
        this.varValor = "";

    }


    public VariablesDTO(long varId, String varNombre, String tipo, String varValor, long varVersion, NotificacionDTO varNotId) {
        this.varId = varId;
        this.varNombre = varNombre;
        this.tipo = tipo;
        this.varValor = varValor;
        this.varVersion = varVersion;
        this.varNotId = varNotId;
    }

    // Getters y Setters
    public Long getVarId() {
        if (this.varId != null && !this.varId.equals(0L)) {
            return varId;
        } else {
            return null;
        }
    }

    public void setVarId(long varId) {
        this.varId = varId;
    }

    public String getVarNombre() {
        return varNombre;
    }

    public void setVarNombre(String varNombre) {
        this.varNombre = varNombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVarValor() {
        return varValor;
    }

    public void setVarValor(String varValor) {
        this.varValor = varValor;
    }

    public long getVarVersion() {
        return varVersion;
    }

    public void setVarVersion(long varVersion) {
        this.varVersion = varVersion;
    }

    public NotificacionDTO getVarNotId() {
        return varNotId;
    }

    public void setVarNotId(NotificacionDTO varNotId) {
        this.varNotId = varNotId;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(varId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VariablesDTO other = (VariablesDTO) obj;
        return varId == other.varId;
    }

    @Override
    public String toString() {
        return "VariablesDTO{" +
                "varId=" + varId +
                ", varNombre='" + varNombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", varValor='" + varValor + '\'' +
                ", varVersion=" + varVersion +
                ", varNotId=" + varNotId +
                '}';
    }
}

