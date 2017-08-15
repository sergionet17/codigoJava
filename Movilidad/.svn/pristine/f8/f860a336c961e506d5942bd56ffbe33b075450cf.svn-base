package co.gov.movilidadbogota.sipa.data.doc;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class TipoActuacion {

    public static final TipoActuacion FALLO = new TipoActuacion(1, "Fallo");
    public static final TipoActuacion APERTURA_AUDIENCIA_ACEPTACION = new TipoActuacion(2, "Apertura de audiencia de aceptación");
    public static final TipoActuacion APERTURA_AUDIENCIA_IMPUGNACION = new TipoActuacion(3, "Apertura de audiencia de impugnación");
    public static final TipoActuacion CONTINUACION_AUDIENCIA = new TipoActuacion(4, "Continuación de audiencia");

    public static final Collection<TipoActuacion> FULL_SET = Arrays.asList(
            FALLO,
            APERTURA_AUDIENCIA_ACEPTACION,
            APERTURA_AUDIENCIA_IMPUGNACION,
            CONTINUACION_AUDIENCIA
    );

    public TipoActuacion() {
    }

    public TipoActuacion(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Id
    private Integer id;

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
