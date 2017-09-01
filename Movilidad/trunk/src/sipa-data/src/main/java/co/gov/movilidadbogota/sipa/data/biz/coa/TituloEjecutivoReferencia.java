package co.gov.movilidadbogota.sipa.data.biz.coa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class TituloEjecutivoReferencia {

    @Id
    private UUID id;

    @ManyToOne
    @NotNull
    private TituloEjecutivo tituloEjecutivo;

    @NotNull
    private UUID referencia;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TituloEjecutivo getTituloEjecutivo() {
        return tituloEjecutivo;
    }

    public void setTituloEjecutivo(TituloEjecutivo tituloEjecutivo) {
        this.tituloEjecutivo = tituloEjecutivo;
    }

    public UUID getReferencia() {
        return referencia;
    }

    public void setReferencia(UUID referencia) {
        this.referencia = referencia;
    }
}
