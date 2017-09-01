package co.gov.movilidadbogota.sipa.data.biz.trans;

import co.gov.movilidadbogota.sipa.data.doc.TipoPlantilla;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by JohnOchoa on 10/08/2017.
 */

@Entity
public class TipoPruebaProcesoContravencional {


    public static final TipoPruebaProcesoContravencional SUSTANCIO_FALLO = new TipoPruebaProcesoContravencional(1, "Sustancio Fallo");
    public static final TipoPruebaProcesoContravencional SUSTANCION_CONTINUA = new TipoPruebaProcesoContravencional(2, "Sustanció continua");
    public static final TipoPruebaProcesoContravencional SUSTANCION_PRUEBA = new TipoPruebaProcesoContravencional(3, "Sustancio prueba");
    public static final TipoPruebaProcesoContravencional OTRO = new TipoPruebaProcesoContravencional(4, "Otro");
    public static final Collection<TipoPruebaProcesoContravencional> FULL_SET = Arrays.asList(
            SUSTANCIO_FALLO, SUSTANCION_CONTINUA, SUSTANCION_PRUEBA, OTRO
    );

    @Id
    private Integer id;
    private String nombre;

    public TipoPruebaProcesoContravencional() {
    }

    public TipoPruebaProcesoContravencional(Integer id, String nombre) {
        this.setId(id);
        this.setNombre(nombre);
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
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (nombre != null) {
            builder.append(nombre);
        }
        return builder.toString();
    }
}