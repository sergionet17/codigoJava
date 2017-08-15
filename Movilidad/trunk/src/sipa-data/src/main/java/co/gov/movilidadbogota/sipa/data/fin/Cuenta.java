package co.gov.movilidadbogota.sipa.data.fin;

import org.springframework.util.DigestUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Representa una cuenta contable en la que se realizan los registros financieros correspondientes
 *
 * @author arturo.cruz
 */
@Entity
public class Cuenta {

    public static final Cuenta ORD_DEU_COMPARENDOS = new Cuenta(
            UUID.fromString("eae0e5ba-601f-4852-89e6-9bf95e188fc9"),
            "Deuda de comparendos",
            NaturalezaCuenta.ORDEN_DEUDORAS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("eae0e5ba-601f-4852-89e6-9bf95e188fc9".getBytes())
    );

    public static final Cuenta ORD_DEU_COMPARENDOS_CONTRA = new Cuenta(
            UUID.fromString("606589cb-65af-4361-8cbf-e17368197984"),
            "Deuda de comparendos en contra",
            NaturalezaCuenta.ORDEN_DEUDORAS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("606589cb-65af-4361-8cbf-e17368197984".getBytes())
    );
    public static final Cuenta ORD_DEU_SUBSANACIONES = new Cuenta(
            UUID.fromString("fb3c5007-ed2d-46ea-91fd-623690a20774"),
            "Deuda de subsanaciones",
            NaturalezaCuenta.ORDEN_DEUDORAS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("fb3c5007-ed2d-46ea-91fd-623690a20774".getBytes())
    );

    public static final Cuenta ORD_DEU_SUBSANACIONES_CONTRA = new Cuenta(
            UUID.fromString("0f10949e-c5f9-47ca-bca5-46a39245cd94"),
            "Deuda de subsanaciones en contra",
            NaturalezaCuenta.ORDEN_DEUDORAS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("0f10949e-c5f9-47ca-bca5-46a39245cd94".getBytes())
    );
    public static final Cuenta ACT_COMPARENDOS = new Cuenta(
            UUID.fromString("83fe1dcf-5ffb-40bc-90f7-871ff62c6235"),
            "Activos Comparendos",
            NaturalezaCuenta.ACTIVOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("83fe1dcf-5ffb-40bc-90f7-871ff62c6235".getBytes())
    );
    public static final Cuenta ACT_HACIENDA = new Cuenta(
            UUID.fromString("6a2a6286-a1a4-410b-96d8-c2fe6b309a39"),
            "Activos Hacienda",
            NaturalezaCuenta.ACTIVOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("6a2a6286-a1a4-410b-96d8-c2fe6b309a39".getBytes())
    );
    public static final Cuenta ING_COMPARENDOS = new Cuenta(
            UUID.fromString("56fe1d9e-7cdc-42de-8e31-e3f87e5d7c5a"),
            "Ingresos Comparendos",
            NaturalezaCuenta.INGRESOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("56fe1d9e-7cdc-42de-8e31-e3f87e5d7c5a".getBytes())
    );
    public static final Cuenta PAS_CUENTAS_POR_PAGAR = new Cuenta(
            UUID.fromString("4485e999-fb50-4ed7-aa6e-95f59cfb5e49"),
            "Cuentas por pagar",
            NaturalezaCuenta.PASIVOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("4485e999-fb50-4ed7-aa6e-95f59cfb5e49".getBytes())
    );
    public static final Cuenta ACT_DESCUENTO_COMPARENDOS = new Cuenta(
            UUID.fromString("8d213564-0017-4d14-90d6-5a396e82d050"),
            "Descuento comparendos",
            NaturalezaCuenta.ACTIVOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("8d213564-0017-4d14-90d6-5a396e82d050".getBytes())
    );
    public static final Cuenta ACT_SUBSANACIONES = new Cuenta(
            UUID.fromString("073dc94e-1b86-430f-8435-f10c28897401"),
            "Subsanaciones",
            NaturalezaCuenta.ACTIVOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("073dc94e-1b86-430f-8435-f10c28897401".getBytes())
    );
    public static final Cuenta ING_SUBSANACIONES = new Cuenta(
            UUID.fromString("e0a732b0-bc21-4dd5-b82c-b42bf6f0b7d8"),
            "Subsanaciones",
            NaturalezaCuenta.INGRESOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("e0a732b0-bc21-4dd5-b82c-b42bf6f0b7d8".getBytes())
    );
    public static final Cuenta ORD_DEU_PATIOS_GRUAS = new Cuenta(
            UUID.fromString("407e8971-144e-4e48-9d42-547850c2e469"),
            "Deuda patios y gruas",
            NaturalezaCuenta.ORDEN_DEUDORAS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("407e8971-144e-4e48-9d42-547850c2e469".getBytes())
    );
    public static final Cuenta ORD_DEU_PATIOS_GRUAS_CONTRA = new Cuenta(
            UUID.fromString("9d9a0740-e9e2-46be-bc8e-1d776e5cf70b"),
            "Deuda patios y gruas en contra",
            NaturalezaCuenta.ORDEN_DEUDORAS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("9d9a0740-e9e2-46be-bc8e-1d776e5cf70b".getBytes())
    );
    public static final Cuenta ING_PATIOS_GRUAS = new Cuenta(
            UUID.fromString("3e264d8b-3ae3-4abb-b964-bd4ef3f22343"),
            "Ingresos patios y gruas",
            NaturalezaCuenta.INGRESOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("3e264d8b-3ae3-4abb-b964-bd4ef3f22343".getBytes())
    );
    public static final Cuenta ACT_PATIOS_GRUAS = new Cuenta(
            UUID.fromString("55d256bd-9950-41cc-8a9b-f1120be9e781"),
            "Activos patios y gruas",
            NaturalezaCuenta.ACTIVOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("55d256bd-9950-41cc-8a9b-f1120be9e781".getBytes())
    );
    public static final Cuenta ORD_DEU_TRANSPORTE_PUBLICO = new Cuenta(
            UUID.fromString("7d2dbd50-4220-424b-adba-22dca17a9db5"),
            "Deuda transporte publico",
            NaturalezaCuenta.ORDEN_DEUDORAS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("7d2dbd50-4220-424b-adba-22dca17a9db5".getBytes())
    );
    public static final Cuenta ORD_DEU_TRANSPORTE_PUBLICO_CONTRA = new Cuenta(
            UUID.fromString("8879ccec-ac17-4401-8a94-d6d6b4fb3a30"),
            "Deuda tranporte publico en contra",
            NaturalezaCuenta.ORDEN_DEUDORAS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("8879ccec-ac17-4401-8a94-d6d6b4fb3a30".getBytes())
    );
    public static final Cuenta ING_TRANSPORTE_PUBLICO = new Cuenta(
            UUID.fromString("39610651-98f0-48dc-b4a8-3ff466928502"),
            "Ingresos transporte publico",
            NaturalezaCuenta.INGRESOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("39610651-98f0-48dc-b4a8-3ff466928502".getBytes())
    );
    public static final Cuenta ACT_TRANSPORTE_PUBLICO = new Cuenta(
            UUID.fromString("4bda25af-ee5a-49e3-936e-44e898990a1b"),
            "Activos transporte publico",
            NaturalezaCuenta.ACTIVOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("4bda25af-ee5a-49e3-936e-44e898990a1b".getBytes())
    );
    public static final Cuenta ING_INTERESES = new Cuenta(
            UUID.fromString("d91acb5c-03b1-4ee7-a179-17b9b99347f2"),
            "Ingresos intereses",
            NaturalezaCuenta.INGRESOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("d91acb5c-03b1-4ee7-a179-17b9b99347f2".getBytes())
    );
    public static final Cuenta ACT_INTERESES = new Cuenta(
            UUID.fromString("b1555720-6c7e-4baa-bf79-553771ad871e"),
            "Activos intereses",
            NaturalezaCuenta.ACTIVOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("b1555720-6c7e-4baa-bf79-553771ad871e".getBytes())
    );
    public static final Cuenta PAT_COMPARENDOS = new Cuenta(
            UUID.fromString("bb395d81-a9ee-4523-b106-ddb5f0776bb9"),
            "Patrimonio comparendos",
            NaturalezaCuenta.PATRIMONIO,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("bb395d81-a9ee-4523-b106-ddb5f0776bb9".getBytes())
    );
    public static final Cuenta PAT_SUBSANACIONES = new Cuenta(
            UUID.fromString("6ff080d0-9b9a-4d3d-906f-1b3b3205cecb"),
            "Patrimonio subsanaciones",
            NaturalezaCuenta.PATRIMONIO,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("6ff080d0-9b9a-4d3d-906f-1b3b3205cecb".getBytes())
    );
    public static final Cuenta PAT_TRANSPORTE_PUBLICO = new Cuenta(
            UUID.fromString("a13f5684-a568-41f9-b731-663c76c0f506"),
            "Patrimonio transporte publico",
            NaturalezaCuenta.PATRIMONIO,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("a13f5684-a568-41f9-b731-663c76c0f506".getBytes())
    );
    public static final Cuenta PAT_PATIOS_GRUAS = new Cuenta(
            UUID.fromString("8c1278ae-0fbf-43af-a0a5-6850c72768a4"),
            "Patrimonio patios y gruas",
            NaturalezaCuenta.PATRIMONIO,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("8c1278ae-0fbf-43af-a0a5-6850c72768a4".getBytes())
    );
    public static final Cuenta ING_COSTAS = new Cuenta(
            UUID.fromString("f478c281-897a-4a0d-95fd-980fcc82e3f5"),
            "Ingresos costas",
            NaturalezaCuenta.INGRESOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("f478c281-897a-4a0d-95fd-980fcc82e3f5".getBytes())
    );
    public static final Cuenta ACT_COSTAS = new Cuenta(
            UUID.fromString("746ccc09-711d-40e2-90b6-3c88287005c4"),
            "Activos costas",
            NaturalezaCuenta.ACTIVOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("746ccc09-711d-40e2-90b6-3c88287005c4".getBytes())
    );
    public static final Cuenta ORD_DEU_POSIBLE_RECAUDO = new Cuenta(
            UUID.fromString("f46017fe-83ff-4084-aa90-cf3b0dbd5018"),
            "Deuda posible recaudo",
            NaturalezaCuenta.ORDEN_DEUDORAS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("f46017fe-83ff-4084-aa90-cf3b0dbd5018".getBytes())
    );
    public static final Cuenta ORD_DEU_POSIBLE_RECAUDO_CONTRA = new Cuenta(
            UUID.fromString("512604e5-2332-488e-a699-6866a986e91e"),
            "Deuda posible recaudo en contra",
            NaturalezaCuenta.ORDEN_DEUDORAS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("512604e5-2332-488e-a699-6866a986e91e".getBytes())
    );
    public static final Cuenta ORD_DEU_RECAUDO = new Cuenta(
            UUID.fromString("ccd3b6de-2d19-4e62-adab-011ff18f959e"),
            "Deuda recaudo",
            NaturalezaCuenta.ORDEN_DEUDORAS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("ccd3b6de-2d19-4e62-adab-011ff18f959e".getBytes())
    );
    public static final Cuenta ORD_DEU_RECAUDO_CONTRA = new Cuenta(
            UUID.fromString("35a82f25-825c-4317-a5b3-d80c03b8fa1f"),
            "Deuda recaudo en contra",
            NaturalezaCuenta.ORDEN_DEUDORAS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("35a82f25-825c-4317-a5b3-d80c03b8fa1f".getBytes())
    );
    public static final Cuenta PAS_DEPOSITO_JUDICIAL = new Cuenta(
            UUID.fromString("526aff59-ab3d-4439-a148-9b06110ed3d9"),
            "Deposito judicial",
            NaturalezaCuenta.PASIVOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("526aff59-ab3d-4439-a148-9b06110ed3d9".getBytes())
    );
    public static final Cuenta ING_SANCIONES_DISCIPLINARIAS = new Cuenta(
            UUID.fromString("2451c1c0-115b-43f8-9617-d81048c4c488"),
            "Ingresos sanciones disciplinarias",
            NaturalezaCuenta.INGRESOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("2451c1c0-115b-43f8-9617-d81048c4c488".getBytes())
    );
    public static final Cuenta ACT_SANCIONES_DISCIPLINARIAS = new Cuenta(
            UUID.fromString("06a4eed3-ee20-43af-806c-7f6d3f241ad6"),
            "Activos sanciones disciplinarias",
            NaturalezaCuenta.ACTIVOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("06a4eed3-ee20-43af-806c-7f6d3f241ad64".getBytes())
    );
    public static final Cuenta ING_ENTIDADES_EXTERNAS = new Cuenta(
            UUID.fromString("88e51365-63f9-4dcc-b0c8-14811e887ba6"),
            "Ingresos entidades externas",
            NaturalezaCuenta.INGRESOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("88e51365-63f9-4dcc-b0c8-14811e887ba6".getBytes())
    );
    public static final Cuenta ACT_ENTIDADES_EXTERNAS = new Cuenta(
            UUID.fromString("e5ed85b2-95a9-475a-8971-e15799e659cd"),
            "Activos entidades externas",
            NaturalezaCuenta.ACTIVOS,
            Boolean.TRUE,
            DigestUtils.md5DigestAsHex("e5ed85b2-95a9-475a-8971-e15799e659cd".getBytes())
    );
    @Id
    private UUID id;
    @NotNull
    private String nombre;
    private String descripcion;

    @ManyToOne
    @NotNull
    private NaturalezaCuenta naturalezaCuenta;

    @NotNull
    private Boolean obligaReferencia;

    @NotNull
    private String lastHash;

    public Cuenta() {
    }

    public Cuenta(UUID id, String nombre, NaturalezaCuenta naturalezaCuenta, Boolean obligaReferencia, String lastHash) {
        this.setId(id);
        this.setNombre(nombre);
        this.setNaturalezaCuenta(naturalezaCuenta);
        this.setObligaReferencia(obligaReferencia);
        this.setLastHash(lastHash);
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public NaturalezaCuenta getNaturalezaCuenta() {
        return naturalezaCuenta;
    }

    private void setNaturalezaCuenta(NaturalezaCuenta naturalezaCuenta) {
        this.naturalezaCuenta = naturalezaCuenta;
    }

    public Boolean getObligaReferencia() {
        return obligaReferencia;
    }

    private void setObligaReferencia(Boolean obligaReferencia) {
        this.obligaReferencia = obligaReferencia;
    }

    public String getLastHash() {
        return lastHash;
    }

    public void setLastHash(String lastHash) {
        this.lastHash = lastHash;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", naturalezaCuenta=" + naturalezaCuenta +
                ", obligaReferencia=" + obligaReferencia +
                ", lastHash='" + lastHash + '\'' +
                '}';
    }
}
