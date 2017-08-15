package co.gov.movilidadbogota.sipa.data.gen;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Clase que representa los valores de los parametros
 *
 * @author lorenzo.pinango
 */
@Entity
public class ValorParametro {

    @Id
    private
    UUID id;

    @NotNull
    @ManyToOne
    @JsonIgnore
    private
    Parametro parametro;

    @NotNull
    private String valor;

    @NotNull
    private Date inicioVigencia;

    private Date finVigencia;

    @ManyToOne
    private
    Usuario usuario;

    @NotNull
    private Date fechaCreacion;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private
    ValorParametroFile valorParametroFile;

    public ValorParametro() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getFinVigencia() {
        return finVigencia;
    }

    public void setFinVigencia(Date finVigencia) {
        this.finVigencia = finVigencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public ValorParametroFile getValorParametroFile() {
        return valorParametroFile;
    }

    public void setValorParametroFile(ValorParametroFile valorParametroFile) {
        this.valorParametroFile = valorParametroFile;
    }
}