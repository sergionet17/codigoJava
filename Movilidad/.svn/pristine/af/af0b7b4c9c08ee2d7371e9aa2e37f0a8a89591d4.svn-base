package co.gov.movilidadbogota.sipa.serv.api;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Datos de una solicitud de registro de transacción para el servicio de registro financiero
 *
 * @author arturo.cruz
 */
public class EventoFinanciero {

    @NotNull
    private String codigoEvento;
    @NotNull
    private String descripcionEvento;
    @NotNull
    private Date fechaEvento;
    @NotNull
    private String tipoReferencia;
    @NotNull
    private UUID referencia;
    @NotNull
    private BigDecimal valor;
    @Transient
    private Map<String, Serializable> parametros;

    public EventoFinanciero() {
    }

    public EventoFinanciero(String codigoEvento, String descripcionEvento, Date fechaEvento, String tipoReferencia, UUID referencia, BigDecimal valor) {
        this.codigoEvento = codigoEvento;
        this.descripcionEvento = descripcionEvento;
        this.fechaEvento = fechaEvento;
        this.tipoReferencia = tipoReferencia;
        this.referencia = referencia;
        this.valor = valor;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getTipoReferencia() {
        return tipoReferencia;
    }

    public void setTipoReferencia(String tipoReferencia) {
        this.tipoReferencia = tipoReferencia;
    }

    public UUID getReferencia() {
        return referencia;
    }

    public void setReferencia(UUID referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Map<String, Serializable> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Serializable> parametros) {
        this.parametros = parametros;
    }

    @Override
    public String toString() {
        return "EventoFinanciero{" +
                "codigoEvento='" + codigoEvento + '\'' +
                ", descripcionEvento='" + descripcionEvento + '\'' +
                ", fechaEvento=" + fechaEvento +
                ", tipoReferencia='" + tipoReferencia + '\'' +
                ", referencia=" + referencia +
                ", valor=" + valor +
                ", parametros=" + parametros +
                '}';
    }
}
