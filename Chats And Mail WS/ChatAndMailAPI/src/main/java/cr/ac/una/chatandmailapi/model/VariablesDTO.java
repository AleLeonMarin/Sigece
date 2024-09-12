package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;

/**
 * DTO para la entidad Variables.
 */
public class VariablesDTO implements Serializable {

    private long varId;
    private String varNombre;
    private String tipo;
    private String varValor;
    private long varVersion;
    private NotificacionDTO varNotId;

    public VariablesDTO() {}

    public VariablesDTO(Variables variables) {
        this.varId = variables.getVarId();
        this.varNombre = variables.getVarNombre();
        this.tipo = variables.getTipo();
        this.varValor = variables.getVarValor();
        this.varVersion = variables.getVarVersion();
        if (variables.getVarNotId() != null) {
            this.varNotId = new NotificacionDTO(variables.getVarNotId());
        }
    }

    // Getters y Setters
    public long getVarId() {
        return varId;
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
        int hash = 7;
        hash = 31 * hash + Long.hashCode(varId);
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
        VariablesDTO other = (VariablesDTO) obj;
        return this.varId == other.varId;
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
