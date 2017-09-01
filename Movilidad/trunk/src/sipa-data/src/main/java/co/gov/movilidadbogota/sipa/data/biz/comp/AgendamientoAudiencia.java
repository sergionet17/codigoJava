package co.gov.movilidadbogota.sipa.data.biz.comp;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Se registra la parametrización de los periodos de tiempo dentro de los cuales se pueden hacer audiencias de continuación.
 * Created by paola.charry on 29/06/2017.
 */
@Entity
public class AgendamientoAudiencia {

    @ManyToOne
    @NotNull
    TipoAudienciaComparendo tipo;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private
    Integer id;
    @NotNull
    private
    Integer diaInicial;

    @NotNull
    private
    Integer diaFinal;

    private
    Date fechaRegistro;

    private
    Date fechaCierre;

    public AgendamientoAudiencia() {
        this.fechaRegistro = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoAudienciaComparendo getTipo() {
        return tipo;
    }

    public void setTipo(TipoAudienciaComparendo tipo) {
        this.tipo = tipo;
    }

    public Integer getDiaInicial() {
        return diaInicial;
    }

    public void setDiaInicial(Integer diaInicial) {
        this.diaInicial = diaInicial;
    }

    public Integer getDiaFinal() {
        return diaFinal;
    }

    public void setDiaFinal(Integer diaFinal) {
        this.diaFinal = diaFinal;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
}
