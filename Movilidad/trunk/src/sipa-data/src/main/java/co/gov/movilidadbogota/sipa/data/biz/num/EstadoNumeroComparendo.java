package co.gov.movilidadbogota.sipa.data.biz.num;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Representa el estado de un número de comparendo
 *
 * @author arturo.cruz
 */
@Entity
public class EstadoNumeroComparendo {

    public static final EstadoNumeroComparendo DISPONIBLE = new EstadoNumeroComparendo(0, "Disponible");
    public static final EstadoNumeroComparendo IMPUESTO = new EstadoNumeroComparendo(1, "Impuesto");
    public static final EstadoNumeroComparendo ANULADO = new EstadoNumeroComparendo(2, "Anulado");
    public static final EstadoNumeroComparendo INCONSISTENTE = new EstadoNumeroComparendo(3, "Inconsistente");

    public static final List<EstadoNumeroComparendo> FULL_SET = Arrays.asList(
            DISPONIBLE,
            IMPUESTO,
            ANULADO,
            INCONSISTENTE
    );

    @Id
    private
    Integer id;

    @NotNull
    private
    String nombre;

    public EstadoNumeroComparendo() {
    }

    public EstadoNumeroComparendo(Integer id, String nombre) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstadoNumeroComparendo)) return false;

        EstadoNumeroComparendo that = (EstadoNumeroComparendo) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
