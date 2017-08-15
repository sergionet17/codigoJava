package co.gov.movilidadbogota.sipa.data.persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Representa el tipo de persona
 *
 * @author arturo.cruz
 */
@Entity
public class TipoPersona {

    public static final TipoPersona NATURAL = new TipoPersona(1, "Natural");
    public static final TipoPersona JURIDICA = new TipoPersona(2, "Juridica");
    public static final List<TipoPersona> FULL_SET = Arrays.asList(NATURAL, JURIDICA);

    @Id
    Integer id;
    @NotNull
    String nombre;

    public TipoPersona() {
    }

    public TipoPersona(Integer id, String nombre) {
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
