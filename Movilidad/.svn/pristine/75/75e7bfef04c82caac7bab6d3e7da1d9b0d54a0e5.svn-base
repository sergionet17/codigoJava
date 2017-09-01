package co.gov.movilidadbogota.sipa.data.biz.num;

import co.gov.movilidadbogota.sipa.data.biz.comp.OrganismoTransito;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoRangoNumeracion;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Representa un rango de numeración aprobado
 */
@Entity
public class RangoNumeracion {

    public RangoNumeracion() {
    }

    public RangoNumeracion(UUID id) {
        this.setId(id);
    }

    @ManyToOne
    @NotNull
    private
    TipoRangoNumeracion tipoRangoNumeracion;

    @Id
    private
    UUID id;

    @NotNull
    @Range(min = 1, max = Integer.MAX_VALUE)
    private
    Integer inicio;

    @NotNull
    @Range(min = 1, max = Integer.MAX_VALUE)
    private
    Integer fin;

    @ManyToOne
    private
    Documento solicitud;

    @ManyToOne
    private
    Documento respuestaSolicitud;

    @NotNull
    private
    Date fechaSolicitud;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "rangoNumeracion")
    private
    List<NumeroComparendo> numerosComparendo;

    @ManyToOne
    @NotNull
    private
    OrganismoTransito organismoTransito;

    private Date fechaRespuesta;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getInicio() {
        return inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    public Integer getFin() {
        return fin;
    }

    public void setFin(Integer fin) {
        this.fin = fin;
    }

    public Documento getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Documento solicitud) {
        this.solicitud = solicitud;
    }

    public Documento getRespuestaSolicitud() {
        return respuestaSolicitud;
    }

    public void setRespuestaSolicitud(Documento respuestaSolicitud) {
        this.respuestaSolicitud = respuestaSolicitud;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public List<NumeroComparendo> getNumerosComparendo() {
        return numerosComparendo;
    }

    public void setNumerosComparendo(List<NumeroComparendo> numerosComparendo) {
        this.numerosComparendo = numerosComparendo;
    }

    public TipoRangoNumeracion getTipoRangoNumeracion() {
        return tipoRangoNumeracion;
    }

    public void setTipoRangoNumeracion(TipoRangoNumeracion tipoRangoNumeracion) {
        this.tipoRangoNumeracion = tipoRangoNumeracion;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }
}
