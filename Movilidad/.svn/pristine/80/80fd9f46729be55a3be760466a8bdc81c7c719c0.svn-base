package co.gov.movilidadbogota.sipa.data.doc;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

/**
 * Es la configuración de una plantilla y los documentos generados por la misma.
 * Las características de la plantilla incluyen banderas que indican si los
 * documentos se sustancian (editan a mano), se verifican (dar visto bueno), se
 * firman y si la firma es automática o manual.
 */
@Entity
public class Plantilla {

    @Id
    private UUID id;
    private String nombre;
    private Boolean seSustancia;
    private Boolean seVerifica;
    private Boolean seFirma;
    private Boolean seFirmaManual;
    private String beanExtraccion;

    @ManyToOne
    private TipoDocumental tipoDocumental;

    @ManyToOne
    private TipoPlantilla tipoPlantilla;

    @ManyToOne
    private Documento documento;

    private String contentTypeProducido;

    private String nombreArchivo;

    public Plantilla() {
    }

    public Boolean getSeSustancia() {
        return seSustancia;
    }

    public void setSeSustancia(Boolean seSustancia) {
        this.seSustancia = seSustancia;
    }

    public Boolean getSeVerifica() {
        return seVerifica;
    }

    public void setSeVerifica(Boolean seVerifica) {
        this.seVerifica = seVerifica;
    }

    public Boolean getSeFirma() {
        return seFirma;
    }

    public void setSeFirma(Boolean seFirma) {
        this.seFirma = seFirma;
    }

    public Boolean getSeFirmaManual() {
        return seFirmaManual;
    }

    public void setSeFirmaManual(Boolean seFirmaManual) {
        this.seFirmaManual = seFirmaManual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBeanExtraccion() {
        return beanExtraccion;
    }

    public void setBeanExtraccion(String beanExtraccion) {
        this.beanExtraccion = beanExtraccion;
    }

    public TipoPlantilla getTipoPlantilla() {
        return tipoPlantilla;
    }

    public void setTipoPlantilla(TipoPlantilla tipoPlantilla) {
        this.tipoPlantilla = tipoPlantilla;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public String getContentTypeProducido() {
        return contentTypeProducido;
    }

    public void setContentTypeProducido(String contentTypeProducido) {
        this.contentTypeProducido = contentTypeProducido;
    }

    public TipoDocumental getTipoDocumental() {
        return tipoDocumental;
    }

    public void setTipoDocumental(TipoDocumental tipoDocumental) {
        this.tipoDocumental = tipoDocumental;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public String toString() {
        return "Plantilla{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", seSustancia=" + seSustancia +
                ", seVerifica=" + seVerifica +
                ", seFirma=" + seFirma +
                ", seFirmaManual=" + seFirmaManual +
                ", beanExtraccion='" + beanExtraccion + '\'' +
                ", tipoDocumental=" + tipoDocumental +
                ", tipoPlantilla=" + tipoPlantilla +
                ", documento=" + documento +
                ", contentTypeProducido='" + contentTypeProducido + '\'' +
                ", nombreArchivo='" + nombreArchivo + '\'' +
                '}';
    }
}
