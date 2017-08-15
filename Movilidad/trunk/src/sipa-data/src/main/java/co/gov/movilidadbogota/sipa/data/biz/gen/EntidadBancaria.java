package co.gov.movilidadbogota.sipa.data.biz.gen;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by maria on 5/2/17.
 */
@Entity
public class EntidadBancaria {
    @Id
    Integer id;
    @NotNull
    String codigo;
    @NotNull
    String nombre;
}
