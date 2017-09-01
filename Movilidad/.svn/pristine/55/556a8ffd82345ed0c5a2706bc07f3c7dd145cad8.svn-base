package co.gov.movilidadbogota.sipa.data.biz.trans;

import co.gov.movilidadbogota.sipa.data.biz.comp.Abogado;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Entidad que indica un documento que fue remitido a un abogado
 * Created by oscar.mellizo on 15/08/2017.
 */
@Entity
public class AbogadoDocumentoRemitido {
    @Id
    private UUID id;

    @ManyToOne
    @NotNull
    private DocumentoInfraccion documentoInfraccion;

    @ManyToOne
    @NotNull
    private Abogado abogado;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DocumentoInfraccion getDocumentoInfraccion() {
        return documentoInfraccion;
    }

    public void setDocumentoInfraccion(DocumentoInfraccion documentoInfraccion) {
        this.documentoInfraccion = documentoInfraccion;
    }

    public Abogado getAbogado() {
        return abogado;
    }

    public void setAbogado(Abogado abogado) {
        this.abogado = abogado;
    }
}
