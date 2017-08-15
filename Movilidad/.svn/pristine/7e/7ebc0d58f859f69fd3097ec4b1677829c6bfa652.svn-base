package co.gov.movilidadbogota.sipa.data.biz.comp;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Entidad que representa los turnos de firma de las autoridades de tránsito en el sistema
 *
 * @author maria.rodriguez
 */
@Entity

public class TurnoFirma {

    @Id
    public
    UUID id;
    @NotNull
    public Date desde;
    public Date hasta;

    @NotNull
    @ManyToOne
    public Usuario autoridad;

    @ManyToOne
    private Usuario suplenteOficial;
    private Boolean activo;
    private Boolean cerrado;


    public TurnoFirma() {
    }

    public TurnoFirma(Usuario u) {
        this.autoridad = u;
        this.id = UUID.randomUUID();
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Usuario getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(Usuario autoridad) {
        this.autoridad = autoridad;
    }

    public Usuario getSuplenteOficial() {
        return suplenteOficial;
    }

    public void setSuplenteOficial(Usuario suplenteOficial) {
        this.suplenteOficial = suplenteOficial;
    }

    public Boolean getActivo() {
        if (desde != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String today = dateFormat.format(date);
            String vigencia = dateFormat.format(desde);
            try {
                if (dateFormat.parse(today).after(dateFormat.parse(vigencia)) || dateFormat.parse(vigencia).equals(dateFormat.parse(today)))
                    return true;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getCerrado() {
        if (desde != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String today = dateFormat.format(date);
            String vigencia = dateFormat.format(hasta);
            try {
                if (dateFormat.parse(today).after(dateFormat.parse(vigencia)) || dateFormat.parse(vigencia).equals(dateFormat.parse(today)))
                    return true;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }
}
