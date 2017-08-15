package co.gov.movilidadbogota.sipa.data.biz.comp;

import co.gov.movilidadbogota.sipa.data.biz.gen.AgenteTransito;
import co.gov.movilidadbogota.sipa.data.biz.gen.Patio;
import co.gov.movilidadbogota.sipa.data.biz.gen.Testigo;
import co.gov.movilidadbogota.sipa.data.biz.gen.Vehiculo;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendo;
import co.gov.movilidadbogota.sipa.data.fin.Entrada;
import co.gov.movilidadbogota.sipa.data.fin.VolantePago;
import co.gov.movilidadbogota.sipa.data.loc.Direccion;
import co.gov.movilidadbogota.sipa.data.persona.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Representa un comparendo dentro del sistema.
 * Created by acpreda on 4/7/17.
 */
@Entity
public class Comparendo {

    public Comparendo() {
    }

    public Comparendo(UUID id) {
        this.id = id;
    }

    @Id
    private
    UUID id;

    @ManyToOne
    @NotNull
    private
    NumeroComparendo numero;

    @NotNull
    private
    Date fechaImposicion;

    @NotNull
    private
    Date fechaRegistro;

    @ManyToOne
    @NotNull
    private
    TipoComparendo tipoComparendo;

    @ManyToOne
    @NotNull
    private
    EstadoComparendo estado;

    @ManyToOne
    @NotNull
    private
    Infraccion infraccion;

    @ManyToOne
    @NotNull
    private
    FormatoComparendo formato;

    private
    Date fechaNotificacion;

    @ManyToOne
    private
    Persona infractor;

    @ManyToOne
    private
    TipoInfractor tipoInfractor;

    @ManyToOne
    private
    DecisionComparendo decision;

    @ManyToOne
    private
    Vehiculo vehiculo;

    private BigDecimal valorNominal;

    @ManyToOne
    private
    Licencia licencia;

    @ManyToOne
    private
    Persona propietario;

    @ManyToOne
    private
    AgenteTransito agenteTransito;

    @ManyToOne
    private
    Direccion direccion;

    @ManyToOne
    private
    Patio patio;

    @ManyToOne
    private
    Vehiculo grua;

    @ManyToOne
    private
    Inmovilizacion inmovilizacion;

    private String observaciones;

    @ManyToOne
    private
    Testigo testigo;

    @ManyToOne
    private
    CursoComparendo cursoComparendo;

    @ManyToOne
    private
    VolantePago volantePago;


    @Transient
    private List<Entrada> cartera;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public NumeroComparendo getNumero() {
        return numero;
    }

    public void setNumero(NumeroComparendo numero) {
        this.numero = numero;
    }

    public Date getFechaImposicion() {
        return fechaImposicion;
    }

    public void setFechaImposicion(Date fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public TipoComparendo getTipoComparendo() {
        return tipoComparendo;
    }

    public void setTipoComparendo(TipoComparendo tipoComparendo) {
        this.tipoComparendo = tipoComparendo;
    }

    public Persona getInfractor() {
        return infractor;
    }

    public void setInfractor(Persona infractor) {
        this.infractor = infractor;
    }

    public TipoInfractor getTipoInfractor() {
        return tipoInfractor;
    }

    public void setTipoInfractor(TipoInfractor tipoInfractor) {
        this.tipoInfractor = tipoInfractor;
    }

    public EstadoComparendo getEstado() {
        return estado;
    }

    public void setEstado(EstadoComparendo estado) {
        this.estado = estado;
    }

    public DecisionComparendo getDecision() {
        return decision;
    }

    public void setDecision(DecisionComparendo decision) {
        this.decision = decision;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Infraccion getInfraccion() {
        return infraccion;
    }

    public void setInfraccion(Infraccion infraccion) {
        this.infraccion = infraccion;
    }

    public BigDecimal getValorNominal() {
        return valorNominal;
    }

    public void setValorNominal(BigDecimal valorNominal) {
        this.valorNominal = valorNominal;
    }

    public Licencia getLicencia() {
        return licencia;
    }

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    public AgenteTransito getAgenteTransito() {
        return agenteTransito;
    }

    public void setAgenteTransito(AgenteTransito agenteTransito) {
        this.agenteTransito = agenteTransito;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

    public Vehiculo getGrua() {
        return grua;
    }

    public void setGrua(Vehiculo grua) {
        this.grua = grua;
    }

    public Inmovilizacion getInmovilizacion() {
        return inmovilizacion;
    }

    public void setInmovilizacion(Inmovilizacion inmovilizacion) {
        this.inmovilizacion = inmovilizacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Testigo getTestigo() {
        return testigo;
    }

    public void setTestigo(Testigo testigo) {
        this.testigo = testigo;
    }

    public FormatoComparendo getFormato() {
        return formato;
    }

    public void setFormato(FormatoComparendo formato) {
        this.formato = formato;
    }

    public CursoComparendo getCursoComparendo() {
        return cursoComparendo;
    }

    public void setCursoComparendo(CursoComparendo cursoComparendo) {
        this.cursoComparendo = cursoComparendo;
    }

    public List<Entrada> getCartera() {
        return cartera;
    }

    public void setCartera(List<Entrada> cartera) {
        this.cartera = cartera;
    }

    public VolantePago getVolantePago() { return volantePago; }

    public void setVolantePago(VolantePago volantePago) { this.volantePago = volantePago; }

}
