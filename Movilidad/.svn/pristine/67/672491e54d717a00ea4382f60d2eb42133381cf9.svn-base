package co.gov.movilidadbogota.sipa.data.loc;

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
public class Telefono {

    @Id
    private
    UUID id;

    @NotNull
    private
    String numero;

    @NotNull
    @ManyToOne
    @JsonIgnore
    private
    Persona persona;

    public Telefono() {
    }

    public Telefono(Persona persona, String numero) {
        this.id = UUID.randomUUID();
        this.numero = numero;
        this.persona = persona;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
