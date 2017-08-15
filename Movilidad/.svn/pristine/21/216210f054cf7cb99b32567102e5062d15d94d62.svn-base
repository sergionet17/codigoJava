package co.gov.movilidadbogota.sipa.data.biz.comp;

import co.gov.movilidadbogota.sipa.data.persona.Persona;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

/**
 * Representa un instructor que imparte un {@link Instructor}
 *
 * @author paola.charry
 */
@Entity
public class Instructor {

    @Id
    private
    UUID id;

    @ManyToOne
    private
    Persona persona;

    private Date inicioVinculacion;
    private Date finVinculacion;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Date getInicioVinculacion() {
        return inicioVinculacion;
    }

    public void setInicioVinculacion(Date inicioVinculacion) {
        this.inicioVinculacion = inicioVinculacion;
    }

    public Date getFinVinculacion() {
        return finVinculacion;
    }

    public void setFinVinculacion(Date finVinculacion) {
        this.finVinculacion = finVinculacion;
    }



}
