package co.gov.movilidadbogota.sipa.data.biz.gen;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Representa el estado en el que se encuentra un vehículo
 *
 * @author arturo.cruz
 */
@Entity
public class EstadoVehiculo {

    public static final EstadoVehiculo EN_CIRCULACION = new EstadoVehiculo(1, "En circulación");
    public static final EstadoVehiculo INMOVILIZADO = new EstadoVehiculo(2, "Inmovilizado");
    public static final EstadoVehiculo EN_SUBSANACION = new EstadoVehiculo(3, "En subsanación");

    public static final List<EstadoVehiculo> FULL_SET = Arrays.asList(
            EstadoVehiculo.EN_CIRCULACION,
            EstadoVehiculo.INMOVILIZADO,
            EstadoVehiculo.EN_SUBSANACION
    );

    @Id
    private
    Integer id;
    @NotNull
    private
    String nombre;

    public EstadoVehiculo() {
    }

    public EstadoVehiculo(Integer id, String nombre) {
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
