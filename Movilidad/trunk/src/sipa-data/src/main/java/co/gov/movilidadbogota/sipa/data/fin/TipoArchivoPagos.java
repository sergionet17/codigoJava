package co.gov.movilidadbogota.sipa.data.fin;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

/**
 * Created by acpreda on 7/13/17.
 */
@Entity
public class TipoArchivoPagos {

    public static final TipoArchivoPagos BANCO_OCCIDENTE = new TipoArchivoPagos(1, "Banco de Occidente", "Asobancaria2001");
    public static final TipoArchivoPagos BANCO_CAJA_SOCIAL = new TipoArchivoPagos(2, "Banco Caja Social", "Asobancaria2001");
    public static final TipoArchivoPagos SIMIT = new TipoArchivoPagos(3, "SIMIT", "Simit");
    public static final TipoArchivoPagos HACIENDA = new TipoArchivoPagos(4, "Secretaría de Hacienda", "Hacienda");

    public static final List<TipoArchivoPagos> FULL_SET = Arrays.asList(
            BANCO_CAJA_SOCIAL,
            BANCO_OCCIDENTE,
            SIMIT,
            HACIENDA
    );

    @Id
    private
    Integer id;
    private String nombre;
    private Boolean activo;
    private String classname;

    public TipoArchivoPagos(Integer id, String nombre, String classname) {
        this.id = id;
        this.nombre = nombre;
        this.classname = classname;
    }

    public TipoArchivoPagos() {

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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
