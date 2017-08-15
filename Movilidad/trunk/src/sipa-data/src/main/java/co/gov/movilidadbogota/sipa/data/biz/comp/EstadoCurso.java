package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;

/**
 * Catalogo de los estados del curso
 * <p>
 * Created by paola.charry on 11/06/2017.
 */
@Entity
public class EstadoCurso {

    public static final EstadoCurso VIGENTE = new EstadoCurso(1, "Vigente");
    public static final EstadoCurso TERMINADO = new EstadoCurso(2, "Terminado");
    public static final EstadoCurso DESISTIDO = new EstadoCurso(3, "Desistido");
    public static final Collection<EstadoCurso> FULL_SET = Arrays.asList(
            VIGENTE, TERMINADO, DESISTIDO
    );

    @Id
    private
    Integer id;

    @NotNull
    private
    String nombre;

    public EstadoCurso() {
    }

    public EstadoCurso(Integer id, String nombre) {
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



