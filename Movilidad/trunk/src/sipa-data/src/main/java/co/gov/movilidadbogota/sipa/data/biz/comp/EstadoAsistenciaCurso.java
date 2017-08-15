package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;

/**
 * Estado de la asistencia del curso pedagogico de un infractor de comparendo
 * <p>
 * Created by paola.charry on 11/06/2017.
 */
@Entity
public class EstadoAsistenciaCurso {

    public static final EstadoAsistenciaCurso INSCRITO = new EstadoAsistenciaCurso(1, "Inscrito");
    public static final EstadoAsistenciaCurso ASISTIDO = new EstadoAsistenciaCurso(2, "Asistido");
    public static final EstadoAsistenciaCurso NO_ASISTIDO = new EstadoAsistenciaCurso(3, "No asistio");
    public static final EstadoAsistenciaCurso CANCELADO = new EstadoAsistenciaCurso(4, "Cancelado");
    public static final Collection<EstadoAsistenciaCurso> FULL_SET = Arrays.asList(
            INSCRITO, ASISTIDO, NO_ASISTIDO, CANCELADO
    );

    @Id

    private
    Integer id;
    @NotNull
    private
    String nombre;

    public EstadoAsistenciaCurso() {
    }

    public EstadoAsistenciaCurso(Integer id, String nombre) {
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



