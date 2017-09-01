package co.gov.movilidadbogota.sipa.data.loc;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.trans.BaseTableReference;
import co.gov.movilidadbogota.sipa.trans.SipaFieldTableReference;
import co.gov.movilidadbogota.sipa.trans.SipaTableReference;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Clase que representa los departamentos
 *
 * @author arturo.cruz
 */
@Entity
@SipaTableReference(name = "Departamento",
        repository = "co.gov.movilidadbogota.sipa.data.loc.DepartamentoRepo")
public class Departamento extends BaseTableReference {

    public static final String BOGOTA = "11";

    @SipaFieldTableReference(title = "ID", numberColumn = 1)
    @NotNull
    private
    String id;

    @SipaFieldTableReference(title = "Nombre", numberColumn = 2)
    @NotNull
    private
    String nombre;

    @ManyToOne
    @NotNull
    @SipaFieldTableReference(title = "País", fieldRoute = "id", numberColumn = 3,
            repository = "co.gov.movilidadbogota.sipa.data.loc.PaisRepo")
    private
    Pais pais;

    public Departamento() {
    }

    public Departamento(String id, String nombre, UUID identifier, Date inicioVigencia,
                        Date finVigencia, Date fechaCreacion, Usuario usuario, Long version) {
        super(identifier, inicioVigencia, finVigencia, fechaCreacion, usuario, version);
        this.id = id;
        this.nombre = nombre;
        this.pais = Pais.COLOMBIA_OBJ;
        this.setIdentifier(identifier);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
