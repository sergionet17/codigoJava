package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Representa el estado del comparendo
 *
 * @author arturo.cruz
 */
@Entity
public class EstadoComparendo {

    public static final EstadoComparendo PENDIENTE_FALLO = new EstadoComparendo(1, "Pendiente de fallo");
    public static final EstadoComparendo IMPUGNADO = new EstadoComparendo(2, "Impugnado");
    public static final EstadoComparendo EN_FIRME = new EstadoComparendo(3, "En firme");
    public static final EstadoComparendo PAGADO = new EstadoComparendo(4, "Pagado");
    public static final EstadoComparendo PRESCCRITO = new EstadoComparendo(5, "Prescrito");
    public static final EstadoComparendo CADUCADO = new EstadoComparendo(6, "Caducado");
    public static final EstadoComparendo IMPUESTO = new EstadoComparendo(7, "Impuesto");
    public static final EstadoComparendo CON_FALLO = new EstadoComparendo(8, "Con fallo");

    public static final List<EstadoComparendo> FULL_LIST = Arrays.asList(
            EstadoComparendo.CADUCADO,
            EstadoComparendo.EN_FIRME,
            EstadoComparendo.IMPUGNADO,
            EstadoComparendo.PAGADO,
            EstadoComparendo.PENDIENTE_FALLO,
            EstadoComparendo.PRESCCRITO,
            EstadoComparendo.IMPUESTO,
            EstadoComparendo.CON_FALLO
    );

    @Id
    private
    Integer id;
    @NotNull
    private
    String nombre;

    public EstadoComparendo() {
    }

    public EstadoComparendo(Integer id, String nombre) {
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
