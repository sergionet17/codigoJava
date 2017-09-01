package co.gov.movilidadbogota.sipa.data.gen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Clase que representa los archivos de los parametros
 *
 * @author lorenzo.pinango
 */
@Entity
public class ValorParametroFile {

    @Id
    private
    UUID id;

    @NotNull
    @OneToOne
    @JsonIgnore
    private
    ValorParametro valorParametro;

    @NotNull
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private
    byte[] archivo;

    @NotNull
    private String mimeType;

    public ValorParametroFile() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public ValorParametro getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(ValorParametro valorParametro) {
        this.valorParametro = valorParametro;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}

