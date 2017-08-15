/*
 * #%L
 * Alfresco Remote API
 * %%
 * Copyright (C) 2005 - 2016 Alfresco Software Limited
 * %%
 * This file is part of the Alfresco software. 
 * If the software was purchased under a paid Alfresco license, the terms of 
 * the paid license agreement will prevail.  Otherwise, the software is 
 * provided under the following open source license terms:
 * 
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package org.alfresco.opencmis;

import java.lang.reflect.Field;
import java.util.*;

import org.alfresco.mbeans.ConnectorServerFactory;
import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.repo.tenant.Network;
import org.alfresco.repo.tenant.NetworksService;
import org.alfresco.repo.tenant.TenantAdminService;
import org.alfresco.repo.tenant.TenantService;
import org.alfresco.repo.tenant.TenantUtil;
import org.alfresco.repo.tenant.TenantUtil.TenantRunAsWork;
import org.alfresco.rest.api.CustomModels;
import org.alfresco.rest.api.model.CustomAspect;
import org.alfresco.rest.api.model.CustomModelProperty;
import org.alfresco.rest.api.model.CustomType;
import org.alfresco.util.FileFilterMode;
import org.alfresco.util.FileFilterMode.Client;
import org.apache.chemistry.opencmis.commons.data.*;
import org.apache.chemistry.opencmis.commons.data.Properties;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.definitions.TypeDefinition;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.apache.chemistry.opencmis.commons.enums.Cardinality;
import org.apache.chemistry.opencmis.commons.enums.PropertyType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisNotSupportedException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.RepositoryInfoImpl;
import org.apache.chemistry.opencmis.commons.spi.Holder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Override OpenCMIS service object - for public api
 * 
 * @author sglover
 * @since PublicApi1.0
 */
public class PublicApiAlfrescoCmisService extends AlfrescoCmisServiceImpl
{
    private static Log logger = LogFactory.getLog(ConnectorServerFactory.class);
	protected CMISConnector connector;
    protected TenantAdminService tenantAdminService;
    protected NetworksService networksService;
    protected CustomModels customModels;
    
    public PublicApiAlfrescoCmisService(CMISConnector connector, TenantAdminService tenantAdminService, NetworksService networksService, CustomModels customModels)
    {
        super(connector);
        
        this.connector = connector;
        this.networksService = networksService;
        this.tenantAdminService = tenantAdminService;
        this.customModels = customModels;
    }
    
    @Override
    public String create(String repositoryId, Properties properties, String folderId,
                ContentStream contentStream, VersioningState versioningState,
                List<String> policies, ExtensionsData extension)
    {
        FileFilterMode.setClient(Client.cmis);
        try
        {
            return super.create(
                        repositoryId,
                        properties,
                        folderId,
                        contentStream,
                        versioningState,
                        policies,
                        extension);
        }
        finally
        {
            FileFilterMode.clearClient();
        }
    }


    /**
     * Overridden to capture content upload for publishing to analytics service.
     */
    @Override
    public String createDocument(String repositoryId, Properties properties, String folderId,
                ContentStream contentStream, VersioningState versioningState,
                List<String> policies, Acl addAces, Acl removeAces, ExtensionsData extension)
    {
        String newId = super.createDocument(
                    repositoryId,
                    properties,
                    folderId,
                    contentStream,
                    versioningState,
                    policies,
                    addAces,
                    removeAces,
                    extension);
        return newId;
    }

    /**
     * Overridden to capture content upload for publishing to analytics service.
     */
    @Override
    public void setContentStream(String repositoryId, Holder<String> objectId,
                Boolean overwriteFlag, Holder<String> changeToken, ContentStream contentStream,
                ExtensionsData extension)
    {
        FileFilterMode.setClient(Client.cmis);
        try
        {
            super.setContentStream(repositoryId, objectId, overwriteFlag, changeToken, contentStream, extension);
        }
        finally
        {
            FileFilterMode.clearClient();
        }
    }

    @Override
    public List<RepositoryInfo> getRepositoryInfos(ExtensionsData extension)
    {
    	// for currently authenticated user
    	PagingResults<Network> networks = networksService.getNetworks(new PagingRequest(0, Integer.MAX_VALUE));
    	List<Network> page = networks.getPage();
        final List<RepositoryInfo> repoInfos = new ArrayList<RepositoryInfo>(page.size() + 1);
        for (Network network : page)
        {
            repoInfos.add(getRepositoryInfo(network));
        }
        return repoInfos;
    }
    
    @Override
    public RepositoryInfo getRepositoryInfo(String repositoryId, ExtensionsData extension)
    {
    	Network network = null;
    	
    	try
    	{
            checkRepositoryId(repositoryId);
            network = networksService.getNetwork(repositoryId);
    	}
    	catch(Exception e)
    	{
    		// ACE-2540: Avoid information leak. Same response if repository does not exist or if user is not a member
            throw new CmisObjectNotFoundException("Unknown repository '" + repositoryId + "'!");
    	}

        return getRepositoryInfo(network);
    }

    private RepositoryInfo getRepositoryInfo(final Network network)
    {
    	final String networkId = network.getTenantDomain();
        final String tenantDomain = (networkId.equals(TenantUtil.SYSTEM_TENANT) || networkId.equals(TenantUtil.DEFAULT_TENANT)) ? TenantService.DEFAULT_DOMAIN : networkId;

        return TenantUtil.runAsSystemTenant(new TenantRunAsWork<RepositoryInfo>()
        {
            public RepositoryInfo doWork()
            {
                RepositoryInfoImpl repoInfo = (RepositoryInfoImpl)connector.getRepositoryInfo(getContext().getCmisVersion());

                repoInfo.setId(!networkId.equals("") ? networkId : TenantUtil.SYSTEM_TENANT);
                repoInfo.setName(tenantDomain);
                repoInfo.setDescription(tenantDomain);

                return repoInfo;
            }
        }, tenantDomain);
    }

    @Override
    public void checkRepositoryId(String repositoryId)
    {
        if(repositoryId.equals(TenantUtil.DEFAULT_TENANT) || repositoryId.equals(TenantUtil.SYSTEM_TENANT))
        {
            // TODO check for super admin
            return;
        }

        if(!tenantAdminService.existsTenant(repositoryId) || !tenantAdminService.isEnabledTenant(repositoryId))
        {
            throw new CmisObjectNotFoundException("Unknown repository '" + repositoryId + "'!");
        }
    }
    
    @Override
    public void beforeCall()
    {
        // NOTE: Don't invoke super beforeCall to exclude authentication which is already supported by
        //       Web Script F/W
        //super.beforeCall();
    }
    
    @Override
    public void afterCall()
    {
        // NOTE: Don't invoke super afterCall to exclude authentication which is already supported by
        //       Web Script F/W
        //super.afterCall();
    }
    
    @Override
    public void close()
    {
    	super.close();
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
        try {
            if (logger.isErrorEnabled())
            {
                logger.error("TypeDefinition.createType[20:07]"+repositoryId);
                logger.error("Type:"+ (new MovilidadReflectionToStringBuilder(type) ).toString());
                Map<String, PropertyDefinition<?>> m = type.getPropertyDefinitions();
                for(Map.Entry<String, PropertyDefinition<?>> entry: m.entrySet()) {
                    logger.error("PKey:"+entry.getKey()+" "+(new MovilidadReflectionToStringBuilder(entry.getValue()) ).toString());
                }
                logger.error("Extension:"+(new MovilidadReflectionToStringBuilder(extension) ).toString());
                if(extension!=null) {
                    List<CmisExtensionElement> l = extension.getExtensions();
                    for (CmisExtensionElement el : l) {
                        logger.error("EE:" + (new MovilidadReflectionToStringBuilder(el)).toString());
                    }
                }
            }
            String modelName = getModelName(type);
            BaseTypeId baseType = type.getBaseTypeId();
            logger.error(baseType + " es el tipo");
            if (baseType.value().equals(BaseTypeId.CMIS_SECONDARY.value())) {
                CustomAspect aspect = new CustomAspect();
                aspect.setName(type.getLocalName());
                aspect.setTitle(type.getDisplayName());
                aspect.setDescription(type.getDescription());
                aspect.setProperties(getCustomModelProperties(type));
                logger.error("Creando aspecto con " + modelName + " y padre " + type.getParentTypeId() + "[" + aspect + "]");
                try {
                    customModels.createCustomAspect(modelName, aspect);
                } catch (Exception e) {
                    logger.error("Creando aspecto" + e);
                    throw new CmisNotSupportedException(e.getMessage());
                }
            } else {
                CustomType customType = new CustomType();
                customType.setName(type.getLocalName());
                customType.setTitle(type.getDisplayName());
                customType.setDescription(type.getDescription());
                customType.setParentName(getParentType(type));
                customType.setProperties(getCustomModelProperties(type));
                logger.error("Creando tipo con " + modelName + " y padre " + type.getParentTypeId() + "[" + customType + "]");
                try {
                    customModels.createCustomType(modelName, customType);
                } catch (Exception e) {
                    logger.error("Creando tipo" ,e);
                    throw new CmisNotSupportedException(e.getMessage());
                }
            }
        } catch(Exception e) {
            logger.error("Error desconocido",e);
        }
        return type;
    }

    private List<CustomModelProperty> getCustomModelProperties(TypeDefinition type) {
        List<CustomModelProperty> customModelProperties = new ArrayList<CustomModelProperty>();
        for(Map.Entry<String, PropertyDefinition<?>> entry: type.getPropertyDefinitions().entrySet()) {
            CustomModelProperty cmp = new CustomModelProperty();
            PropertyDefinition<?> cmisProperty = entry.getValue();
            cmp.setDataType(getAlfrescoPropertyType(cmisProperty.getPropertyType()));
            cmp.setMultiValued(Cardinality.MULTI.equals(cmisProperty.getCardinality()));
            cmp.setMandatory(cmisProperty.isRequired());
            cmp.setIndexed(cmisProperty.isQueryable());
            cmp.setDescription(cmisProperty.getDescription());
            cmp.setTitle(cmisProperty.getDisplayName());
            String fullname = entry.getKey();
            int colon = fullname.indexOf(':');
            if(colon>0) {
                fullname = fullname.substring(colon+1);
            }
            cmp.setName(fullname);
            customModelProperties.add(cmp);
        }
        return customModelProperties;
    }

    /**
     * Dado un propertyType de CMIS devolver el tipo en Alfresco
     * @param propertyType
     * @return
     */
    private String getAlfrescoPropertyType(PropertyType propertyType) {
        switch(propertyType) {
            case STRING:
                return "d:text";
            case ID:
                return "d:int";
            case DATETIME:
                return "d:datetime";
            case BOOLEAN:
                return "d:boolean";
            case INTEGER:
                return "d:int";
            case DECIMAL:
                return "d:float";
            case URI:
                return "d:text";
            default:
                return "d:text";
        }
    }

    /**
     * Devuelve el tipo válido en Alfresco, basado en un tipo válido en CMIS
     * @param type
     * @return cm:content, cm:folder o [namespace]:[tipo]
     */
    private String getParentType(TypeDefinition type) {
        if("cmis:document".equals(type.getParentTypeId())) {
            return "cm:content";
        } else if("cmis:folder".equals(type.getParentTypeId())) {
            return "cm:folder";
        } else {
            if(logger.isInfoEnabled()) {
                logger.info("Tipo padre implícito para "+type.getParentTypeId()+ " usando el por defecto");
            }
            return type.getParentTypeId();
        }

        //Default to something
    }

    private String getModelName(TypeDefinition type) {
        String queryName = type.getQueryName();
        int colon = queryName.indexOf(':');
        String modelName="";
        if(colon>0) {
            modelName = queryName.substring(0,colon);

        }
        return modelName;
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
