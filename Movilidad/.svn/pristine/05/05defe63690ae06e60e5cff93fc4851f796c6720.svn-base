package co.gov.movilidadbogota.sipa.data.biz.trans;

import co.gov.movilidadbogota.sipa.data.doc.Carpeta;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by acpreda on 4/16/17.
 */
@Entity
public class PreliminaresInvestigacion {

    @Id
    Integer id;
    @NotNull
    Date fecha;

    @ManyToOne
    @NotNull
    Carpeta carpeta;

    @ManyToOne
    Investigacion investigacion;

    @ManyToOne
    @NotNull
    EstadoPreliminaresInvestigacion estado;

}
