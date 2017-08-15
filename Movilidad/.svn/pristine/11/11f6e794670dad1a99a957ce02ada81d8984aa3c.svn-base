package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Created by acpreda on 4/7/17.
 */
@Entity
public class OrganismoTransito {

    public static final OrganismoTransito SDM = new OrganismoTransito(1, "Secretaría Distrital de Movilidad");

    public static final List<OrganismoTransito> FULL_SET = Arrays.asList(
            SDM
    );

    @Id
    Integer id;
    @NotNull
    String nombre;

    public OrganismoTransito() {
    }

    public OrganismoTransito(Integer id, String nombre) {
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
