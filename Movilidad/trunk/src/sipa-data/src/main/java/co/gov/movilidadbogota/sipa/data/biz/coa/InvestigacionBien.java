package co.gov.movilidadbogota.sipa.data.biz.coa;

import co.gov.movilidadbogota.sipa.data.doc.Carpeta;
import co.gov.movilidadbogota.sipa.data.doc.Documento;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by maria on 4/20/17.
 */
@Entity
public class InvestigacionBien {

    @Id
    Integer id;

    @ManyToOne
    @NotNull
    TituloEjecutivo titulo;

    @OneToMany
    List<BienInmueble> inmueble;

    @OneToMany
    List<BienCuenta> cuenta;

    @OneToMany
    List<BienSueldo> sueldo;

    @OneToMany
    List<BienVehiculo> vehiculo;

    @ManyToOne
    @NotNull
    Carpeta carpeta;

    @OneToMany
    List<Documento> soporte;

    @NotNull
    @ManyToOne
    EstadoInvestigacionBien estado;

    @ManyToOne
    ResultadoInvestigacionBien resultado;


}
