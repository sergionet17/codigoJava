package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by acpreda on 4/8/17.
 */
@Entity
public class TipoAudienciaComparendo {


    public static final TipoAudienciaComparendo AUDIENCIA_INMOVILIZACION = new TipoAudienciaComparendo(1, "Audiencia en que se comparece con abogado o practica de pruebas sobre inmovilización de vehículo");
    public static final TipoAudienciaComparendo AUDIENCIA_FALLO = new TipoAudienciaComparendo(2, "Audiencia de práctica de pruebas o notificación de fallo");
    public static final TipoAudienciaComparendo AUDIENCIA_SIN_CUPO = new TipoAudienciaComparendo(3, "Rango cuando no encuentra cupo para audiencia se habilitan todos los horarios");

    public static final TipoAudienciaComparendo AUDIENCIA_ACEPTACION = new TipoAudienciaComparendo(4, "Audiencia de aceptación de comparendo");
    public static final TipoAudienciaComparendo AUDIENCIA_IMPUGNACION = new TipoAudienciaComparendo(5, "Audiencia de impugnación de comparendo");
    public static final TipoAudienciaComparendo AUDIENCIA_CONTINUACION = new TipoAudienciaComparendo(5, "Audiencia de continuación");

    public static final Collection<TipoAudienciaComparendo> FULL_SET = Arrays.asList(
            AUDIENCIA_INMOVILIZACION,
            AUDIENCIA_FALLO,
            AUDIENCIA_SIN_CUPO,
            AUDIENCIA_ACEPTACION,
            AUDIENCIA_IMPUGNACION,
            AUDIENCIA_CONTINUACION
    );

    @Id
    Integer id;
    @NotNull
    String nombre;

    public TipoAudienciaComparendo() {
    }

    public TipoAudienciaComparendo(Integer id, String nombre) {
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
