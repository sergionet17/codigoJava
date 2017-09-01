package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Representa el tipo de comparendo
 *
 * @author arturo.cruz
 */
@Entity
public class TipoComparendo {

    public static final TipoComparendo MANUAL = new TipoComparendo(1, "Manual");
    public static final TipoComparendo ELECTRONICO = new TipoComparendo(2, "Electrónico");

    public static final List<TipoComparendo> FULL_LIST = Arrays.asList(MANUAL, ELECTRONICO);
    @Id
    private Integer id;
    @NotNull
    private String nombre;

    public TipoComparendo() {
    }

    public TipoComparendo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public TipoComparendo(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el objeto mediante que corresponde al identificador. Primero busca en el conjunto completo
     * estático de la clase donde se supone que debería estar. Si el objeto no se encuentra en el conjunto de la clase
     * entonces lo busca en el repositorio.
     *
     * @param id
     * @param fallbackSource
     * @return
     */
    public static TipoComparendo findOne(Integer id, TipoComparendoRepo fallbackSource) {
        Optional<TipoComparendo> item = FULL_LIST.stream().filter(x -> x.getId().equals(id)).findFirst();
        if (item.isPresent()) {
            return item.get();
        } else {
            return fallbackSource.findOne(id);
        }
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
