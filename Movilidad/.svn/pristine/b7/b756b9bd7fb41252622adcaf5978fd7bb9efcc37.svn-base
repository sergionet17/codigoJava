package co.gov.movilidadbogota.sipa.data.aut;

import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.data.loc.Dependencia;
import co.gov.movilidadbogota.sipa.data.persona.Persona;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

/**
 * Clase que representa un usuario del sistema. Este usuario puede provenir,
 * en parte, desde un directorio activo. Sin embargo cualquier usuario del
 * sistema debe tener registro en la base de datos.
 *
 * @author arturo.cruz
 */
@Entity
public class Usuario {

    public static final String USERNAME_ROOT = "root";
    public static final String USERNAME_SISTEMA = "sistema";

    public static final Usuario ROOT = new Usuario(
            UUID.fromString("57b20829-1806-4fd4-9eb7-f568f4642d69"),
            "sipa@movilidadbogota.gov.co",
            USERNAME_ROOT,
            DigestUtils.sha1Hex("57b20829-1806-4fd4-9eb7-f568f4642d69"),
            "2050-01-01",
            Persona.PERSONA_ROOT,
            Perfil.ADMIN_USUARIOS
    );

    public static final Usuario SISTEMA = new Usuario(
            UUID.fromString("6951f38b-332d-46a7-8046-83a781b2e51f"),
            "sipa@movilidadbogota.gov.co",
            USERNAME_SISTEMA,
            DigestUtils.sha1Hex("6951f38b-332d-46a7-8046-83a781b2e51f"),
            "2050-01-01",
            Persona.PERSONA_SISTEMA,
            Perfil.ADMIN_USUARIOS
    );

    public Usuario(UUID id, String email, String username, String password, String fechaCaducidad, Persona persona, Perfil perfil) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        try {
            this.fechaCaducidad = Constants.DATE_FORMAT.parse(fechaCaducidad);
        } catch (ParseException e) {
            // Do nothing
        }
        this.persona = persona;
        this.perfil = perfil;
        this.activo = Boolean.TRUE;
    }

    @Id
    private
    UUID id;

    // TODO: Validación de email
    @NotNull
    @Size(max = Constants.VALOR_MAXIMO_EMAIL)
    private
    String email;

    // TODO: Validación con caracteres permitidos
    @NotNull
    @Size(max = Constants.VALOR_MAXIMO_USERNAME)
    @Column(unique = true)
    private
    String username;

    @Size(max = Constants.VALOR_MAXIMO_PASSWORD)
    private String password;

    @NotNull()
    private Boolean activo;

    @NotNull
    private
    Date fechaCaducidad;

    @ManyToOne
    @JsonIgnore
    private
    Dependencia dependencia;

    @Size(max = Constants.VALOR_MAXIMO_CARGO)
    private String cargo;

    @ManyToOne
    @NotNull
    private
    Persona persona;

    @ManyToOne
    private
    Perfil perfil;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private
    byte[] firma;

    @ManyToOne
    @Transient
    private
    Persona autorizador;

    @ManyToOne
    private
    Documento documentoSoporte;

    public Usuario() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public byte[] getFirma() {
        return firma;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }


    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getDescripcionEstado() {
        if (activo)
            return "Activo";
        else
            return "Inactivo";
    }

    public Persona getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(Persona autorizador) {
        this.autorizador = autorizador;
    }

    public Documento getDocumentoSoporte() {
        return documentoSoporte;
    }

    public void setDocumentoSoporte(Documento documentoSoporte) {
        this.documentoSoporte = documentoSoporte;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Usuario [");
        if (id != null) {
            builder.append("id=");
            builder.append(id);
            builder.append(", ");
        }
        if (email != null) {
            builder.append("email=");
            builder.append(email);
            builder.append(", ");
        }
        if (username != null) {
            builder.append("username=");
            builder.append(username);
            builder.append(", ");
        }
        if (password != null) {
            builder.append("password=");
            builder.append(password);
            builder.append(", ");
        }
        if (activo != null) {
            builder.append("activo=");
            builder.append(activo);
            builder.append(", ");
        }
        if (fechaCaducidad != null) {
            builder.append("fechaCaducidad=");
            builder.append(fechaCaducidad);
            builder.append(", ");
        }
        if (cargo != null) {
            builder.append("cargo=");
            builder.append(cargo);
            builder.append(", ");
        }
        if (persona != null) {
            builder.append("persona=");
            builder.append(persona);
        }
        if (autorizador != null) {
            builder.append("autorizador=");
            builder.append(autorizador);
        }
        builder.append("]");
        return builder.toString();
    }
}
