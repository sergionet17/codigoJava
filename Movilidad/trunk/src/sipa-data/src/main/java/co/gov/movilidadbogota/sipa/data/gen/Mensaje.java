package co.gov.movilidadbogota.sipa.data.gen;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacion;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Se parametrizan los mensajes y/o Alertas generados por el sistema
 * Created by diego.gomez on 06/07/2017.
 */
@Entity
public class Mensaje {

    public Mensaje() {
    }

    public Mensaje(Usuario usuarioFuente, Usuario usuarioDestino, String mensaje, TipoNotificacion tipoNotificacion) {
        this.usuarioFuente = usuarioFuente;
        this.usuarioDestino = usuarioDestino;
        this.mensaje = mensaje;
        this.tipoNotificacion = tipoNotificacion;
        this.id = UUID.randomUUID();
        this.fechaCreacion = new Date();
        this.leido = false;
    }

    @Id
    private
    UUID id;

    @ManyToOne
    @NotNull
    private
    Usuario usuarioFuente;

    @ManyToOne
    @NotNull
    private
    Usuario usuarioDestino;

    @NotNull
    @Lob
    private
    String mensaje;

    @NotNull
    private
    boolean leido;

    @NotNull
    private
    Date fechaCreacion;

    @ManyToOne
    @NotNull
    private
    TipoNotificacion tipoNotificacion;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getUsuarioFuente() {
        return usuarioFuente;
    }

    public void setUsuarioFuente(Usuario usuarioFuente) {
        this.usuarioFuente = usuarioFuente;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }
}

