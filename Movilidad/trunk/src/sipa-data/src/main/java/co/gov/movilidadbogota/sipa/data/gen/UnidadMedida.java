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
public class UnidadMedida {

    public static final UnidadMedida SMMLV = new UnidadMedida(1, "SMMLV");
    public static final UnidadMedida SMDLV = new UnidadMedida(2, "SMDLV");

    public static final List<UnidadMedida> FULL_LIST = Arrays.asList(SMDLV, SMMLV);

    @Id
    private
    Integer id;
    @NotNull
    private
    String nombre;

    public UnidadMedida() {
    }

    public UnidadMedida(Integer id) {
        this.id = id;
    }

    public UnidadMedida(Integer id, String nombre) {
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
