package co.gov.movilidadbogota.sipa.data.doc;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class EntradaExpediente {

    public EntradaExpediente() {
    }

    public EntradaExpediente(TipoActuacion tipoActuacion, UUID idActuacion, Expediente expediente, Documento documento) {
        this.id = UUID.randomUUID();
        this.tipoActuacion = tipoActuacion;
        this.idActuacion = idActuacion;
        this.expediente = expediente;
        this.documento = documento;
    }

    @Id
    private
    UUID id;

    @ManyToOne
    @NotNull
    private
    TipoActuacion tipoActuacion;

    @NotNull
    private UUID idActuacion;

    @ManyToOne
    @NotNull
    private
    Expediente expediente;

    @ManyToOne
    @NotNull
    private
    Documento documento;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TipoActuacion getTipoActuacion() {
        return tipoActuacion;
    }

    public void setTipoActuacion(TipoActuacion tipoActuacion) {
        this.tipoActuacion = tipoActuacion;
    }

    public UUID getIdActuacion() {
        return idActuacion;
    }

    public void setIdActuacion(UUID idActuacion) {
        this.idActuacion = idActuacion;
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
}
