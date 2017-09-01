package co.gov.movilidadbogota.sipa.data.loc;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.trans.BaseTableReference;
import co.gov.movilidadbogota.sipa.trans.SipaFieldTableReference;
import co.gov.movilidadbogota.sipa.trans.SipaTableReference;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * Clase que representa un pais
 *
 * @author arturo.cruz
 */
@Entity
@SipaTableReference(name = "País",
        repository = "co.gov.movilidadbogota.sipa.data.loc.PaisRepo")
public class Pais extends BaseTableReference {

    public static final String COLOMBIA = "57";
    public static Pais COLOMBIA_OBJ;

    static {
        try {
            COLOMBIA_OBJ = new Pais(57, "COLOMBIA",
                    UUID.fromString("5cdb2038-3d63-4088-8f3d-fb4a1646f114"),
                    Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Long.valueOf(1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static final Collection<Pais> FULL_SET = Arrays.asList(COLOMBIA_OBJ);

    @NotNull
    @SipaFieldTableReference(title = "ID", numberColumn = 1)
    private
    Integer id;

    @NotNull
    @SipaFieldTableReference(title = "Nombre", numberColumn = 2)
    private
    String nombre;

    public Pais() {
    }

    public Pais(Integer id, String nombre, UUID identifier, Date inicioVigencia,
                Date finVigencia, Date fechaCreacion, Usuario usuario, Long version) {
        super(identifier, inicioVigencia, finVigencia, fechaCreacion, usuario, version);
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
