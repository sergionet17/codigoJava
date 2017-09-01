package co.gov.movilidadbogota.sipa.data.doc;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class ExpedienteDocumento {

    @Id
    private
    UUID id;

    @ManyToOne
    private
    Expediente expediente;

    @ManyToOne
    private
    Documento documento;

    @ManyToOne
    private
    Actuacion actuacion;

    public ExpedienteDocumento() {
    }

    public ExpedienteDocumento(Expediente expediente, Documento documento, Actuacion actuacion) {
        this.id = UUID.randomUUID();
        this.expediente = expediente;
        this.documento = documento;
        this.actuacion = actuacion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Actuacion getActuacion() {
        return actuacion;
    }

    public void setActuacion(Actuacion actuacion) {
        this.actuacion = actuacion;
    }
}
