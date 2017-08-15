package co.gov.movilidadbogota.sipa.data.biz.comp;

import co.gov.movilidadbogota.sipa.data.doc.Documento;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by acpreda on 4/8/17.
 */
@Entity
public class RecursoComparendo {

    @Id
    Integer id;

    @ManyToOne
    @NotNull
    Comparendo comparendo;
    @NotNull
    Date fecha;

    @ManyToOne
    @NotNull
    Documento documento;

    @ManyToOne
    @NotNull
    TipoRecurso tipo;

}
