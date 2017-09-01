package co.gov.movilidadbogota.sipa.data.biz.num;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by acpreda on 4/16/17.
 */
@Entity
public class NumeroComparendo {

    @Id
    private
    UUID id;

    private Integer consecutivo;

    private String valor;

    @ManyToOne
    @NotNull
    @JsonIgnore
    private
    RangoNumeracion rangoNumeracion;

    @ManyToOne
    @NotNull
    private
    EstadoNumeroComparendo estado;

    public NumeroComparendo() {
    }

    public NumeroComparendo(String valor) {
        this.valor = valor;
    }

    public NumeroComparendo(RangoNumeracion rangoNumeracion, Integer consecutivo) {
        this.id = UUID.randomUUID();
        this.rangoNumeracion = rangoNumeracion;
        this.consecutivo = consecutivo;
        this.valor = String.format("11001000%s", StringUtils.leftPad(consecutivo.toString(), 12, '0'));
        this.estado = EstadoNumeroComparendo.DISPONIBLE;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public RangoNumeracion getRangoNumeracion() {
        return rangoNumeracion;
    }

    public void setRangoNumeracion(RangoNumeracion rangoNumeracion) {
        this.rangoNumeracion = rangoNumeracion;
    }

    public EstadoNumeroComparendo getEstado() {
        return estado;
    }

    public void setEstado(EstadoNumeroComparendo estado) {
        this.estado = estado;
    }
}
