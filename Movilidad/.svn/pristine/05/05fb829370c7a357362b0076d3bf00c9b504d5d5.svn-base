package co.gov.movilidadbogota.sipa.data.biz.coa;

import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.persona.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Bien {

    @Id
    Integer id;

    @ManyToOne
    @NotNull
    Persona propietario;

    @ManyToOne
    BienCuenta cuenta;

    @ManyToOne
    BienVehiculo vehiculo;

    @ManyToOne
    BienInmueble inmueble;

    @ManyToOne
    BienSueldo sueldo;

    @ManyToOne
    Documento soporte;

    BigDecimal valor;

}
