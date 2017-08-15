package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by dagomez on 7/4/17.
 */
@Entity
public class TipoRangoNumeracion {

    public static final TipoRangoNumeracion COMPARENDO_TRANSITO = new TipoRangoNumeracion(1, "Comparendo de Transito");
    public static final TipoRangoNumeracion INFORME_TRANSPORTE_PUBLICO = new TipoRangoNumeracion(2, "Infracción de Transporte público");
    public static final Collection<TipoRangoNumeracion> FULL_SET = Arrays.asList(
            COMPARENDO_TRANSITO,
            INFORME_TRANSPORTE_PUBLICO
    );

    @Id
    Integer id;
    @NotNull
    String nombre;

    public TipoRangoNumeracion() {

    }

    public TipoRangoNumeracion(Integer id, String nombre) {
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
