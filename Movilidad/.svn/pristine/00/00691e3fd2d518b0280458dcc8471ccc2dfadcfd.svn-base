package co.gov.movilidadbogota.sipa.data.biz.coa;

import co.gov.movilidadbogota.sipa.data.doc.Documento;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by maria on 5/4/17.
 */
@Entity
public class AvaluoBien {

    @Id
    Integer id;

    @ManyToOne
    Bien bien;

    @NotNull
    BigDecimal valorAvaluo;

    @ManyToOne
    EstadoAvaluo estado;

    @OneToMany
    List<Documento> prueba;
}
