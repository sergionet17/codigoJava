package co.gov.movilidadbogota.sipa.data.biz.trans;

import co.gov.movilidadbogota.sipa.data.doc.Carpeta;
import co.gov.movilidadbogota.sipa.data.persona.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Representa una investigación al transporte público
 *
 * @author arturo.cruz
 */
@Entity
public class Investigacion {

    @Id
    private
    UUID id;
    @NotNull
    private
    Date fecha;

    @ManyToOne
    @NotNull
    private
    Carpeta carpeta;

    @ManyToOne
    @NotNull
    private
    EstadoInvestigacion estado;

    private Boolean recibioAlegatos;

    @ManyToOne
    private
    FalloInvestigacion fallo;

    @OneToMany
    private
    List<PruebaInvestigacion> pruebas;

    @OneToMany
    private
    List<Persona> tercero;

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

    public Carpeta getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(Carpeta carpeta) {
        this.carpeta = carpeta;
    }

    public EstadoInvestigacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoInvestigacion estado) {
        this.estado = estado;
    }

    public Boolean getRecibioAlegatos() {
        return recibioAlegatos;
    }

    public void setRecibioAlegatos(Boolean recibioAlegatos) {
        this.recibioAlegatos = recibioAlegatos;
    }

    public FalloInvestigacion getFallo() {
        return fallo;
    }

    public void setFallo(FalloInvestigacion fallo) {
        this.fallo = fallo;
    }

    public List<PruebaInvestigacion> getPruebas() {
        return pruebas;
    }

    public void setPruebas(List<PruebaInvestigacion> pruebas) {
        this.pruebas = pruebas;
    }

    public List<Persona> getTercero() {
        return tercero;
    }

    public void setTercero(List<Persona> tercero) {
        this.tercero = tercero;
    }
}
