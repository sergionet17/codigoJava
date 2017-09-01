package co.gov.movilidadbogota.cmis.types;

import org.alfresco.opencmis.AlfrescoCmisService;
import org.alfresco.opencmis.AlfrescoCmisServiceImpl;
import org.alfresco.opencmis.CMISConnector;
import org.alfresco.opencmis.AlfrescoCmisServiceFactory;

public class MovilidadCmisServiceFactory extends AlfrescoCmisServiceFactory {
    /**
     * Acá se invoca el creador de {@link AlfrescoCmisServiceFactory}, por lo que
     * se envía una instancia de {@link MovilidadCmisServiceImpl}
     * @param connector
     * @return
     */
    @Override
    protected AlfrescoCmisService getCmisServiceTarget(CMISConnector connector)
    {
        return new MovilidadCmisServiceImpl(connector);
    }
}
