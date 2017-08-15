package co.gov.movilidadbogota.sipa.data.biz.comp;

import co.gov.movilidadbogota.sipa.data.persona.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by acpreda on 4/7/17.
 */
@Entity
public class Licencia {

    @Id
    UUID id;

    @ManyToOne
    @NotNull
    Persona persona;

    @NotNull
    String numero;

    @OneToOne
    @NotNull
    CategoriaLicencia categoriaLicencia;

    @ManyToOne
    OrganismoTransito organismoTransito;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public CategoriaLicencia getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(CategoriaLicencia categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }
}
