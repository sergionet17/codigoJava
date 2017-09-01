package co.gov.movilidadbogota.sipa.serv.comparendos;

import co.gov.movilidadbogota.sipa.data.biz.comp.*;
import co.gov.movilidadbogota.sipa.data.biz.gen.AgenteTransito;
import co.gov.movilidadbogota.sipa.data.biz.gen.ClaseServicioVehiculo;
import co.gov.movilidadbogota.sipa.data.biz.gen.TipoVehiculo;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendo;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;
import co.gov.movilidadbogota.sipa.data.loc.Localidad;
import co.gov.movilidadbogota.sipa.data.loc.Municipio;

/**
 * Created by Admin on 04/07/2017.
 */
public class ComparendoDto {

    private Municipio municipioInfraccion;
    private Localidad localidadInfraccion;
    private Municipio municipioInfractor;
    private OrganismoTransito organismoTransito;
    private NumeroComparendo numeroComparendo;
    private CategoriaLicencia categoriaLicencia;
    private TipoInfractor tipoInfractor;
    private AgenteTransito agenteTransito;
    private Infraccion infraccion;
    private TipoDocumento tipoDocumentoInfractor;
    private TipoDocumento tipoDocumentoPropietario;
    private TipoDocumento tipoDocumentoTestigo;
    private ClaseServicioVehiculo claseVehiculo;
    private TipoVehiculo tipoVehiculo;
    private Licencia licencia;
    private Inmovilizacion inmovilizacion;

    public ComparendoDto() {
    }

    public AgenteTransito getAgenteTransito() {
        return agenteTransito;
    }

    public void setAgenteTransito(AgenteTransito agenteTransito) {
        this.agenteTransito = agenteTransito;
    }

    public Infraccion getInfraccion() {
        return infraccion;
    }

    public void setInfraccion(Infraccion infraccion) {
        this.infraccion = infraccion;
    }

    public TipoDocumento getTipoDocumentoInfractor() {
        return tipoDocumentoInfractor;
    }

    public void setTipoDocumentoInfractor(TipoDocumento tipoDocumentoInfractor) {
        this.tipoDocumentoInfractor = tipoDocumentoInfractor;
    }

    public TipoDocumento getTipoDocumentoPropietario() {
        return tipoDocumentoPropietario;
    }

    public void setTipoDocumentoPropietario(TipoDocumento tipoDocumentoPropietario) {
        this.tipoDocumentoPropietario = tipoDocumentoPropietario;
    }

    public TipoDocumento getTipoDocumentoTestigo() {
        return tipoDocumentoTestigo;
    }

    public void setTipoDocumentoTestigo(TipoDocumento tipoDocumentoTestigo) {
        this.tipoDocumentoTestigo = tipoDocumentoTestigo;
    }

    public Municipio getMunicipioInfraccion() {
        return municipioInfraccion;
    }

    public void setMunicipioInfraccion(Municipio municipioInfraccion) {
        this.municipioInfraccion = municipioInfraccion;
    }

    public Localidad getLocalidadInfraccion() {
        return localidadInfraccion;
    }

    public void setLocalidadInfraccion(Localidad localidadInfraccion) {
        this.localidadInfraccion = localidadInfraccion;
    }

    public Municipio getMunicipioInfractor() {
        return municipioInfractor;
    }

    public void setMunicipioInfractor(Municipio municipioInfractor) {
        this.municipioInfractor = municipioInfractor;
    }

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public NumeroComparendo getNumeroComparendo() {
        return numeroComparendo;
    }

    public void setNumeroComparendo(NumeroComparendo numeroComparendo) {
        this.numeroComparendo = numeroComparendo;
    }

    public CategoriaLicencia getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(CategoriaLicencia categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }

    public TipoInfractor getTipoInfractor() {
        return tipoInfractor;
    }

    public void setTipoInfractor(TipoInfractor tipoInfractor) {
        this.tipoInfractor = tipoInfractor;
    }

    public ClaseServicioVehiculo getClaseVehiculo() {
        return claseVehiculo;
    }

    public void setClaseVehiculo(ClaseServicioVehiculo claseVehiculo) {
        this.claseVehiculo = claseVehiculo;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public void setlicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    public Licencia getLicencia() {
        return licencia;
    }

    public Inmovilizacion getInmovilizacion() {
        return inmovilizacion;
    }

    public void setInmovilizacion(Inmovilizacion inmovilizacion) {
        this.inmovilizacion = inmovilizacion;
    }
}
