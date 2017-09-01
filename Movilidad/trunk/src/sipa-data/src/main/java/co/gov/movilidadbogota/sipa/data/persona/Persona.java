package co.gov.movilidadbogota.sipa.data.persona;

import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.data.gen.SistemaOrigen;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;
import co.gov.movilidadbogota.sipa.data.loc.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Created by acpreda on 4/7/17.
 */
@Entity
public class Persona {

    public static final Persona PERSONA_ROOT = new Persona(
            UUID.fromString("a1b49bae-5ef9-4384-97e3-e6c609a9fb38"),
            TipoDocumento.CC,
            "0000000000000",
            "Administrador","SIPA",
            TipoPersona.NATURAL
    );

    public static final Persona PERSONA_SISTEMA = new Persona(
            UUID.fromString("e74b79b2-7f61-476a-bd44-155f1c597c17"),
            TipoDocumento.CC,
            "0000000000001",
            "Sistema","SIPA",
            TipoPersona.NATURAL
    );

    public Persona() {
    }

    public Persona(UUID id, TipoDocumento tipoDocumentoIdentidad, String numeroDocumentoIdentidad, String primerNombre, String primerApellido, TipoPersona tipoPersona) {
        this.id = id;
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.tipoPersona = tipoPersona;
    }

    @Id
    private
    UUID id;

    @ManyToOne
    @NotNull
    private
    TipoDocumento tipoDocumentoIdentidad;

    @NotNull
    private
    String numeroDocumentoIdentidad;

    @NotNull
    @Size(max = Constants.VALOR_MAXIMO_NOMBRE)
    private
    String primerNombre;

    @Size(max = Constants.VALOR_MAXIMO_NOMBRE)
    private
    String segundoNombre;

    @NotNull
    @Size(max = Constants.VALOR_MAXIMO_NOMBRE)
    private
    String primerApellido;

    @Size(max = Constants.VALOR_MAXIMO_NOMBRE)
    private
    String segundoApellido;

    @ManyToOne
    @NotNull
    private
    TipoPersona tipoPersona;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private
    List<Direccion> direcciones;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private
    List<Telefono> telefonos;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private
    List<Email> emails;

    public String obtenerNombreCompleto() {
        String nombreCompleto = getPrimerNombre();
        if (getSegundoNombre() != null)
            nombreCompleto = nombreCompleto + " " + getSegundoNombre();
        nombreCompleto = nombreCompleto + " " + getPrimerApellido();
        if (getSegundoApellido() != null)
            nombreCompleto = nombreCompleto + " " + getSegundoApellido();
        return nombreCompleto;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + getId() +
                ", tipoDocumentoIdentidad=" + getTipoDocumentoIdentidad() +
                ", numeroDocumentoIdentidad='" + getNumeroDocumentoIdentidad() + '\'' +
                ", primerNombre='" + getPrimerNombre() + '\'' +
                ", segundoNombre='" + getSegundoNombre() + '\'' +
                ", primerApellido='" + getPrimerApellido() + '\'' +
                ", segundoApellido='" + getSegundoApellido() + '\'' +
                ", tipoPersona=" + getTipoPersona() +
                ", direcciones=" + getDirecciones() +
                ", telefonos=" + getTelefonos() +
                ", emails=" + getEmails() +
                '}';
    }

    public static Persona create(TipoDocumento tipoDocumentoIdentidad, String numeroDocumentoIdentidad, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, TipoPersona tipoPersona) {
        Persona persona;
        persona = new Persona();
        persona.setTipoDocumentoIdentidad(tipoDocumentoIdentidad);
        persona.setNumeroDocumentoIdentidad(numeroDocumentoIdentidad);
        persona.setPrimerApellido(primerApellido);
        persona.setSegundoApellido(segundoApellido);
        persona.setPrimerNombre(primerNombre);
        persona.setSegundoNombre(segundoNombre);
        return persona;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TipoDocumento getTipoDocumentoIdentidad() {
        return tipoDocumentoIdentidad;
    }

    public void setTipoDocumentoIdentidad(TipoDocumento tipoDocumentoIdentidad) {
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
    }

    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
}
