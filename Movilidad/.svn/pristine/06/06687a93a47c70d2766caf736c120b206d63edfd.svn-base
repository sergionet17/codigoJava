package co.gov.movilidadbogota.sipa.data.biz.comp;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Entidad que representa los datos históricos con los turnos de firma de las autoridades de tránsito.
 *
 * @author maria.rodriguez
 */
@Entity
public class TurnoFirmaHistorico {

    @Id
    private
    UUID id;
    @NotNull
    private Date desde;
    @NotNull
    private Date hasta;
    @NotNull
    @ManyToOne
    private Usuario autoridad;
    @NotNull
    @ManyToOne
    private Usuario suplenteOficial;
    @NotNull
    private Boolean cerrado;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Usuario getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(Usuario autoridad) {
        this.autoridad = autoridad;
    }

    public Usuario getSuplenteOficial() {
        return suplenteOficial;
    }

    public void setSuplenteOficial(Usuario suplenteOficial) {
        this.suplenteOficial = suplenteOficial;
    }

    public Boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }
}
