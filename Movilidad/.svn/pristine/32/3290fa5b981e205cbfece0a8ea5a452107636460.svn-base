package co.gov.movilidadbogota.sipa.data.biz.coa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que representa el estado del titulo ejecutivo
 *
 * @author lorenzo.pinango
 */
@Entity
public class EstadoTitulo {

    public static final EstadoTitulo VIGENTE = new EstadoTitulo(1, "Vigente");
    public static final EstadoTitulo PREESCRITO = new EstadoTitulo(2, "Prescrito");
    public static final EstadoTitulo CANCELADO = new EstadoTitulo(3, "Cancelado");

    public static final List<EstadoTitulo> FULL_LIST = Arrays.asList(VIGENTE, PREESCRITO, CANCELADO);

    @Id
    Integer id;
    @NotNull
    String nombre;

    public EstadoTitulo() {
    }

    public EstadoTitulo(Integer id) {
        this.id = id;
    }

    public EstadoTitulo(Integer id, String nombre) {
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
