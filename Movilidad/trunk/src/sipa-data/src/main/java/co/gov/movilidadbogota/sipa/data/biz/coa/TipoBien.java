package co.gov.movilidadbogota.sipa.data.biz.coa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by maria on 4/20/17.
 */
@Entity
public class TipoBien {

    @Id
    Integer id;

    @NotNull
    String nombre;


}
