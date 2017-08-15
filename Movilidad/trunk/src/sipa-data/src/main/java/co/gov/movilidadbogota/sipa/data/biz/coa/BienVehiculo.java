package co.gov.movilidadbogota.sipa.data.biz.coa;

import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.persona.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by maria on 5/2/17.
 */
@Entity
public class BienVehiculo {

    @Id
    Integer id;

    @NotNull
    String placa;

    @ManyToOne
    @NotNull
    Persona tercero;

    @NotNull
    BigDecimal valor;

    @ManyToOne
    Documento soporte;

    Boolean embargado;

    Boolean inmovilizado;


}
