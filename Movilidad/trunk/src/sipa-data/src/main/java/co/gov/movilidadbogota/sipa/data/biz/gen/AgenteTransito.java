package co.gov.movilidadbogota.sipa.data.biz.gen;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by acpreda on 4/7/17.
 */
@Entity
public class AgenteTransito {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    Integer id;

    @NotNull
    String primerNombre;
    String segundoNombre;
    @NotNull
    String primerApellido;
    String segundoApellido;

    @NotNull
    String placa;

    @ManyToOne
    @NotNull
    EntidadAgenteTransito entidadAgenteTransito;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public EntidadAgenteTransito getEntidadAgenteTransito() {
        return entidadAgenteTransito;
    }

    public void setEntidadAgenteTransito(EntidadAgenteTransito entidadAgenteTransito) {
        this.entidadAgenteTransito = entidadAgenteTransito;
    }
}
