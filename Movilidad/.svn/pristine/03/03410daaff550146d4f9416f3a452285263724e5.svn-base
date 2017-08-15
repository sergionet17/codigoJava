package co.gov.movilidadbogota.sipa.data.biz.coa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class EmbargoBien {

    @Id
    Integer id;

    @ManyToOne
    Bien bien;

    @NotNull
    BigDecimal valorEmbargo;

    @ManyToOne
    EstadoEmbargo estado;

}
