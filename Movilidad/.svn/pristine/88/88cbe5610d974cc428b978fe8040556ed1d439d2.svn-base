package co.gov.movilidadbogota.cmis.types;

import org.alfresco.mbeans.ConnectorServerFactory;
import org.alfresco.opencmis.CMISConnector;
import org.alfresco.repo.tenant.NetworksService;
import org.alfresco.repo.tenant.TenantAdminService;
import org.alfresco.rest.api.CustomModels;
import org.alfresco.rest.api.model.CustomType;
import org.apache.chemistry.opencmis.commons.data.CmisExtensionElement;
import org.apache.chemistry.opencmis.commons.data.ExtensionsData;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.definitions.TypeDefinition;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.apache.chemistry.opencmis.commons.exceptions.CmisNotSupportedException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class MovilidadPublicApiCmisServiceImpl extends org.alfresco.opencmis.PublicApiAlfrescoCmisService {
    private static Log logger = LogFactory.getLog(ConnectorServerFactory.class);

    private CustomModels customModels;

    public MovilidadPublicApiCmisServiceImpl(CMISConnector connector, TenantAdminService tenantAdminService, NetworksService networksService, CustomModels customModels) {
        super(connector, tenantAdminService, networksService);
        this.customModels = customModels;
    }

    /**
     * TypeDefinition normalmente es un @see org.apache.chemistry.opencmis.commons.impl.dataobjects.DocumentTypeDefinitionImpl
     * que tiene las siguientes propiedades:
     *  serialVersionUID=1,
     *  contentStreamAllowed=ALLOWED,
     *  isVersionable=true,serialVersionUID=2,
     *  id=D:movdpa:resolucion-de-absolucion,
     *  localName=resolucion-de-absolucion,
     *  localNamespace=http://www.movilidadbogota.gov.co/model/dpa/1.0,queryName=movdpa:resolucion_x002d_de_x002d_absolucion,
     *  displayName=Resoluci?n de Absolucion,
     *  description=D:movdpa:resolucion-de-absolucion,
     *  baseId=CMIS_DOCUMENT,
     *  parentId=cmis:document,
     *  isCreatable=true,
     *  isFileable=true,
     *  isQueryable=true,
     *  isIncludedInSupertypeQuery=true,isFulltextIndexed=true,isControllableACL=true,isControllablePolicy=false,propertyDefinitions={},typeMutability=<null>,$assertionsDisabled=true,serialVersionUID=1,extensions=[{}mandatoryAspects {}: [{}mandatoryAspect {}: P:sys:localized]]]
     * @param repositoryId
     * @param type
     * @param extension
     * @return
     */
    @Override
    public TypeDefinition createType(String repositoryId, TypeDefinition type, ExtensionsData extension) {
        checkRepositoryId(repositoryId);
        System.out.println("Desde MovilidadPublicApiCmisServiceImpl"+repositoryId);
        if (logger.isErrorEnabled())
        {
            logger.error("El id del repositorio en logger es "+repositoryId);
            logger.error("Type:"+ (new MovilidadReflectionToStringBuilder(type) ).toString());
            Map<String, PropertyDefinition<?>> m = type.getPropertyDefinitions();
            for(Map.Entry<String, PropertyDefinition<?>> entry: m.entrySet()) {
                logger.error("PKey:"+entry.getKey()+" "+(new MovilidadReflectionToStringBuilder(entry.getValue()) ).toString());
            }
            logger.error("Extension:"+(new MovilidadReflectionToStringBuilder(extension) ).toString());
            List<CmisExtensionElement> l = extension.getExtensions();
            for (CmisExtensionElement el:l) {
                logger.error("EE:"+(new MovilidadReflectionToStringBuilder(el) ).toString());
            }
        }
        BaseTypeId baseType = type.getBaseTypeId();
        if(baseType.value().equals(BaseTypeId.CMIS_SECONDARY.value())) {
            //TODO: llamar
            // customModels.createCustomAspect()
        } else {
            CustomType customType = new CustomType();
            customType.setName(type.getLocalName());
            customType.setDescription(type.getDescription());
            customType.setParentName(type.getParentTypeId());
            customModels.createCustomType(type.getLocalName(), customType);
        }

        throw new CmisNotSupportedException("Not supported, but there is hope!");
    }
    @Override
    public void deleteType(String repositoryId, String typeId, ExtensionsData extension) {
        //TODO: Usar customModels.deleteCustomType
        //customModels.deleteCustomModel();
        if (logger.isErrorEnabled()) {
            logger.error("DeleteType" + repositoryId);
            logger.error("Type:" + typeId);
        }
        if(typeId.startsWith("P")) {
            //Es secundario
        } else {
            //Es primario o Folder?
        }
        super.deleteType(repositoryId, typeId, extension);
    }
    private class MovilidadReflectionToStringBuilder extends ReflectionToStringBuilder {
        public MovilidadReflectionToStringBuilder(Object o){
            super(o);
        }
        @Override
        protected boolean accept(Field field) {
            return true;
        }
    }
}
