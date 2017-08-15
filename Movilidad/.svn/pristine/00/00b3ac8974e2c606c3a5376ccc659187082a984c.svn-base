package co.gov.movilidadbogota.sipa.data.biz.seg;

import co.gov.movilidadbogota.sipa.data.doc.Carpeta;
import co.gov.movilidadbogota.sipa.data.doc.Documento;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by acpreda on 4/16/17.
 */
@Entity
public class RecursoQueja {

    @Id
    Integer id;
    @NotNull
    Date fecha;

    @ManyToOne
    @NotNull
    Documento oficio;

    @ManyToOne
    @NotNull
    Carpeta carpetaOrigen;

    @ManyToOne
    @NotNull
    EstadoRecursoQueja estado;

    @ManyToOne
    DecisionRecursoQueja decision;

}
