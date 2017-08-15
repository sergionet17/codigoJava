package co.gov.movilidadbogota.sipa.data.biz.coa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by maria on 5/3/17.
 */
@Entity
public class TipoCarteraCoactivo {

    /*El tipo de cartera coactiva es una clasificación que usan los abogados para determinar la viabilidad de cobro.  Cartera de dificial cobro, Facil cobro, No cobrable*/

    @Id
    Integer id;

    @NotNull
    String nombre;
}
