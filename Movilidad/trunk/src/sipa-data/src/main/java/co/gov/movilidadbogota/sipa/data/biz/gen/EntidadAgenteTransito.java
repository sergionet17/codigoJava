package co.gov.movilidadbogota.sipa.data.biz.gen;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by acpreda on 4/7/17.
 */
@Entity
public class EntidadAgenteTransito {

    @Id
    Integer id;
    @NotNull
    String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
