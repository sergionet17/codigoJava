package co.gov.movilidadbogota.sipa.data.doc;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

/**
 * Es el tipo de plantilla
 */
@Entity
public class TipoPlantilla {

    public static final TipoPlantilla FREEMARKER = new TipoPlantilla(1, "FREEMARKER");
    public static final TipoPlantilla JASPERREPORTS = new TipoPlantilla(2, "JASPERREPORTS");
    public static final TipoPlantilla XDOCREPORT = new TipoPlantilla(3, "XDOCREPORT");
    public static final Collection<TipoPlantilla> FULL_SET = Arrays.asList(
            FREEMARKER, JASPERREPORTS, XDOCREPORT
    );

    @Id
    private Integer id;
    private String nombre;

    public TipoPlantilla() {
    }

    public TipoPlantilla(Integer id, String nombre) {
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
}
