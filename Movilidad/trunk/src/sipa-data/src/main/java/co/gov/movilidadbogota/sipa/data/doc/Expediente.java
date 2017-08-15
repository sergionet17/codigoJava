package co.gov.movilidadbogota.sipa.data.doc;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
public class Expediente {

    @Id
    private UUID idOrigen;

    @ManyToOne
    private TipoDocumental tipoDocumental;

    private String externalId;

    @NotNull
    private Date fechaCreacion;

    public Expediente() {
    }

    public Expediente(UUID idOrigen) {
        this.setIdOrigen(idOrigen);
    }

    public Expediente(UUID idOrigen, TipoDocumental tipoDocumental) {
        this.idOrigen = idOrigen;
        this.tipoDocumental = tipoDocumental;
    }

    public UUID getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(UUID idOrigen) {
        this.idOrigen = idOrigen;
    }

    public TipoDocumental getTipoDocumental() {
        return tipoDocumental;
    }

    public void setTipoDocumental(TipoDocumental tipoDocumental) {
        this.tipoDocumental = tipoDocumental;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
