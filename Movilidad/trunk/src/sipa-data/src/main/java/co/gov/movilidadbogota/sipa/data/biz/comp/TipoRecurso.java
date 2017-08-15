package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by acpreda on 4/8/17.
 */
@Entity
public class TipoRecurso {

    @Id
    Integer id;
    @NotNull
    String nombre;

}
