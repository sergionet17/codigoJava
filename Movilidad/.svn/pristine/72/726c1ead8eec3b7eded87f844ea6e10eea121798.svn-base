package co.gov.movilidadbogota.sipa.cli;

import co.gov.movilidadbogota.sipa.serv.api.ComparendoService;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoService;
import co.gov.movilidadbogota.sipa.serv.api.FinancieroService;
import co.gov.movilidadbogota.sipa.serv.api.VolantePagoService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SipaServicesClient extends RestTemplate {

    private ServiceLocationProperties serviceLocationProperties;

    public SipaServicesClient(ServiceLocationProperties serviceLocationProperties) {
        this.serviceLocationProperties = serviceLocationProperties;
    }

    public DocumentoService getDocumentos() {
        return new DocumentosServiceClient(this, serviceLocationProperties);
    }

    public ComparendoService getComparendos() {
        return new ComparendoServiceClient(this, serviceLocationProperties);
    }

    public FinancieroService getFinanciero() {
        return new FinancieroServiceClient(this, serviceLocationProperties);
    }

    public VolantePagoService getVolantePago(){ return  new VolantePagoServiceClient(this, serviceLocationProperties);}

}
