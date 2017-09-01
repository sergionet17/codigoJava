package co.gov.movilidadbogota.sipa.data.biz.trans;

import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;
/**
 * Entidad que es una averiguacion preliminar
 * Created by oscar.mellizo on 08/08/2017.
 */
@Entity
public class AveriguacionPreliminar {
    @Id
    private UUID id;

    @NotNull
    private Integer numero;

    @NotNull
    private String indagado;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date fecha;

    @NotNull
    private String origen;

    @ManyToOne
    @NotNull
    private TipoDocumento tipoDocumento;

    @NotNull
    private String documento;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getIndagado() {
        return indagado;
    }

    public void setIndagado(String indagado) {
        this.indagado = indagado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
