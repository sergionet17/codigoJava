package co.gov.movilidadbogota.sipa.data.biz.coa;

import co.gov.movilidadbogota.sipa.data.persona.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by maria on 5/3/17.
 */
@Entity
public class DiligenciaRemate {

    @Id
    Integer id;

    @ManyToOne
    @NotNull
    Persona Deudor;


}
