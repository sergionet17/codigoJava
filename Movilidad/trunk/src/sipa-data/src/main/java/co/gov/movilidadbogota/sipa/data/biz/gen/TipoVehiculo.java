package co.gov.movilidadbogota.sipa.data.biz.gen;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Representa el tipo de vehículo
 *
 * @author arturo.cruz
 */
@Entity
public class TipoVehiculo {

    public static final TipoVehiculo BICICLETA_TRICICLO = new TipoVehiculo(1, "Bicicleta ó Triciclo");
    public static final TipoVehiculo TRACCION_ANIMAL = new TipoVehiculo(2, "Traccion Animal");
    public static final TipoVehiculo AUTOMOVIL = new TipoVehiculo(3, "Automovil");
    public static final TipoVehiculo CAMPERO = new TipoVehiculo(4, "Campero");
    public static final TipoVehiculo CAMIONETA = new TipoVehiculo(5, "Camioneta");
    public static final TipoVehiculo MICROBUS = new TipoVehiculo(6, "Microbus");
    public static final TipoVehiculo BUSETA = new TipoVehiculo(7, "Buseta");
    public static final TipoVehiculo BUS = new TipoVehiculo(8, "Bus");
    public static final TipoVehiculo BUS_ARTICULADO = new TipoVehiculo(9, "Bus Articulado");
    public static final TipoVehiculo CAMION = new TipoVehiculo(10, "Camion");
    public static final TipoVehiculo VOLQUETA = new TipoVehiculo(11, "Volqueta");
    public static final TipoVehiculo TRACTOCAMION = new TipoVehiculo(12, "Tractocamion");
    public static final TipoVehiculo MOTOCICLO = new TipoVehiculo(13, "Motociclo");
    public static final TipoVehiculo MOTOTRICICLO = new TipoVehiculo(14, "MotoTriciclo");
    public static final TipoVehiculo MOTOCARRO = new TipoVehiculo(15, "Motocarro");
    public static final TipoVehiculo MOTOCICLETA = new TipoVehiculo(16, "Motocicleta");
    public static final TipoVehiculo CUATRIMOTO = new TipoVehiculo(17, "Cuatrimoto");
    public static final TipoVehiculo REMOLQUE_SEMIREM = new TipoVehiculo(18, "Remolque / Semirem");

    public static final List<TipoVehiculo> FULL_SET = Arrays.asList(
            TipoVehiculo.BICICLETA_TRICICLO,
            TipoVehiculo.TRACCION_ANIMAL,
            TipoVehiculo.AUTOMOVIL,
            TipoVehiculo.CAMPERO,
            TipoVehiculo.CAMIONETA,
            TipoVehiculo.MICROBUS,
            TipoVehiculo.BUSETA,
            TipoVehiculo.BUS,
            TipoVehiculo.BUS_ARTICULADO,
            TipoVehiculo.CAMION,
            TipoVehiculo.VOLQUETA,
            TipoVehiculo.TRACTOCAMION,
            TipoVehiculo.MOTOCICLO,
            TipoVehiculo.MOTOTRICICLO,
            TipoVehiculo.MOTOCARRO,
            TipoVehiculo.MOTOCICLETA,
            TipoVehiculo.CUATRIMOTO,
            TipoVehiculo.REMOLQUE_SEMIREM
    );

    @Id
    Integer id;

    @NotNull
    String nombre;

    public TipoVehiculo() {
    }

    public TipoVehiculo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
