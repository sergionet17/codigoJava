package co.gov.movilidadbogota.sipa.data.fin;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by acpreda on 7/13/17.
 */
@Entity
public class ArchivoPagos {

    @Id
    private
    UUID id;
    private Date fecha;

    @ManyToOne
    private
    Usuario usuario;

    @ManyToOne
    private
    TipoArchivoPagos tipo;

    private Integer cargados;
    private Integer rechazados;
    private BigDecimal valorCargado;
    private BigDecimal valorTotal;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private
    byte[] contenido;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private
    String errores;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "archivoPagos")
    private List<Pago> pagos;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoArchivoPagos getTipo() {
        return tipo;
    }

    public void setTipo(TipoArchivoPagos tipo) {
        this.tipo = tipo;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getErrores() {
        return errores;
    }

    public void setErrores(String errores) {
        this.errores = errores;
    }

    public Integer getCargados() {
        return cargados;
    }

    public void setCargados(Integer cargados) {
        this.cargados = cargados;
    }

    public Integer getRechazados() {
        return rechazados;
    }

    public void setRechazados(Integer rechazados) {
        this.rechazados = rechazados;
    }

    public BigDecimal getValorCargado() {
        return valorCargado;
    }

    public void setValorCargado(BigDecimal valorCargado) {
        this.valorCargado = valorCargado;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
}
