package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;

/**
 * Representa el tipo de notificacion de mensajes
 *
 * @author diego.gomez
 */
@Entity
public class TipoNotificacion {

    public static final TipoNotificacion SIN_NOTIFICACION = new TipoNotificacion(3, "Sin notificación");
    public static final TipoNotificacion PANTALLA = new TipoNotificacion(4, "Notificacion por pantalla");
    public static final TipoNotificacion EMAIL = new TipoNotificacion(5, "Notificacion por email");
    public static final TipoNotificacion BOTH = new TipoNotificacion(6, "Notificacion por email y pantalla");

    public static final Collection<TipoNotificacion> FULL_SET = Arrays.asList(
            SIN_NOTIFICACION, PANTALLA, EMAIL, BOTH
    );

    @Id
    private Integer id;

    @NotNull
    private String nombre;

    public TipoNotificacion() {
    }

    public TipoNotificacion(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public TipoNotificacion(Integer id) {
        this.id = id;
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
