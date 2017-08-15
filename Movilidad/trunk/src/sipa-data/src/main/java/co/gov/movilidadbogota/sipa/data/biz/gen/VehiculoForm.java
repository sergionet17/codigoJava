package co.gov.movilidadbogota.sipa.data.biz.gen;

/**
 * Created by maria on 6/12/17.
 */
public class VehiculoForm {

    public String placaVehiculo;
    public String numeroComparendo;
    public String estado;
    public String clase;
    public String servicio;
    public String documentoIdentidad;
    public String tipoDocumento;
    public String message = "";

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(String numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
