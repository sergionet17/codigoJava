package co.gov.movilidadbogota.sipa.data.biz.num;

import co.gov.movilidadbogota.sipa.data.biz.comp.TipoRangoNumeracion;
import co.gov.movilidadbogota.sipa.data.doc.Documento;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by acpreda on 4/16/17.
 */
@Entity
public class AnulacionNumeracion {

    @Id
    private
    UUID id;

    @NotNull
    Date fecha;

    @ManyToOne
    Documento resolucion;

    @ManyToOne
    private
    Documento resolucionAutomatica;

    @OneToMany(cascade = {CascadeType.ALL})
    @NotNull
    List<NumeroComparendo> numeros;

    @Transient
    String rangoTexto;

    @ManyToOne
    TipoRangoNumeracion tipoRangoNumeracion;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Documento getResolucion() {
        return resolucion;
    }

    public void setResolucion(Documento resolucion) {
        this.resolucion = resolucion;
    }

    public List<NumeroComparendo> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<NumeroComparendo> numeros) {
        this.numeros = numeros;
    }

    public String getRangoTexto() {
        return rangoTexto;
    }

    public void setRangoTexto(String rangoTexto) {
        this.rangoTexto = rangoTexto;
    }

    public TipoRangoNumeracion getTipoRangoNumeracion() {
        return tipoRangoNumeracion;
    }

    public void setTipoRangoNumeracion(TipoRangoNumeracion tipoRangoNumeracion) {
        this.tipoRangoNumeracion = tipoRangoNumeracion;
    }

    public Documento getResolucionAutomatica() {
        return resolucionAutomatica;
    }

    public void setResolucionAutomatica(Documento resolucionAutomatica) {
        this.resolucionAutomatica = resolucionAutomatica;
    }
}
