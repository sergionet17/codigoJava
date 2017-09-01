package co.gov.movilidadbogota.sipa.data.fin;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by acpreda on 7/13/17.
 */
@Entity
public class EstadoPago {

    public static final EstadoPago NO_APLICADO = new EstadoPago(1, "No aplicado");
    public static final EstadoPago APLICADO = new EstadoPago(2, "Aplicado");

    public static final List<EstadoPago> FULL_SET = Collections.unmodifiableList(Arrays.asList(
            NO_APLICADO,
            APLICADO
    ));

    public EstadoPago() {
    }

    public EstadoPago(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Id
    private
    Integer id;

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
