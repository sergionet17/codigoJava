package co.gov.movilidadbogota.sipa.data.biz.coa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que representa la etapa del titulo ejecutivo
 *
 * @author lorenzo.pinango
 */
@Entity
public class EtapaTituloEjecutivo {

    public static final EtapaTituloEjecutivo INSTACIA_PROCESO_COACTIVO = new EtapaTituloEjecutivo(1, "Instacia de proceso coativo");
    public static final EtapaTituloEjecutivo COBRO_PERSUASIVO = new EtapaTituloEjecutivo(2, "Cobro persuasivo");

    public static final List<EtapaTituloEjecutivo> FULL_LIST = Arrays.asList(COBRO_PERSUASIVO, INSTACIA_PROCESO_COACTIVO);

    @Id
    Integer id;
    @NotNull
    String nombre;

    public EtapaTituloEjecutivo() {
    }

    public EtapaTituloEjecutivo(Integer id) {
        this.id = id;
    }

    public EtapaTituloEjecutivo(Integer id, String nombre) {
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
