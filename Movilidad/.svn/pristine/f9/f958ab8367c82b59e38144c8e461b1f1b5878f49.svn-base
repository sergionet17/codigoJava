package co.gov.movilidadbogota.sipa.data.biz.gen;

import co.gov.movilidadbogota.sipa.data.loc.Direccion;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by acpreda on 4/7/17.
 */
@Entity
public class Patio {

    @Id
    Integer id;
    @NotNull
    String numero;

    @ManyToOne
    @NotNull
    Direccion direccion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
