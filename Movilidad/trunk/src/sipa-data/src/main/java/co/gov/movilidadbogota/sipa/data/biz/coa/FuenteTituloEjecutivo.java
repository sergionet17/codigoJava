package co.gov.movilidadbogota.sipa.data.biz.coa;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.trans.BaseTableReference;
import co.gov.movilidadbogota.sipa.trans.SipaFieldTableReference;
import co.gov.movilidadbogota.sipa.trans.SipaTableReference;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * Clase que representa una fuente de titulo ejecutivo
 *
 * @author victor.hernandez
 */
@Entity
@SipaTableReference(name = "Fuente para título ejecutivo",
        repository = "co.gov.movilidadbogota.sipa.data.biz.coa.FuenteTituloEjecutivoRepo")
public class FuenteTituloEjecutivo extends BaseTableReference {

    public static final String CONTRAVENCIONES = "1";
    public static final String SANCIONES = "2";
    public static final String OTROS_COBROS = "3";
    public static final String SUBSANACIONES = "4";
    public static final String RESOLUCIONES = "5";
    public static final String MULTAS = "6";

    public static FuenteTituloEjecutivo CONTRAVENCIONES_OBJ;
    public static FuenteTituloEjecutivo SANCIONES_OBJ;
    public static FuenteTituloEjecutivo OTROS_COBROS_OBJ;
    public static FuenteTituloEjecutivo SUBSANACIONES_OBJ;
    public static FuenteTituloEjecutivo RESOLUCIONES_OBJ;
    public static FuenteTituloEjecutivo MULTAS_OBJ;

    static {
        try {
            CONTRAVENCIONES_OBJ = new FuenteTituloEjecutivo(1,
                    "Contravenciones, títulos derivados de las multas",
                    "Comparendo",
                    UUID.fromString("ed97e074-6b7b-478a-887e-11a3e6bb6bad"),
                    Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Long.valueOf(1));
            SANCIONES_OBJ = new FuenteTituloEjecutivo(2,
                    "Sanciones de transporte público",
                    null,
                    UUID.fromString("22d6f5d2-b466-4dfd-8069-37862178773f"),
                    Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Long.valueOf(1));
            OTROS_COBROS_OBJ = new FuenteTituloEjecutivo(3,
                    "Otros cobros títulos contractuales",
                    null,
                    UUID.fromString("87383510-274c-4cb1-89f1-6123da56c2d2"),
                    Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Long.valueOf(1));
            SUBSANACIONES_OBJ = new FuenteTituloEjecutivo(4,
                    "Subsanaciones de tránsito, sanción incumplimiento",
                    null,
                    UUID.fromString("3dcbbe46-ea27-47bc-a813-24fd8a6857d5"),
                    Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Long.valueOf(1));
            RESOLUCIONES_OBJ = new FuenteTituloEjecutivo(5,
                    "Resoluciones que determinan la obligación por el cobro del servicio de patios " +
                            "y grúas que se generan desde segunda instancia",
                    null,
                    UUID.fromString("d122440d-deb6-4122-8dbf-96d8a1ef7ddc"),
                    Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Long.valueOf(1));
            MULTAS_OBJ = new FuenteTituloEjecutivo(6,
                    "Multas disciplinarias",
                    null,
                    UUID.fromString("870d9b60-2468-4e52-9f77-6d4196adc155"),
                    Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Constants.DATE_FORMAT.parse("1870-01-01"),
                    null, Long.valueOf(1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static final Collection<FuenteTituloEjecutivo> FULL_SET = Arrays.asList(CONTRAVENCIONES_OBJ,
            SANCIONES_OBJ, OTROS_COBROS_OBJ, SUBSANACIONES_OBJ, RESOLUCIONES_OBJ, MULTAS_OBJ);

    @NotNull
    @SipaFieldTableReference(title = "ID", numberColumn = 1)
    private Integer id;

    @NotNull
    @SipaFieldTableReference(title = "Nombre", numberColumn = 2)
    private String nombre;

    @SipaFieldTableReference(title = "Nombre referencia", numberColumn = 3)
    private String claseReferencia;

    @SipaFieldTableReference(title = "Externo", numberColumn = 4)
    private Boolean externo;

    public FuenteTituloEjecutivo() {
    }

    public FuenteTituloEjecutivo(Integer id, String nombre, String claseReferencia, UUID identifier, Date inicioVigencia,
                                 Date finVigencia, Date fechaCreacion, Usuario usuario, Long version) {
        super(identifier, inicioVigencia, finVigencia, fechaCreacion, usuario, version);
        this.id = id;
        this.nombre = nombre;
        this.claseReferencia = claseReferencia;
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

    public String getClaseReferencia() {
        return claseReferencia;
    }

    public void setClaseReferencia(String claseReferencia) {
        this.claseReferencia = claseReferencia;
    }

    public Boolean getExterno() {
        return externo;
    }

    public void setExterno(Boolean externo) {
        this.externo = externo;
    }
}
