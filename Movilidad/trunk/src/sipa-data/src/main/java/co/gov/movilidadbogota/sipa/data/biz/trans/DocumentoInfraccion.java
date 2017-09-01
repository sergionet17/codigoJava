package co.gov.movilidadbogota.sipa.data.biz.trans;

import co.gov.movilidadbogota.sipa.data.biz.comp.Infraccion;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Entidad que contiene un documento y su correspondiente infraccion
 * Created by oscar.mellizo on 03/08/2017.
 */
@Entity
public class DocumentoInfraccion {
    @Id
    private UUID id;

    @OneToOne
    @NotNull
    private Documento documento;

    @OneToOne
    @NotNull
    private Infraccion infraccion;

    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer radicado;

    @NotNull
    private String asunto;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date fecha;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Infraccion getInfraccion() {
        return infraccion;
    }

    public void setInfraccion(Infraccion infraccion) {
        this.infraccion = infraccion;
    }

    public Integer getRadicado() {
        return radicado;
    }

    public void setRadicado(Integer radicado) {
        this.radicado = radicado;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
