package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Representa el tipo salida de patios
 *
 * @author arturo.cruz
 */
@Entity
public class TipoSalida {

    public static final TipoSalida NORMAL = new TipoSalida(1, "Normal");
    public static final TipoSalida PROVISIONAL = new TipoSalida(2, "Provicional");
    @Id
    private
    Integer id;
    private String nombre;

    public TipoSalida() {
    }

    private TipoSalida(Integer id, String nombre) {
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
