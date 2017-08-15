package co.gov.movilidadbogota.sipa.data.biz.gen;

import co.gov.movilidadbogota.sipa.data.persona.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by acpreda on 4/7/17.
 */
@Entity
public class Vehiculo {

    @Id
    private
    UUID id;

    @NotNull
    private
    String placaVehiculo;

    @ManyToOne
    @NotNull
    private
    TipoVehiculo tipo;

    @ManyToOne
    @NotNull
    private
    ClaseServicioVehiculo clase;

    @ManyToOne
    private
    EstadoVehiculo estado;

    @ManyToOne
    private
    Persona propietario;

    private
    String tarjetaOperacion;

    private
    String modelo;

    private
    String numeroPasajeros;

    public Vehiculo() {
    }

    public Vehiculo(String placaVehiculo, TipoVehiculo tipo, ClaseServicioVehiculo clase) {
        this.id = UUID.randomUUID();
        this.placaVehiculo = placaVehiculo;
        this.tipo = tipo;
        this.clase = clase;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public EstadoVehiculo getEstado() {
        return estado;
    }

    public void setEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public ClaseServicioVehiculo getClase() {
        return clase;
    }

    public void setClase(ClaseServicioVehiculo clase) {
        this.clase = clase;
    }

    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    public String getTarjetaOperacion() {
        return tarjetaOperacion;
    }

    public void setTarjetaOperacion(String tarjetaOperacion) {
        this.tarjetaOperacion = tarjetaOperacion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroPasajeros() {
        return numeroPasajeros;
    }

    public void setNumeroPasajeros(String numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }
}
