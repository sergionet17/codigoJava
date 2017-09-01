package co.gov.movilidadbogota.sipa.data.fin;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Define la naturaleza de la cuenta principalmente cómo se comportan los débitos y los créditos.
 * Corresponden al primer nivel del PUC.
 *
 * @author arturo.cruz
 */
@Entity
public class NaturalezaCuenta {

    private static final BigDecimal DEBITO = BigDecimal.ONE;
    private static final BigDecimal CREDITO = BigDecimal.ONE.negate();

    public static final NaturalezaCuenta ACTIVOS = new NaturalezaCuenta(
            UUID.fromString("98b9df95-0c85-4462-9b33-f0295c7c5c32"),
            "Cuentas de activos",
            DEBITO);

    public static final NaturalezaCuenta PASIVOS = new NaturalezaCuenta(
            UUID.fromString("628d6f23-d1c0-4f4f-a294-7e0b6dc1cf36"),
            "Cuentas de pasivos",
            CREDITO);

    public static final NaturalezaCuenta PATRIMONIO = new NaturalezaCuenta(
            UUID.fromString("a6bf83e4-e832-4b01-8bcd-c524418e808f"),
            "Cuentas de patrimonio",
            CREDITO);

    public static final NaturalezaCuenta INGRESOS = new NaturalezaCuenta(
            UUID.fromString("b1598854-fca3-47d2-88c7-df2f939c306a"),
            "Cuentas de ingresos",
            CREDITO);

    public static final NaturalezaCuenta COSTOS = new NaturalezaCuenta(
            UUID.fromString("ca0a8935-9d2d-4d7d-a10e-c6f15b104591"),
            "Cuentas de costos",
            DEBITO);

    public static final NaturalezaCuenta GASTOS = new NaturalezaCuenta(
            UUID.fromString("ecaed08c-4263-4db5-9427-93b2da36af8f"),
            "Cuentas de gastos",
            DEBITO);

    public static final NaturalezaCuenta ORDEN_DEUDORAS = new NaturalezaCuenta(
            UUID.fromString("1e6c509b-fa28-4398-97a8-678dd0c52f82"),
            "Cuentas de orden deudoras",
            DEBITO);

    public static final NaturalezaCuenta ORDEN_ACREEDORAS = new NaturalezaCuenta(
            UUID.fromString("5dd2b0da-4fa8-4426-a478-f47d7483c07f"),
            "Cuentas de orden acreedoras",
            CREDITO);

    @Id
    private UUID id;
    @NotNull
    private String nombre;
    @NotNull
    private BigDecimal factor;

    public NaturalezaCuenta() {
    }

    public NaturalezaCuenta(UUID id, String nombre, BigDecimal factor) {
        this.setId(id);
        this.setNombre(nombre);
        this.setFactor(factor);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    @Override
    public String toString() {
        return "NaturalezaCuenta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", factor=" + factor +
                '}';
    }
}
