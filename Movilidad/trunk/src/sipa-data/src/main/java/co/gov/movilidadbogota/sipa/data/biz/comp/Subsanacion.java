package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Representa la subsanación de una salida provisional de patios
 *
 * @author arturo.cruz
 */
@Entity
public class Subsanacion {

    @Id
    private
    UUID id;

    @OneToMany(cascade = CascadeType.ALL)
    private
    List<CausalSubsanacion> causalesSubsanacion;

    @NotNull
    private
    Date fechaLimite;

    @ManyToOne
    private Salida salida;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<CausalSubsanacion> getCausalesSubsanacion() {
        return causalesSubsanacion;
    }

    public void setCausalesSubsanacion(List<CausalSubsanacion> causalesSubsanacion) {
        this.causalesSubsanacion = causalesSubsanacion;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Salida getSalida() {
        return salida;
    }

    public void setSalida(Salida salida) {
        this.salida = salida;
    }
}
