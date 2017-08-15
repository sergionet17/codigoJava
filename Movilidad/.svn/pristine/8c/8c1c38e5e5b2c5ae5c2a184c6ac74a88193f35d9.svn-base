package co.gov.movilidadbogota.sipa.data.loc;

import co.gov.movilidadbogota.sipa.data.gen.SistemaOrigen;
import co.gov.movilidadbogota.sipa.data.persona.Persona;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by acpreda on 4/7/17.
 */
@Entity
public class Direccion {

    @Id
    private
    UUID id;

    @NotNull
    private
    String direccion;

    @ManyToOne
    private
    Municipio municipio;

    @ManyToOne
    private
    Localidad localidad;

    @ManyToOne
    private
    SistemaOrigen sistemaOrigen;

    @NotNull
    @ManyToOne
    @JsonIgnore
    private
    Persona persona;

    public Direccion() {
    }

    public Direccion(Persona persona, String direccion) {
        this.id = UUID.randomUUID();
        this.direccion = direccion;
        this.persona = persona;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public SistemaOrigen getSistemaOrigen() {
        return sistemaOrigen;
    }

    public void setSistemaOrigen(SistemaOrigen sistemaOrigen) {
        this.sistemaOrigen = sistemaOrigen;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
