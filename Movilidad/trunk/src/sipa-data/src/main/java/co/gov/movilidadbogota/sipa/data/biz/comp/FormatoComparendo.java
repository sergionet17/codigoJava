package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.UUID;

/**
 * Created by acpreda on 4/7/17.
 */
@Entity
public class FormatoComparendo {

    public static final Integer ESTADO_INCONSISTENTE = Integer.valueOf(1);
    public static final Integer ESTADO_CORREGIDO = Integer.valueOf(2);
    public static final Integer ESTADO_IMPUESTO = Integer.valueOf(3);

    @Id
    private UUID id;
    private UUID comparendoId;
    private String numero;
    private String fecha;
    private String hora;
    private String tipo;
    private String direccion;
    private String localidad;
    private String municipio;
    private String departamento;
    private String codigoInfraccion;
    private String placaVehiculo;
    private String tipoVehiculo;
    private String claseServicioVehiculo;
    private String modeloVehiculo;
    private String pasajerosVehiculo;
    private String tipoIdentificacionInfractor;
    private String numeroIdentificacionInfractor;
    private String primerNombreInfractor;
    private String segundoNombreInfractor;
    private String primerApellidoInfractor;
    private String segundoApellidoInfractor;
    private String numeroLicencia;
    private String categoriaLicencia;
    private String direccionInfractor;
    private String municipioDireccionInfractor;
    private String edadInfractor;
    private String telefonoInfractor;
    private String emailInfractor;
    private String tipoInfractor;
    private String organismoTransito;
    private String tipoIdentificacionPropietario;
    private String numeroIdentificacionPropietario;
    private String primerNombrePropietario;
    private String segundoNombrePropietario;
    private String primerApellidoPropietario;
    private String segundoApellidoPropietario;
    private String nitEmpresa;
    private String nombreEmpresa;
    private String tarjetaOperacion;
    private String primerNombreAgente;
    private String segundoNombreAgente;
    private String primerApellidoAgente;
    private String segundoApellidoAgente;
    private String placaAgente;
    private String entidadAgente;
    private String numeroPatio;
    private String direccionPatio;
    private String numeroGrua;
    private String placaGrua;
    private String numeroInmovilizacion;
    private String observaciones;
    private String tipoIdentificacionTestigo;
    private String numeroIdentificacionTestigo;
    private String primerNombreTestigo;
    private String segundoNombreTestigo;
    private String primerApellidoTestigo;
    private String segundoApellidoTestigo;
    private String direccionTestigo;
    private String telefonoTestigo;
    private String valor;
    private Integer estado;
    private String tipoViaPrincipal;
    private String tipoViaSecundaria;
    private String nombreNumeroViaPrincipal;
    private String nombreNumeroViaSecundaria;
    private String nombresPropietario;
    private String apellidosPropietarios;
    @Lob
    private byte[] resultadoValidacion;
    private boolean corregido;

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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCodigoInfraccion() {
        return codigoInfraccion;
    }

    public void setCodigoInfraccion(String codigoInfraccion) {
        this.codigoInfraccion = codigoInfraccion;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getClaseServicioVehiculo() {
        return claseServicioVehiculo;
    }

    public void setClaseServicioVehiculo(String claseServicioVehiculo) {
        this.claseServicioVehiculo = claseServicioVehiculo;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public String getPasajerosVehiculo() {
        return pasajerosVehiculo;
    }

    public void setPasajerosVehiculo(String pasajerosVehiculo) {
        this.pasajerosVehiculo = pasajerosVehiculo;
    }

    public String getTipoIdentificacionInfractor() {
        return tipoIdentificacionInfractor;
    }

    public void setTipoIdentificacionInfractor(String tipoIdentificacionInfractor) {
        this.tipoIdentificacionInfractor = tipoIdentificacionInfractor;
    }

    public String getNumeroIdentificacionInfractor() {
        return numeroIdentificacionInfractor;
    }

    public void setNumeroIdentificacionInfractor(String numeroIdentificacionInfractor) {
        this.numeroIdentificacionInfractor = numeroIdentificacionInfractor;
    }

    public String getPrimerNombreInfractor() {
        return primerNombreInfractor;
    }

    public void setPrimerNombreInfractor(String primerNombreInfractor) {
        this.primerNombreInfractor = primerNombreInfractor;
    }

    public String getSegundoNombreInfractor() {
        return segundoNombreInfractor;
    }

    public void setSegundoNombreInfractor(String segundoNombreInfractor) {
        this.segundoNombreInfractor = segundoNombreInfractor;
    }

    public String getPrimerApellidoInfractor() {
        return primerApellidoInfractor;
    }

    public void setPrimerApellidoInfractor(String primerApellidoInfractor) {
        this.primerApellidoInfractor = primerApellidoInfractor;
    }

    public String getSegundoApellidoInfractor() {
        return segundoApellidoInfractor;
    }

    public void setSegundoApellidoInfractor(String segundoApellidoInfractor) {
        this.segundoApellidoInfractor = segundoApellidoInfractor;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(String categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }

    public String getDireccionInfractor() {
        return direccionInfractor;
    }

    public void setDireccionInfractor(String direccionInfractor) {
        this.direccionInfractor = direccionInfractor;
    }

    public String getMunicipioDireccionInfractor() {
        return municipioDireccionInfractor;
    }

    public void setMunicipioDireccionInfractor(String municipioDireccionInfractor) {
        this.municipioDireccionInfractor = municipioDireccionInfractor;
    }

    public String getEdadInfractor() {
        return edadInfractor;
    }

    public void setEdadInfractor(String edadInfractor) {
        this.edadInfractor = edadInfractor;
    }

    public String getTelefonoInfractor() {
        return telefonoInfractor;
    }

    public void setTelefonoInfractor(String telefonoInfractor) {
        this.telefonoInfractor = telefonoInfractor;
    }

    public String getEmailInfractor() {
        return emailInfractor;
    }

    public void setEmailInfractor(String emailInfractor) {
        this.emailInfractor = emailInfractor;
    }

    public String getTipoInfractor() {
        return tipoInfractor;
    }

    public void setTipoInfractor(String tipoInfractor) {
        this.tipoInfractor = tipoInfractor;
    }

    public String getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(String organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public String getTipoIdentificacionPropietario() {
        return tipoIdentificacionPropietario;
    }

    public void setTipoIdentificacionPropietario(String tipoIdentificacionPropietario) {
        this.tipoIdentificacionPropietario = tipoIdentificacionPropietario;
    }

    public String getNumeroIdentificacionPropietario() {
        return numeroIdentificacionPropietario;
    }

    public void setNumeroIdentificacionPropietario(String numeroIdentificacionPropietario) {
        this.numeroIdentificacionPropietario = numeroIdentificacionPropietario;
    }

    public String getPrimerNombrePropietario() {
        return primerNombrePropietario;
    }

    public void setPrimerNombrePropietario(String primerNombrePropietario) {
        this.primerNombrePropietario = primerNombrePropietario;
    }

    public String getSegundoNombrePropietario() {
        return segundoNombrePropietario;
    }

    public void setSegundoNombrePropietario(String segundoNombrePropietario) {
        this.segundoNombrePropietario = segundoNombrePropietario;
    }

    public String getPrimerApellidoPropietario() {
        return primerApellidoPropietario;
    }

    public void setPrimerApellidoPropietario(String primerApellidoPropietario) {
        this.primerApellidoPropietario = primerApellidoPropietario;
    }

    public String getSegundoApellidoPropietario() {
        return segundoApellidoPropietario;
    }

    public void setSegundoApellidoPropietario(String segundoApellidoPropietario) {
        this.segundoApellidoPropietario = segundoApellidoPropietario;
    }

    public String getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTarjetaOperacion() {
        return tarjetaOperacion;
    }

    public void setTarjetaOperacion(String tarjetaOperacion) {
        this.tarjetaOperacion = tarjetaOperacion;
    }

    public String getPrimerNombreAgente() {
        return primerNombreAgente;
    }

    public void setPrimerNombreAgente(String primerNombreAgente) {
        this.primerNombreAgente = primerNombreAgente;
    }

    public String getSegundoNombreAgente() {
        return segundoNombreAgente;
    }

    public void setSegundoNombreAgente(String segundoNombreAgente) {
        this.segundoNombreAgente = segundoNombreAgente;
    }

    public String getPrimerApellidoAgente() {
        return primerApellidoAgente;
    }

    public void setPrimerApellidoAgente(String primerApellidoAgente) {
        this.primerApellidoAgente = primerApellidoAgente;
    }

    public String getSegundoApellidoAgente() {
        return segundoApellidoAgente;
    }

    public void setSegundoApellidoAgente(String segundoApellidoAgente) {
        this.segundoApellidoAgente = segundoApellidoAgente;
    }

    public String getPlacaAgente() {
        return placaAgente;
    }

    public void setPlacaAgente(String placaAgente) {
        this.placaAgente = placaAgente;
    }

    public String getEntidadAgente() {
        return entidadAgente;
    }

    public void setEntidadAgente(String entidadAgente) {
        this.entidadAgente = entidadAgente;
    }

    public String getNumeroPatio() {
        return numeroPatio;
    }

    public void setNumeroPatio(String numeroPatio) {
        this.numeroPatio = numeroPatio;
    }

    public String getDireccionPatio() {
        return direccionPatio;
    }

    public void setDireccionPatio(String direccionPatio) {
        this.direccionPatio = direccionPatio;
    }

    public String getNumeroGrua() {
        return numeroGrua;
    }

    public void setNumeroGrua(String numeroGrua) {
        this.numeroGrua = numeroGrua;
    }

    public String getPlacaGrua() {
        return placaGrua;
    }

    public void setPlacaGrua(String placaGrua) {
        this.placaGrua = placaGrua;
    }

    public String getNumeroInmovilizacion() {
        return numeroInmovilizacion;
    }

    public void setNumeroInmovilizacion(String numeroInmovilizacion) {
        this.numeroInmovilizacion = numeroInmovilizacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTipoIdentificacionTestigo() {
        return tipoIdentificacionTestigo;
    }

    public void setTipoIdentificacionTestigo(String tipoIdentificacionTestigo) {
        this.tipoIdentificacionTestigo = tipoIdentificacionTestigo;
    }

    public String getNumeroIdentificacionTestigo() {
        return numeroIdentificacionTestigo;
    }

    public void setNumeroIdentificacionTestigo(String numeroIdentificacionTestigo) {
        this.numeroIdentificacionTestigo = numeroIdentificacionTestigo;
    }

    public String getPrimerNombreTestigo() {
        return primerNombreTestigo;
    }

    public void setPrimerNombreTestigo(String primerNombreTestigo) {
        this.primerNombreTestigo = primerNombreTestigo;
    }

    public String getSegundoNombreTestigo() {
        return segundoNombreTestigo;
    }

    public void setSegundoNombreTestigo(String segundoNombreTestigo) {
        this.segundoNombreTestigo = segundoNombreTestigo;
    }

    public String getPrimerApellidoTestigo() {
        return primerApellidoTestigo;
    }

    public void setPrimerApellidoTestigo(String primerApellidoTestigo) {
        this.primerApellidoTestigo = primerApellidoTestigo;
    }

    public String getSegundoApellidoTestigo() {
        return segundoApellidoTestigo;
    }

    public void setSegundoApellidoTestigo(String segundoApellidoTestigo) {
        this.segundoApellidoTestigo = segundoApellidoTestigo;
    }

    public String getDireccionTestigo() {
        return direccionTestigo;
    }

    public void setDireccionTestigo(String direccionTestigo) {
        this.direccionTestigo = direccionTestigo;
    }

    public String getTelefonoTestigo() {
        return telefonoTestigo;
    }

    public void setTelefonoTestigo(String telefonoTestigo) {
        this.telefonoTestigo = telefonoTestigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipoViaPrincipal() {
        return tipoViaPrincipal;
    }

    public void setTipoViaPrincipal(String tipoViaPrincipal) {
        this.tipoViaPrincipal = tipoViaPrincipal;
    }

    public String getTipoViaSecundaria() {
        return tipoViaSecundaria;
    }

    public void setTipoViaSecundaria(String tipoViaSecundaria) {
        this.tipoViaSecundaria = tipoViaSecundaria;
    }

    public String getNombreNumeroViaPrincipal() {
        return nombreNumeroViaPrincipal;
    }

    public void setNombreNumeroViaPrincipal(String nombreNumeroViaPrincipal) {
        this.nombreNumeroViaPrincipal = nombreNumeroViaPrincipal;
    }

    public String getNombreNumeroViaSecundaria() {
        return nombreNumeroViaSecundaria;
    }

    public void setNombreNumeroViaSecundaria(String nombreNumeroViaSecundaria) {
        this.nombreNumeroViaSecundaria = nombreNumeroViaSecundaria;
    }

    public byte[] getResultadoValidacion() {
        return resultadoValidacion;
    }

    public void setResultadoValidacion(byte[] resultadoValidacion) {
        this.resultadoValidacion = resultadoValidacion;
    }

    @Override
    public String toString() {
        return "FormatoComparendo{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", tipo='" + tipo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", municipio='" + municipio + '\'' +
                ", departamento='" + departamento + '\'' +
                ", codigoInfraccion='" + codigoInfraccion + '\'' +
                ", placaVehiculo='" + placaVehiculo + '\'' +
                ", tipoVehiculo='" + tipoVehiculo + '\'' +
                ", claseServicioVehiculo='" + claseServicioVehiculo + '\'' +
                ", modeloVehiculo='" + modeloVehiculo + '\'' +
                ", pasajerosVehiculo='" + pasajerosVehiculo + '\'' +
                ", tipoIdentificacionInfractor='" + tipoIdentificacionInfractor + '\'' +
                ", numeroIdentificacionInfractor='" + numeroIdentificacionInfractor + '\'' +
                ", primerNombreInfractor='" + primerNombreInfractor + '\'' +
                ", segundoNombreInfractor='" + segundoNombreInfractor + '\'' +
                ", primerApellidoInfractor='" + primerApellidoInfractor + '\'' +
                ", segundoApellidoInfractor='" + segundoApellidoInfractor + '\'' +
                ", numeroLicencia='" + numeroLicencia + '\'' +
                ", categoriaLicencia='" + categoriaLicencia + '\'' +
                ", direccionInfractor='" + direccionInfractor + '\'' +
                ", municipioDireccionInfractor='" + municipioDireccionInfractor + '\'' +
                ", edadInfractor='" + edadInfractor + '\'' +
                ", telefonoInfractor='" + telefonoInfractor + '\'' +
                ", emailInfractor='" + emailInfractor + '\'' +
                ", tipoInfractor='" + tipoInfractor + '\'' +
                ", organismoTransito='" + organismoTransito + '\'' +
                ", tipoIdentificacionPropietario='" + tipoIdentificacionPropietario + '\'' +
                ", numeroIdentificacionPropietario='" + numeroIdentificacionPropietario + '\'' +
                ", primerNombrePropietario='" + primerNombrePropietario + '\'' +
                ", segundoNombrePropietario='" + segundoNombrePropietario + '\'' +
                ", primerApellidoPropietario='" + primerApellidoPropietario + '\'' +
                ", segundoApellidoPropietario='" + segundoApellidoPropietario + '\'' +
                ", nitEmpresa='" + nitEmpresa + '\'' +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                ", tarjetaOperacion='" + tarjetaOperacion + '\'' +
                ", primerNombreAgente='" + primerNombreAgente + '\'' +
                ", segundoNombreAgente='" + segundoNombreAgente + '\'' +
                ", primerApellidoAgente='" + primerApellidoAgente + '\'' +
                ", segundoApellidoAgente='" + segundoApellidoAgente + '\'' +
                ", placaAgente='" + placaAgente + '\'' +
                ", entidadAgente='" + entidadAgente + '\'' +
                ", numeroPatio='" + numeroPatio + '\'' +
                ", direccionPatio='" + direccionPatio + '\'' +
                ", numeroGrua='" + numeroGrua + '\'' +
                ", placaGrua='" + placaGrua + '\'' +
                ", numeroInmovilizacion='" + numeroInmovilizacion + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", tipoIdentificacionTestigo='" + tipoIdentificacionTestigo + '\'' +
                ", numeroIdentificacionTestigo='" + numeroIdentificacionTestigo + '\'' +
                ", primerNombreTestigo='" + primerNombreTestigo + '\'' +
                ", segundoNombreTestigo='" + segundoNombreTestigo + '\'' +
                ", primerApellidoTestigo='" + primerApellidoTestigo + '\'' +
                ", segundoApellidoTestigo='" + segundoApellidoTestigo + '\'' +
                ", direccionTestigo='" + direccionTestigo + '\'' +
                ", telefonoTestigo='" + telefonoTestigo + '\'' +
                ", valor='" + valor + '\'' +
                ", estado='" + getEstado() + '\'' +
                ", tipoViaPrincipal='" + tipoViaPrincipal + '\'' +
                ", tipoViaSecundaria='" + tipoViaSecundaria + '\'' +
                ", nombreNumeroViaPrincipal='" + nombreNumeroViaPrincipal + '\'' +
                ", nombreNumeroViaSecundaria='" + nombreNumeroViaSecundaria + '\'' +
                ", resultadoValidacion='" + resultadoValidacion + '\'' +
                '}';
    }

    public String getNombresPropietario() {
        return nombresPropietario;
    }

    public void setNombresPropietario(String nombresPropietario) {
        this.nombresPropietario = nombresPropietario;
    }

    public String getApellidosPropietarios() {
        return apellidosPropietarios;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public void setApellidosPropietarios(String apellidosPropietarios) {
        this.apellidosPropietarios = apellidosPropietarios;
    }

    public boolean isCorregido() {
        return corregido;
    }

    public void setCorregido(boolean corregido) {
        this.corregido = corregido;
    }

    public UUID getComparendoId() {
        return comparendoId;
    }

    public void setComparendoId(UUID comparendoId) {
        this.comparendoId = comparendoId;
    }
}
