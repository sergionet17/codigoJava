package co.gov.movilidadbogota.sipa.data.fin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Representa la informacion de los volantes de pago generados por el sistema
 * Created by paola.charry on 27/06/2017.
 */
@Entity
public class VolantePago {

    @Id
    private String id;

    @NotNull
    Date fechaRegistro;

    @NotNull
    private BigDecimal abono;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public BigDecimal getAbono() {
        return abono;
    }

    public void setAbono(BigDecimal abono) {
        this.abono = abono;
    }

    public VolantePago() {
        // Se asigna la fecha actual para poder usar la clase como receptor de datos de formulario
        fechaRegistro = new Date();
    }


}
