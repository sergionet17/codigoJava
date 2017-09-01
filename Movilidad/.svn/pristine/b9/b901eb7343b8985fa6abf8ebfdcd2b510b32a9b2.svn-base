package co.gov.movilidadbogota.sipa.data.doc;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Clase que representa el estado del titulo ejecutivo
 *
 * @author lorenzo.pinango
 */
@Entity
public class TipoDocumental {

    public static final String NO_SERIE = "0";

    public static final TipoDocumental INDEFINIDO = new TipoDocumental(UUID.fromString("e2b5e9f8-1764-4baa-901e-8d6413ccdaea"), "INDEFINIDO-NO_DEBE_PASAR", "cmis:document", NO_SERIE);
    public static final TipoDocumental CONSTANCIA_EJECUTORIA = new TipoDocumental(UUID.fromString("187665a5-e498-4c7a-940a-e01a3069f635"), "Constancia ejecutoria", "cmis:document", NO_SERIE);
    public static final TipoDocumental RESOLUCION_FALLO = new TipoDocumental(UUID.fromString("74badb8e-fe92-4b9c-a991-e9748826d2a8"), "Resolución de fallo", "cmis:document", NO_SERIE);
    public static final TipoDocumental COMPARENDO = new TipoDocumental(UUID.fromString("50b74cd4-9941-4db7-8a5c-6aca6a1acbe6"), "Comparendo", "cmis:document", NO_SERIE);
    public static final TipoDocumental CERTIFICADO_ASISTENCIA_CURSO = new TipoDocumental(UUID.fromString("f68b5bc5-bb96-4665-8b09-ee9c2328d636"), "Certificado de asistencia a curso", "cmis:document", NO_SERIE);
    public static final TipoDocumental AUTORIZACION_CREACION_USUARIO = new TipoDocumental(UUID.fromString("19b61bc8-01e3-4791-b9cd-98a222a6e051"), "Autorización de creación de usuario", "cmis:document", NO_SERIE);
    public static final TipoDocumental EVIDENCIA_ANULACION_RANGOS = new TipoDocumental(UUID.fromString("69099597-b454-44ef-9e11-8f3729c178d5"), "Evidencia de anulación de rangos", "cmis:document", NO_SERIE);
    public static final TipoDocumental EVIDENCIA_REGISTRO_RANGOS = new TipoDocumental(UUID.fromString("a4e1141f-b933-4ff9-8ab8-1181603a23ce"), "Evidencia regiatro de rangos", "cmis:document", NO_SERIE);
    public static final TipoDocumental AUDIENCIA = new TipoDocumental(UUID.fromString("47852d72-bc56-4480-bdfd-c61624571937"), "Audiencia", "cmis:document", NO_SERIE);
    public static final TipoDocumental EXPEDIENTE_FORMATO_COMPARENDO = new TipoDocumental(UUID.fromString("ab0e9b8c-f4c3-45da-833b-10a2c259a3a5"), "Expediente formato de comparendo", "cmis:folder", NO_SERIE);
    public static final TipoDocumental TITULO_EJECUTIVO = new TipoDocumental(UUID.fromString("90cd3f4a-8e97-4f36-8f86-518b67400aa9"), "Título ejecutivo folder", "cmis:folder", NO_SERIE);
    public static final TipoDocumental TITULO_EJECUTIVO_DOC = new TipoDocumental(UUID.fromString("54708e06-6b4b-4c92-b844-a463a4325dba"), "Título ejecutivo", "cmis:document", NO_SERIE);
    public static final TipoDocumental RESOLUCION_DECLARATORIA_REINCIDENCIA = new TipoDocumental(UUID.fromString("d1fd93fa-8988-4d8f-b48c-d54151c7873a"), "Resolución declaratoria reincidencia", "cmis:document", NO_SERIE);
    public static final TipoDocumental VOLANTE_PAGO = new TipoDocumental(UUID.fromString("3f2bd225-0dac-44a4-96b4-849477d27e4f"), "Volante de pago", "cmis:document", NO_SERIE);
    public static final TipoDocumental PLANTILLA = new TipoDocumental(UUID.fromString("dd2e4024-507f-4c36-92f1-cbf363dad289"), "Plantilla", "cmis:document", NO_SERIE);
    public static final TipoDocumental RESOLUCION_AUTOMATICA_ANULACION = new TipoDocumental(UUID.fromString("abf983eb-b09a-4bac-9d1b-4a898587717b"), "Resolución automática de anulación de rango de numeración", "cmis:document", NO_SERIE);
    public static final TipoDocumental INFRACCION_TRANSPORTE_PUBLICO = new TipoDocumental(UUID.fromString("e2b5e9f8-1764-4baa-901e-8d6413bbcbeb"), "Infracción transporte público", "cmis:document", NO_SERIE);

    public static final List<TipoDocumental> FULL_LIST = Arrays.asList(
            INDEFINIDO,
            CONSTANCIA_EJECUTORIA,
            RESOLUCION_FALLO,
            COMPARENDO,
            CERTIFICADO_ASISTENCIA_CURSO,
            AUTORIZACION_CREACION_USUARIO,
            EVIDENCIA_ANULACION_RANGOS,
            EVIDENCIA_REGISTRO_RANGOS,
            AUDIENCIA,
            EXPEDIENTE_FORMATO_COMPARENDO,
            TITULO_EJECUTIVO,
            RESOLUCION_DECLARATORIA_REINCIDENCIA,
            VOLANTE_PAGO,
            PLANTILLA,
            TITULO_EJECUTIVO_DOC,
            INFRACCION_TRANSPORTE_PUBLICO,
            RESOLUCION_AUTOMATICA_ANULACION
    );
    @Id
    private UUID id;
    @NotNull
    private String nombre;
    private String cmisType;
    @ManyToOne
    private TipoDocumental padre;
    private String serie;

    public TipoDocumental() {
    }

    public TipoDocumental(UUID id, String nombre, String cmisType, String serie) {
        this(id, nombre, cmisType, serie, null);
    }

    public TipoDocumental(UUID id, String nombre, String cmisType, String serie, TipoDocumental padre) {
        this.id = id;
        this.nombre = nombre;
        this.cmisType = cmisType;
        this.padre = padre;
        this.serie = serie;
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

    public String getCmisType() {
        return cmisType;
    }

    public void setCmisType(String cmisType) {
        this.cmisType = cmisType;
    }

    public TipoDocumental getPadre() {
        return padre;
    }

    public void setPadre(TipoDocumental padre) {
        this.padre = padre;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }
}
