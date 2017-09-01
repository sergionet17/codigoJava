package co.gov.movilidadbogota.sipa.data.biz.trans;

import co.gov.movilidadbogota.sipa.data.persona.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by acpreda on 4/16/17.
 */
@Entity
public class PruebaInvestigacion {

    @Id
    private
    Integer id;
    @NotNull
    private Date fecha;

    @ManyToOne
    @NotNull
    private EstadoPruebaInvestigacion estado;

    @ManyToOne
    @NotNull
    private Persona tercero;

    @ManyToOne
    @NotNull
    private TipoPruebaInvestigacion tipo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoPruebaInvestigacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPruebaInvestigacion estado) {
        this.estado = estado;
    }

    public Persona getTercero() {
        return tercero;
    }

    public void setTercero(Persona tercero) {
        this.tercero = tercero;
    }

    public TipoPruebaInvestigacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoPruebaInvestigacion tipo) {
        this.tipo = tipo;
    }
}
