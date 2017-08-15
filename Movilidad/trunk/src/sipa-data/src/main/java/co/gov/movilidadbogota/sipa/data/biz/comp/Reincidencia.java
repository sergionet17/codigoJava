package co.gov.movilidadbogota.sipa.data.biz.comp;

import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.doc.Expediente;
import co.gov.movilidadbogota.sipa.data.persona.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Created by acpreda on 4/16/17.
 */
@Entity
public class Reincidencia {

    @Id
    public UUID id;

    @NotNull
    public Date fecha;

    @ManyToOne
    @NotNull
    public Persona reincidente;

    @ManyToOne
    @NotNull
    public Expediente expediente;

    @ManyToOne
    @NotNull
    public Documento fallo;


    public Boolean notificado;

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

    public Persona getReincidente() {
        return reincidente;
    }

    public void setReincidente(Persona reincidente) {
        this.reincidente = reincidente;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Documento getFallo() {
        return fallo;
    }

    public void setFallo(Documento fallo) {
        this.fallo = fallo;
    }

    public Boolean getNotificado() {
        return notificado;
    }

    public void setNotificado(Boolean notificado) {
        this.notificado = notificado;
    }
}
