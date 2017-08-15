package co.gov.movilidadbogota.cmis.types;
import org.alfresco.opencmis.PublicApiAlfrescoCmisServiceFactory;
import org.alfresco.opencmis.AlfrescoCmisService;
import org.alfresco.opencmis.CMISConnector;
import org.alfresco.repo.tenant.NetworksService;
import org.alfresco.repo.tenant.TenantAdminService;
import org.alfresco.rest.api.CustomModels;

public class MovilidadPublicApiCmisServiceFactory extends PublicApiAlfrescoCmisServiceFactory  {

    protected CustomModels customModels;
    protected TenantAdminService tenantAdminService;
    protected NetworksService networksService;

    @Override
    public void setTenantAdminService(TenantAdminService tenantAdminService) {
        super.setTenantAdminService(tenantAdminService);
        this.tenantAdminService = tenantAdminService;
    }

    @Override
    public void setNetworksService(NetworksService networksService) {
        super.setNetworksService(networksService);
        this.networksService = networksService;
    }

    public void setCustomModels(org.alfresco.rest.api.CustomModels customModels) {
        this.customModels = customModels;
    }

    @Override
    protected AlfrescoCmisService getCmisServiceTarget(CMISConnector connector)
    {
        return new MovilidadPublicApiCmisServiceImpl(connector,tenantAdminService, networksService, customModels);
    }

}
