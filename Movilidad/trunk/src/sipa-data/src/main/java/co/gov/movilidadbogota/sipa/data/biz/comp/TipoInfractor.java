package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;

/**
 * Representa el tipo de infractor de un comparendo
 *
 * @author diego.gomez
 */
@Entity
public class TipoInfractor {

    public static final TipoInfractor CONDUCTOR = new TipoInfractor(1, "Conductor");
    public static final TipoInfractor PEATON = new TipoInfractor(2, "Peaton");
    public static final TipoInfractor PASAJERO = new TipoInfractor(3, "Pasajero");
    public static final Collection<TipoInfractor> FULL_SET = Arrays.asList(
            CONDUCTOR, PEATON, PASAJERO
    );

    @Id
    private Integer id;

    @NotNull
    private String nombre;

    public TipoInfractor() {
    }

    public TipoInfractor(Integer id, String nombre) {
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
