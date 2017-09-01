package co.gov.movilidadbogota.sipa.data.fin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Es la configuración de una transacción financiera
 *
 * @author arturo.cruz
 */
@Entity
public class PlantillaTransaccion {

    @Id
    private
    UUID id;

    @NotNull
    private
    String codigoEvento;

    @NotNull
    private
    Date inicioVigencia;

    private Date finVigencia;

    @NotNull
    private String claseGeneradora;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFinVigencia() {
        return finVigencia;
    }

    public void setFinVigencia(Date finVigencia) {
        this.finVigencia = finVigencia;
    }

    public String getClaseGeneradora() {
        return claseGeneradora;
    }

    public void setClaseGeneradora(String claseGeneradora) {
        this.claseGeneradora = claseGeneradora;
    }
}
