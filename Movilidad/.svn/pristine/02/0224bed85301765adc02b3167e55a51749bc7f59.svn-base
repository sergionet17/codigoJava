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
 * Clase que representa las localidades
 *
 * @author arturo.cruz
 */
@Entity
@SipaTableReference(name = "Localidad",
        repository = "co.gov.movilidadbogota.sipa.data.loc.LocalidadRepo")
public class Localidad extends BaseTableReference {

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
    @SipaFieldTableReference(title = "Municipio", fieldRoute = "id", numberColumn = 3,
            repository = "co.gov.movilidadbogota.sipa.data.loc.MunicipioRepo")
    private
    Municipio municipio;

    public Localidad() {
    }

    public Localidad(String id, String nombre, Municipio municipio,
                     UUID identifier, Date inicioVigencia, Date finVigencia,
                     Date fechaCreacion, Usuario usuario, Long version) {
        super(identifier, inicioVigencia, finVigencia, fechaCreacion, usuario, version);
        this.id = id;
        this.nombre = nombre;
        this.municipio = municipio;
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

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
}
