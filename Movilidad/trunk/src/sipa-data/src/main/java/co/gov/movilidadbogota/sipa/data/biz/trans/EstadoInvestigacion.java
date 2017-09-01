package co.gov.movilidadbogota.sipa.data.biz.trans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by acpreda on 4/16/17.
 */
@Entity
public class EstadoInvestigacion {

    @Id
    Integer id;
    @NotNull
    String nombre;

}
