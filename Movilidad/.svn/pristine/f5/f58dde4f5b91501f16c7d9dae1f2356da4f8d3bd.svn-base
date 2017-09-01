package co.gov.movilidadbogota.sipa.data.biz.coa;

import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.gen.Zona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by maria on 5/3/17.
 */
@Entity
public class BienInmueble {

    @Id
    Integer id;

    @NotNull
    String folio;

    @NotNull
    String matriculaInmoviliaria;

    @NotNull
    @ManyToOne
    Zona zona;

    @ManyToOne
    Documento soporte;

    @NotNull
    BigDecimal valor;

}
