package co.gov.movilidadbogota.sipa.data.gen;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by acpreda on 7/18/17.
 */
@Entity
public class TipoDia {

    public static final TipoDia CALENDARIO = new TipoDia(1, "Calendario");
    public static final TipoDia HABIL = new TipoDia(2, "Hábil");
    public static final Collection<TipoDia> FULL_SET = Arrays.asList(
            CALENDARIO, HABIL
    );
    @Id
    private
    Integer id;
    private String nombre;

    public TipoDia() {
    }

    public TipoDia(Integer id, String nombre) {
        this.setId(id);
        this.setNombre(nombre);
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
