package co.gov.movilidadbogota.sipa.data.doc;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

/**
 * Representa la relación de pertenencia de un documento a una carpeta.
 */
@Entity
public class CarpetaDocumento {

    @Id
    private
    UUID id;

    @ManyToOne
    private
    Carpeta carpeta;

    @ManyToOne
    private
    Documento documento;

    public CarpetaDocumento() {
    }

    public CarpetaDocumento(Carpeta carpeta, Documento documento) {
        this.id = UUID.randomUUID();
        this.carpeta = carpeta;
        this.documento = documento;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Carpeta getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(Carpeta carpeta) {
        this.carpeta = carpeta;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
}
