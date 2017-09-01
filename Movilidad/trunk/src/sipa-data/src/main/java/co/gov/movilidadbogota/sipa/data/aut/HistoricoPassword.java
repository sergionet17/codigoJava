package co.gov.movilidadbogota.sipa.data.aut;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Clase que representa el historico de contrasenas del usuario
 *
 * @author lorenzo.pinango
 */
@Entity
public class HistoricoPassword {
    @Id
    private
    UUID id;

    @NotNull
    private
    String password;

    @ManyToOne
    @NotNull
    private
    Usuario usuario;

    @Temporal(TemporalType.DATE)
    @NotNull
    private
    Date fechaRegistro;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
