package co.gov.movilidadbogota.sipa.data.doc;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Representa un expediente que almacena documentos
 *
 * @author maria.rodriguez
 */
@Entity
public class Carpeta {

    @Id
    private
    UUID id;

    private
    String numero;

    private
    String nombre;

    private String referencia;

    private String referenciaId;

    public Carpeta() {
    }

    public Carpeta(UUID id) {
        this.id = id;
    }

    public Carpeta(String numero, String nombre) {
        this.id = UUID.randomUUID();
        this.numero = numero;
        this.nombre = nombre;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getReferenciaId() {
        return referenciaId;
    }

    public void setReferenciaId(String referenciaId) {
        this.referenciaId = referenciaId;
    }
}
