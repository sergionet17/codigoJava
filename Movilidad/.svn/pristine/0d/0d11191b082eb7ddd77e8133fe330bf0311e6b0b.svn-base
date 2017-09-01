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
public class Email {

    @Id
    private
    UUID id;

    @ManyToOne
    @NotNull
    @JsonIgnore
    Persona persona;
    @NotNull
    String email;
    Boolean verificado;
    Boolean rebota;

    public Email() {
    }

    public Email(Persona persona, String email) {
        this.id = UUID.randomUUID();
        this.persona = persona;
        this.email = email;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getVerificado() {
        return verificado;
    }

    public void setVerificado(Boolean verificado) {
        this.verificado = verificado;
    }

    public Boolean getRebota() {
        return rebota;
    }

    public void setRebota(Boolean rebota) {
        this.rebota = rebota;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
