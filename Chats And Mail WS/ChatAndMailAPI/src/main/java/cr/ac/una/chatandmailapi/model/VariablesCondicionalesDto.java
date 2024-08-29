package cr.ac.una.chatandmailapi.model;

import java.io.Serializable;

/**
 * DTO for VariablesCondicionales entity
 * 
 * @author Kendall Fonseca
 */
public class VariablesCondicionalesDto implements Serializable {

    private Long vconId;
    private String vcondValor;
    private String vcondResultado;
    private Long vconVarId;
    private Long vconVersion;

    public VariablesCondicionalesDto() {
    }

    public VariablesCondicionalesDto(Long vconId, String vcondValor, String vcondResultado, Long vconVarId, Long vconVersion) {
        this.vconId = vconId;
        this.vcondValor = vcondValor;
        this.vcondResultado = vcondResultado;
        this.vconVarId = vconVarId;
        this.vconVersion = vconVersion;
    }

    public Long getVconId() {
        return vconId;
    }

    public void setVconId(Long vconId) {
        this.vconId = vconId;
    }

    public String getVcondValor() {
        return vcondValor;
    }

    public void setVcondValor(String vcondValor) {
        this.vcondValor = vcondValor;
    }

    public String getVcondResultado() {
        return vcondResultado;
    }

    public void setVcondResultado(String vcondResultado) {
        this.vcondResultado = vcondResultado;
    }

    public Long getVconVarId() {
        return vconVarId;
    }

    public void setVconVarId(Long vconVarId) {
        this.vconVarId = vconVarId;
    }

    public Long getVconVersion() {
        return vconVersion;
    }

    public void setVconVersion(Long vconVersion) {
        this.vconVersion = vconVersion;
    }

    @Override
    public String toString() {
        return "VariablesCondicionalesDto{" +
                "vconId=" + vconId +
                ", vcondValor='" + vcondValor + '\'' +
                ", vcondResultado='" + vcondResultado + '\'' +
                ", vconVarId=" + vconVarId +
                ", vconVersion=" + vconVersion +
                '}';
    }
}
