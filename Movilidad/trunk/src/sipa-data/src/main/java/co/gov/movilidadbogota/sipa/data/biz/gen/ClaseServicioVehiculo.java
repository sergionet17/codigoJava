package co.gov.movilidadbogota.sipa.data.biz.gen;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Representa el tipo de servicio de vehículo
 *
 * @author arturo.cruz
 */
@Entity
public class ClaseServicioVehiculo {

    public static final ClaseServicioVehiculo DIPLOMATICO = new ClaseServicioVehiculo(1, "Diplomatico");
    public static final ClaseServicioVehiculo OFICIAL = new ClaseServicioVehiculo(2, "Oficial");
    public static final ClaseServicioVehiculo PARTICULAR = new ClaseServicioVehiculo(3, "Particular");
    public static final ClaseServicioVehiculo PUBLICO = new ClaseServicioVehiculo(4, "Público");

    public static final List<ClaseServicioVehiculo> FULL_SET = Arrays.asList(
            ClaseServicioVehiculo.DIPLOMATICO,
            ClaseServicioVehiculo.OFICIAL,
            ClaseServicioVehiculo.PARTICULAR,
            ClaseServicioVehiculo.PUBLICO
    );

    @Id
    Integer id;

    @NotNull
    String nombre;

    public ClaseServicioVehiculo() {
    }

    public ClaseServicioVehiculo(Integer id, String nombre) {
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
