package co.gov.movilidadbogota.sipa.data.biz.trans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by acpreda on 4/16/17.
 */
@Entity
public class EstadoPruebaInvestigacion {

    @Id
    private Integer id;

    @NotNull
    private String nombre;

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
