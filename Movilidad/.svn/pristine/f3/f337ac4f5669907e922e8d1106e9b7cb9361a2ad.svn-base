package co.gov.movilidadbogota.sipa.data.biz.comp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by acpreda on 4/7/17.
 */
@Entity
public class MatrizEstadoComparendos {

    @Id
    Integer id;

    @ManyToOne
    @NotNull
    EstadoComparendo de;

    @ManyToOne
    @NotNull
    EstadoComparendo hacia;

    @NotNull
    Date incioVigencia;
    @NotNull
    Date finVigencia;

}
