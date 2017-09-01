package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Representa la decisión que se toma en cuanto a un comparendo
 *
 * @author arturo.cruz
 */
@Entity
public class DecisionComparendo {

    public static final DecisionComparendo EXONERADO = new DecisionComparendo(1, "Exonerado");
    public static final DecisionComparendo CONTRAVENTOR = new DecisionComparendo(2, "Contraventor");

    public static final List<DecisionComparendo> FULL_SET = Arrays.asList(
            DecisionComparendo.EXONERADO,
            DecisionComparendo.CONTRAVENTOR
    );

    @Id
    private
    Integer id;
    @NotNull
    private
    String nombre;

    public DecisionComparendo() {
    }

    public DecisionComparendo(Integer id, String nombre) {
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
