package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Entidad que representa los horarios para los turnos de firma de las autoridades de tránsito.
 * (Las autoridades pueden tener turno en la mañana y/o tarde).
 *
 * @author maria.rodriguez
 */
@Entity
public class HorarioFirma {

    @Id
    private
    UUID id;

    private String lunes = "";
    private String lunesInicioAm;
    private String lunesFinAm;
    private String lunesInicioPm;
    private String lunesFinPm;
    private String martes = "";

    //martes
    private String martesInicioAm;
    private String martesFinAm;
    private String martesInicioPm;
    private String martesFinPm;
    private String miercoles = "";

    //miércoles
    private String miercolesInicioAm;
    private String miercolesFinAm;
    private String miercolesInicioPm;
    private String miercolesFinPm;
    private String jueves = "";

    //jueves
    private String juevesInicioAm;
    private String juevesFinAm;
    private String juevesInicioPm;
    private String juevesFinPm;
    private String viernes = "";

    //viernes
    private String viernesInicioAm;
    private String viernesFinAm;
    private String viernesInicioPm;
    private String viernesFinPm;
    private String sabado = "";

    //sábado
    private String sabadoInicioAm;
    private String sabadoFinAm;
    private String sabadoInicioPm;
    private String sabadoFinPm;
    private String domingo = "";

    //domingo
    private String domingoInicioAm;
    private String domingoFinAm;
    private String domingoInicioPm;
    private String domingoFinPm;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLunes() {
        return lunes;
    }

    public void setLunes(String lunes) {
        this.lunes = lunes;
    }

    public String getLunesInicioAm() {
        return lunesInicioAm;
    }

    public void setLunesInicioAm(String lunesInicioAm) {
        this.lunesInicioAm = lunesInicioAm;
    }

    public String getLunesFinAm() {
        return lunesFinAm;
    }

    public void setLunesFinAm(String lunesFinAm) {
        this.lunesFinAm = lunesFinAm;
    }

    public String getLunesInicioPm() {
        return lunesInicioPm;
    }

    public void setLunesInicioPm(String lunesInicioPm) {
        this.lunesInicioPm = lunesInicioPm;
    }

    public String getLunesFinPm() {
        return lunesFinPm;
    }

    public void setLunesFinPm(String lunesFinPm) {
        this.lunesFinPm = lunesFinPm;
    }

    public String getMartes() {
        return martes;
    }

    public void setMartes(String martes) {
        this.martes = martes;
    }

    public String getMartesInicioAm() {
        return martesInicioAm;
    }

    public void setMartesInicioAm(String martesInicioAm) {
        this.martesInicioAm = martesInicioAm;
    }

    public String getMartesFinAm() {
        return martesFinAm;
    }

    public void setMartesFinAm(String martesFinAm) {
        this.martesFinAm = martesFinAm;
    }

    public String getMartesInicioPm() {
        return martesInicioPm;
    }

    public void setMartesInicioPm(String martesInicioPm) {
        this.martesInicioPm = martesInicioPm;
    }

    public String getMartesFinPm() {
        return martesFinPm;
    }

    public void setMartesFinPm(String martesFinPm) {
        this.martesFinPm = martesFinPm;
    }

    public String getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(String miercoles) {
        this.miercoles = miercoles;
    }

    public String getMiercolesInicioAm() {
        return miercolesInicioAm;
    }

    public void setMiercolesInicioAm(String miercolesInicioAm) {
        this.miercolesInicioAm = miercolesInicioAm;
    }

    public String getMiercolesFinAm() {
        return miercolesFinAm;
    }

    public void setMiercolesFinAm(String miercolesFinAm) {
        this.miercolesFinAm = miercolesFinAm;
    }

    public String getMiercolesInicioPm() {
        return miercolesInicioPm;
    }

    public void setMiercolesInicioPm(String miercolesInicioPm) {
        this.miercolesInicioPm = miercolesInicioPm;
    }

    public String getMiercolesFinPm() {
        return miercolesFinPm;
    }

    public void setMiercolesFinPm(String miercolesFinPm) {
        this.miercolesFinPm = miercolesFinPm;
    }

    public String getJueves() {
        return jueves;
    }

    public void setJueves(String jueves) {
        this.jueves = jueves;
    }

    public String getJuevesInicioAm() {
        return juevesInicioAm;
    }

    public void setJuevesInicioAm(String juevesInicioAm) {
        this.juevesInicioAm = juevesInicioAm;
    }

    public String getJuevesFinAm() {
        return juevesFinAm;
    }

    public void setJuevesFinAm(String juevesFinAm) {
        this.juevesFinAm = juevesFinAm;
    }

    public String getJuevesInicioPm() {
        return juevesInicioPm;
    }

    public void setJuevesInicioPm(String juevesInicioPm) {
        this.juevesInicioPm = juevesInicioPm;
    }

    public String getJuevesFinPm() {
        return juevesFinPm;
    }

    public void setJuevesFinPm(String juevesFinPm) {
        this.juevesFinPm = juevesFinPm;
    }

    public String getViernes() {
        return viernes;
    }

    public void setViernes(String viernes) {
        this.viernes = viernes;
    }

    public String getViernesInicioAm() {
        return viernesInicioAm;
    }

    public void setViernesInicioAm(String viernesInicioAm) {
        this.viernesInicioAm = viernesInicioAm;
    }

    public String getViernesFinAm() {
        return viernesFinAm;
    }

    public void setViernesFinAm(String viernesFinAm) {
        this.viernesFinAm = viernesFinAm;
    }

    public String getViernesInicioPm() {
        return viernesInicioPm;
    }

    public void setViernesInicioPm(String viernesInicioPm) {
        this.viernesInicioPm = viernesInicioPm;
    }

    public String getViernesFinPm() {
        return viernesFinPm;
    }

    public void setViernesFinPm(String viernesFinPm) {
        this.viernesFinPm = viernesFinPm;
    }

    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    public String getSabadoInicioAm() {
        return sabadoInicioAm;
    }

    public void setSabadoInicioAm(String sabadoInicioAm) {
        this.sabadoInicioAm = sabadoInicioAm;
    }

    public String getSabadoFinAm() {
        return sabadoFinAm;
    }

    public void setSabadoFinAm(String sabadoFinAm) {
        this.sabadoFinAm = sabadoFinAm;
    }

    public String getSabadoInicioPm() {
        return sabadoInicioPm;
    }

    public void setSabadoInicioPm(String sabadoInicioPm) {
        this.sabadoInicioPm = sabadoInicioPm;
    }

    public String getSabadoFinPm() {
        return sabadoFinPm;
    }

    public void setSabadoFinPm(String sabadoFinPm) {
        this.sabadoFinPm = sabadoFinPm;
    }

    public String getDomingo() {
        return domingo;
    }

    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }

    public String getDomingoInicioAm() {
        return domingoInicioAm;
    }

    public void setDomingoInicioAm(String domingoInicioAm) {
        this.domingoInicioAm = domingoInicioAm;
    }

    public String getDomingoFinAm() {
        return domingoFinAm;
    }

    public void setDomingoFinAm(String domingoFinAm) {
        this.domingoFinAm = domingoFinAm;
    }

    public String getDomingoInicioPm() {
        return domingoInicioPm;
    }

    public void setDomingoInicioPm(String domingoInicioPm) {
        this.domingoInicioPm = domingoInicioPm;
    }

    public String getDomingoFinPm() {
        return domingoFinPm;
    }

    public void setDomingoFinPm(String domingoFinPm) {
        this.domingoFinPm = domingoFinPm;
    }
}
