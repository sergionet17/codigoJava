package co.gov.movilidadbogota.sipa.data.gen;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import  co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacion;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

/**
 * Created by acpreda on 7/18/17.
 */
@Entity
public class AlarmaRango {

    @Id
    private
    UUID id;

    @ManyToOne
    private
    Alarma alarma;

    private String nombre;

    private Integer dias;

    @ManyToOne
    private
    TipoDia tipoDia;

    @ManyToOne
    private
    TipoNotificacion tipoNotificacion;

    @ManyToOne
    private
    Usuario superior;



    private
    Date cumplimiento;



    public static final AlarmaRango RANGO1 = new AlarmaRango(
            UUID.randomUUID(),
            30,
            "Verde"
    );

    public static final AlarmaRango RANGO2 = new AlarmaRango(
            UUID.randomUUID(),
            15,
            "Amarillo"

    );

    public static final AlarmaRango RANGO3 = new AlarmaRango(
            UUID.randomUUID(),
            5,
            "Rojo"
    );

    public AlarmaRango(){

    }


    public AlarmaRango(UUID id, Integer dias, String nombre){
        this.id = id;
        this.dias = dias;
        this.nombre = nombre;
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Alarma getAlarma() {
        return alarma;
    }

    public void setAlarma(Alarma alarma) {
        this.alarma = alarma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoDia getTipoDia() {
        return tipoDia;
    }

    public void setTipoDia(TipoDia tipoDia) {
        this.tipoDia = tipoDia;
    }

    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public Usuario getSuperior() {
        return superior;
    }

    public void setSuperior(Usuario superior) {
        this.superior = superior;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Date getCumplimiento() { return cumplimiento; }

    public void setCumplimiento(Date cumplimiento) { this.cumplimiento = cumplimiento; }

}
