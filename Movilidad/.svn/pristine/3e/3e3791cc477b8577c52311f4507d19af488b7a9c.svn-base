package co.gov.movilidadbogota.sipa.data.biz.coa;

import co.gov.movilidadbogota.sipa.data.biz.gen.EntidadBancaria;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by maria on 5/2/17.
 */
@Entity
public class BienCuenta {

    @Id
    Integer id;

    @ManyToOne
    @NotNull
    EntidadBancaria banco;

    String numero;


}
