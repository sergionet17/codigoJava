package co.gov.movilidadbogota.sipa.data.biz.coa;

import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.loc.Dependencia;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Clase que representa un titulo ejecutivo
 *
 * @author lorenzo.pinango
 */
@Entity
public class TituloEjecutivo {


    @Id
    private UUID id;
    @OneToMany
    private List<Documento> soporte;
    @ManyToOne
    //@NotNull TODO
    private Dependencia dependencia;
    @NotNull
    private Date fechaTitulo;
    @NotNull
    private Date fechaEjecutoria;
    @OneToMany
    @NotNull
    private List<Deudor> deudores;
    @NotNull
    private BigDecimal valor;
    @ManyToOne
    @NotNull
    private EstadoTitulo estado;
    @OneToMany
    private List<TipoCarteraCoactivo> tipoCartera;

    @OneToMany
    private List<TituloEjecutivoReferencia> tituloEjecutivosReferencias;

    @ManyToOne
    private FuenteTituloEjecutivo fuenteTituloEjecutivo;
    @ManyToOne
    @NotNull
    private EtapaTituloEjecutivo etapaTituloEjecutivo;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Documento> getSoporte() {
        return soporte;
    }

    public void setSoporte(List<Documento> soporte) {
        this.soporte = soporte;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public Date getFechaTitulo() {
        return fechaTitulo;
    }

    public void setFechaTitulo(Date fechaTitulo) {
        this.fechaTitulo = fechaTitulo;
    }

    public Date getFechaEjecutoria() {
        return fechaEjecutoria;
    }

    public void setFechaEjecutoria(Date fechaEjecutoria) {
        this.fechaEjecutoria = fechaEjecutoria;
    }

    public List<Deudor> getDeudores() {
        return deudores;
    }

    public void setDeudores(List<Deudor> deudores) {
        this.deudores = deudores;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public EstadoTitulo getEstado() {
        return estado;
    }

    public void setEstado(EstadoTitulo estado) {
        this.estado = estado;
    }

    public List<TipoCarteraCoactivo> getTipoCartera() {
        return tipoCartera;
    }

    public List<TituloEjecutivoReferencia> getTituloEjecutivosReferencias() {
        return tituloEjecutivosReferencias;
    }

    public void setTituloEjecutivosReferencias(List<TituloEjecutivoReferencia> tituloEjecutivosReferencias) {
        this.tituloEjecutivosReferencias = tituloEjecutivosReferencias;
    }

    public void setTipoCartera(List<TipoCarteraCoactivo> tipoCartera) {
        this.tipoCartera = tipoCartera;

    }

    public FuenteTituloEjecutivo getFuenteTituloEjecutivo() {
        return fuenteTituloEjecutivo;
    }

    public void setFuenteTituloEjecutivo(FuenteTituloEjecutivo fuenteTituloEjecutivo) {
        this.fuenteTituloEjecutivo = fuenteTituloEjecutivo;
    }

    public EtapaTituloEjecutivo getEtapaTituloEjecutivo() {
        return etapaTituloEjecutivo;
    }

    public void setEtapaTituloEjecutivo(EtapaTituloEjecutivo etapaTituloEjecutivo) {
        this.etapaTituloEjecutivo = etapaTituloEjecutivo;
    }

    @Override
    public String toString() {
        return "TituloEjecutivo{" +
                "id=" + id +
                ", soporte=" + soporte +
                ", dependencia=" + dependencia +
                ", fechaTitulo=" + fechaTitulo +
                ", fechaEjecutoria=" + fechaEjecutoria +
                ", deudores=" + deudores +
                ", valor=" + valor +
                ", estado=" + estado +
                ", tipoCartera=" + tipoCartera +
                ", tituloEjecutivosReferencias=" + tituloEjecutivosReferencias +
                ", fuenteTituloEjecutivo=" + fuenteTituloEjecutivo +
                ", etapaTituloEjecutivo=" + etapaTituloEjecutivo +
                '}';
    }
}
