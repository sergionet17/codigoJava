package co.gov.movilidadbogota.sipa.data.biz.comp;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.doc.Documento;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Representa la información relativa a las constancias ejecutorias automáticas que genera el sistema
 *
 * @author maria.rodriguez
 */
@Entity
public class ConstanciaEjecutoriaAutomatica {

    @Id
    private
    UUID id;

    @NotNull
    String numero;

    @ManyToOne
    @NotNull
    Comparendo comparendo;

    @ManyToOne
    @NotNull
    Usuario autoridad;

    @ManyToOne
    @NotNull
    Documento documento;

    @NotNull
    Date fechaExpedicion;

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

    public Comparendo getComparendo() {
        return comparendo;
    }

    public void setComparendo(Comparendo comparendo) {
        this.comparendo = comparendo;
    }

    public Usuario getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(Usuario autoridad) {
        this.autoridad = autoridad;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }
}
