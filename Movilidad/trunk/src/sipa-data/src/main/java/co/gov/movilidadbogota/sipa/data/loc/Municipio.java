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
 * Clase que representa los municipios
 *
 * @author arturo.cruz
 */
@Entity
@SipaTableReference(name = "Municipio",
        repository = "co.gov.movilidadbogota.sipa.data.loc.MunicipioRepo")
public class Municipio extends BaseTableReference {

    public static final String BOGOTA = "11001";

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
    @SipaFieldTableReference(title = "Departamento", fieldRoute = "id", numberColumn = 3,
            repository = "co.gov.movilidadbogota.sipa.data.loc.DepartamentoRepo")
    private
    Departamento departamento;

    public Municipio() {
    }

    public Municipio(String id, String nombre, Departamento departamento,
                     UUID identifier, Date inicioVigencia, Date finVigencia,
                     Date fechaCreacion, Usuario usuario, Long version) {
        super(identifier, inicioVigencia, finVigencia, fechaCreacion, usuario, version);
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
