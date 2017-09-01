package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

/**
 * Representa una salida. Esta puede ser una salida normal de patios o una salida por subsanación
 *
 * @author arturo.cruz
 */
@Entity
public class Salida {

    @Id
    private
    UUID id;

    @ManyToOne
    private
    Inmovilizacion inmovilizacion;

    @ManyToOne
    private
    TipoSalida tipoSalida;

    private Date fechaSalida;

    @ManyToOne
    private
    Subsanacion subsanacion;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Inmovilizacion getInmovilizacion() {
        return inmovilizacion;
    }

    public void setInmovilizacion(Inmovilizacion inmovilizacion) {
        this.inmovilizacion = inmovilizacion;
    }

    public TipoSalida getTipoSalida() {
        return tipoSalida;
    }

    public void setTipoSalida(TipoSalida tipoSalida) {
        this.tipoSalida = tipoSalida;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Subsanacion getSubsanacion() {
        return subsanacion;
    }

    public void setSubsanacion(Subsanacion subsanacion) {
        this.subsanacion = subsanacion;
    }
}
