package co.gov.movilidadbogota.sipa.data.gen;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Created by acpreda on 4/7/17.
 */
@Entity
public class SistemaOrigen {

    public static final SistemaOrigen DUUPS = new SistemaOrigen(1, "DUUPS");

    public static final List<SistemaOrigen> FULL_SET = Arrays.asList(DUUPS);

    @Id
    private
    Integer id;
    @NotNull
    private
    String nombre;

    public SistemaOrigen() {
    }

    public SistemaOrigen(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

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
