package co.gov.movilidadbogota.sipa.data.gen;

import co.gov.movilidadbogota.sipa.data.util.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.sound.midi.SysexMessage;
import java.time.Duration;
import java.util.*;

/**
 * Created by acpreda on 7/18/17.
 */
@Entity
public class Alarma {

    public static final Alarma TAREAS_EN_PROGRESO = new Alarma(
            "TAREAS_EN_PROGRESO",
            "Alarma para la notificación de vencimiento de tareas que se en cuentra en curso",
            Arrays.asList( AlarmaRango.RANGO1, AlarmaRango.RANGO2,AlarmaRango.RANGO3),
            "alarmaStrategyImpl"
    );

    public static final Collection<Alarma> FULL_SET = Arrays.asList(
            TAREAS_EN_PROGRESO
    );


    @Id
    private
    String clave;

    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alarma")
    private
    List<AlarmaRango> rangos;

    private String implementacion;

    public Alarma() {
    }

    public Alarma(String clave, String descripcion, List<AlarmaRango> rangos, String implementacion) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.rangos = rangos;
        this.implementacion = implementacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<AlarmaRango> getRangos() {
        return rangos;
    }

    public void setRangos(List<AlarmaRango> rangos) {
        this.rangos = rangos;
    }

    /*
    public AlarmaRango getRango(Date date) {
        Calendar now, due;
        (now = Calendar.getInstance()).setTime(new Date());
        (due = Calendar.getInstance()).setTime(date);
        Collections.sort(rangos, (o1, o2) -> o1.getDias().compareTo(o2.getDias()));
        AlarmaRango last = null;
        for (AlarmaRango x : rangos) {
            long diff = due.getTimeInMillis() - now.getTimeInMillis();
            long days = (diff/(24 * 60 * 60 * 1000)); // dia calendario
            if(x.getTipoDia().equals(2)){ // si es dia hábil
                long diasCal = BaseUtils.laboralesToCalendario(date,x.getDias(),festivoRepo);
                days = (diasCal/(24 * 60 * 60 * 1000));
            }
            if (days > x.getDias())
                return last;
            last = x;
        }
        return last;
    }*/

    public String getImplementacion() { return implementacion; }

    public void setImplementacion(String implementacion) {
        this.implementacion = implementacion;
    }
}
