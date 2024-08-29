package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;

/**
 * DTO for Variables entity
 * 
 * @author Kendall Fonseca
 */
public class VariablesDto implements Serializable {

    private Long varId;
    private String varNombre;
    private String tipo;
    private String varValor;
    private Long varNotId;
    private Long varVersion;

    public VariablesDto() {
    }

    public VariablesDto(Long varId, String varNombre, String tipo, String varValor, Long varNotId, Long varVersion) {
        this.varId = varId;
        this.varNombre = varNombre;
        this.tipo = tipo;
        this.varValor = varValor;
        this.varNotId = varNotId;
        this.varVersion = varVersion;
    }

    public Long getVarId() {
        return varId;
    }

    public void setVarId(Long varId) {
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

    public Long getVarNotId() {
        return varNotId;
    }

    public void setVarNotId(Long varNotId) {
        this.varNotId = varNotId;
    }

    public Long getVarVersion() {
        return varVersion;
    }

    public void setVarVersion(Long varVersion) {
        this.varVersion = varVersion;
    }

    @Override
    public String toString() {
        return "VariablesDto{" +
                "varId=" + varId +
                ", varNombre='" + varNombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", varValor='" + varValor + '\'' +
                ", varNotId=" + varNotId +
                ", varVersion=" + varVersion +
                '}';
    }
}

