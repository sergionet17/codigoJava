package co.gov.movilidadbogota.sipa.data.biz.comp;

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
public class SuspensionLicencia {

    @Id
    Integer id;

    @ManyToOne
    @NotNull
    Carpeta expediente;

    @ManyToOne
    @NotNull
    Licencia licencia;

    @NotNull
    Date fecha;

    @ManyToOne
    @NotNull
    EstadoSuspensionLicencia estado;

}
