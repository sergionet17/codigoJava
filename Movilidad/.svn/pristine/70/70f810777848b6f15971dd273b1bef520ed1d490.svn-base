package co.gov.movilidadbogota.sipa.data.doc;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Representa un documento del sistema
 *
 * @author arturo.cruz
 */
@Entity
public class Documento {

    @Id
    private UUID id;

    @NotNull
    private Date fechaCreacion;

    @NotNull
    private String contentType;

    @NotNull
    private String originalName;

    @ManyToOne
    @NotNull
    private TipoDocumental tipoDocumental;

    private String externalId;

    private String hashContenido;

    public Documento() {
    }

    public Documento(String contentType, String originalName, TipoDocumental tipoDocumental) {
        this.setId(UUID.randomUUID());
        this.setFechaCreacion(new Date());
        this.setContentType(contentType);
        this.setOriginalName(originalName);
        this.setTipoDocumental(tipoDocumental);
    }

    public Documento(UUID id) {
        this.setId(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
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

    public String getHashContenido() {
        return hashContenido;
    }

    public void setHashContenido(String hashContenido) {
        this.hashContenido = hashContenido;
    }
}
