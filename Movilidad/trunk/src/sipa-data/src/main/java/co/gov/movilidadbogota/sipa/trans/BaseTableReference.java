package co.gov.movilidadbogota.sipa.trans;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Clase base para las tablas de referencia
 *
 * @author lorenzo.pinango
 */
@MappedSuperclass
public abstract class BaseTableReference {
    @Id
    private
    UUID identifier;

    @NotNull
    private Date inicioVigencia;

    private Date finVigencia;

    @NotNull
    private Date fechaCreacion;

    @ManyToOne
    private
    Usuario usuario;

    @Version
    @NotNull
    private Long version;

    public BaseTableReference() {
    }

    public BaseTableReference(UUID identifier, Date inicioVigencia, Date finVigencia, Date fechaCreacion, Usuario usuario, Long version) {
        this.identifier = identifier;
        this.inicioVigencia = inicioVigencia;
        this.finVigencia = finVigencia;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.version = version;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFinVigencia() {
        return finVigencia;
    }

    public void setFinVigencia(Date finVigencia) {
        this.finVigencia = finVigencia;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
