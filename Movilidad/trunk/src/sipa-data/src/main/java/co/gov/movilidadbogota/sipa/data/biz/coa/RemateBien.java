package co.gov.movilidadbogota.sipa.data.biz.coa;

import co.gov.movilidadbogota.sipa.data.doc.Documento;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by maria on 5/4/17.
 */
public class RemateBien {

    @Id
    Integer id;

    @ManyToOne
    Bien bien;

    @NotNull
    BigDecimal valorRemate;

    @ManyToOne
    EstadoRemate estado;

    @OneToMany
    Documento prueba;
}
