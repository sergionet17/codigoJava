package co.gov.movilidadbogota.sipa.data.biz.coa;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by maria on 5/4/17.
 */
@Entity
public class EstadoEmbargo {

    @Id
    Integer id;

    String nombre;

}
