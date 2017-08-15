package co.gov.movilidadbogota.sipa.data.biz.comp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

/**
 * Salon donde se dictan cursos de seguridad vial pertenecientes a una sede
 *
 * @author paola.charry
 */
@Entity
public class Salon {

    @Id
    private
    UUID id;

    private String nombre;

    @ManyToOne
    @JsonIgnore
    private
    Sede sede;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }
}
