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

import org.alfresco.mbeans.ConnectorServerFactory;
import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.repo.tenant.*;
import org.alfresco.repo.tenant.TenantUtil.TenantRunAsWork;
import org.alfresco.rest.api.CustomModels;
import org.alfresco.rest.api.model.CustomAspect;
import org.alfresco.rest.api.model.CustomModelProperty;
import org.alfresco.rest.api.model.CustomType;
import org.alfresco.rest.framework.core.exceptions.EntityNotFoundException;
import org.alfresco.service.cmr.dictionary.CustomModelService;
import org.alfresco.util.FileFilterMode;
import org.alfresco.util.FileFilterMode.Client;
import org.apache.chemistry.opencmis.commons.data.*;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.definitions.TypeDefinition;
import org.apache.chemistry.opencmis.commons.enums.*;
import org.apache.chemistry.opencmis.commons.exceptions.CmisBaseException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisNotSupportedException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisRuntimeException;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.RepositoryInfoImpl;
import org.apache.chemistry.opencmis.commons.spi.Holder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    protected CustomModelService customModelService;
    
    public PublicApiAlfrescoCmisService(CMISConnector connector, TenantAdminService tenantAdminService, NetworksService networksService, CustomModels customModels, CustomModelService customModelService)
    {
        super(connector);
        
        this.connector = connector;
        this.networksService = networksService;
        this.tenantAdminService = tenantAdminService;
        this.customModels = customModels;
        this.customModelService = customModelService;
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
            if (logger.isInfoEnabled())
            {
                logger.info("TypeDefinition.createType[14:48]"+repositoryId);
                logger.info("Type:"+ (new MovilidadReflectionToStringBuilder(type) ).toString());
            }
            String modelName = getModelName(type);
            BaseTypeId baseType = type.getBaseTypeId();
            logger.info(baseType + " es el tipo");
            if (baseType.value().equals(BaseTypeId.CMIS_SECONDARY.value())) {
                CustomAspect aspect = new CustomAspect();
                aspect.setName(getName(type));
                aspect.setTitle(type.getDisplayName());
                aspect.setDescription(type.getDescription());
                aspect.setProperties(getCustomModelProperties(type));
                aspect.setParentName(getParentType(type));
                logger.info("Creando aspecto con " + modelName + " y padre " + type.getParentTypeId() + "[" + aspect + "]");
                try {
                    customModels.createCustomAspect(modelName, aspect);
                } catch (Exception e) {
                    logger.error("Creando aspecto" + e);
                    throw new CmisNotSupportedException(e.getMessage());
                }
            } else {
                CustomType customType = new CustomType();
                customType.setName(getName(type));
                customType.setTitle(type.getDisplayName());
                customType.setDescription(type.getDescription());
                customType.setParentName(getParentType(type));
                customType.setProperties(getCustomModelProperties(type));
                logger.info("Creando tipo con " + modelName + " y padre " + type.getParentTypeId() + "[" + customType + "]");
                try {
                    customModels.createCustomType(modelName, customType);
                } catch (EntityNotFoundException enfe) {
                    logger.warn("Creando tipo, algo no se encuentra" ,enfe);
                    throw new CmisObjectNotFoundException(enfe.getMessage());
                }
            }
        } catch(CmisBaseException e) {
            throw e;
        } catch(Exception e2) {
            logger.error("Error desconocido",e2);
            throw new CmisRuntimeException("Error no identificado creando tipo",e2);

        }
        return type;
    }

    /**
     * Sacar el nombre de la definición de tipos.
     * Maneja el hecho de que a veces los tipos llegan con cosas raras.
     * @param type
     * @return
     */
    private String getName(TypeDefinition type) {
        String localName = type.getLocalName();
        String [] tokens = localName.split(":");
        if(tokens.length<=1) {
            return localName;
        } if(tokens.length==2) {
            return tokens[1];
        } else {
            return tokens[tokens.length-1];
        }
    }

    /**
     * Dado un TypeDefinition mapea todas las definciones de propiedades y las devuelve.
     * @param type
     * @return
     */
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
        String parentTypeId = type.getParentTypeId();
        if("cmis:document".equals(parentTypeId)) {
            return "cm:content";
        } else if("cmis:folder".equals(parentTypeId)) {
            return "cm:folder";
        } else if("cmis:secondary".equals(parentTypeId)) {
            //This type gives us no information, because we know it is an aspect
            return null;
        } else {
            if(logger.isInfoEnabled()) {
                logger.info("Tipo padre implícito para "+type.getParentTypeId()+ " usando el por defecto");
            }
            return type.getParentTypeId();
        }

        //Default to something
    }

    /**
     * Devuelve el nombre del modelo basado en los datos que llegan.
     * Se basa en queryName. Si es de la forma [x] retorna [x]
     * Si es de la forma [x]:[y] retorna [x]
     * Si es de la forma [c]:[y]:[z] retorna [y]
     * @param type
     * @return
     */
    private String getModelName(TypeDefinition type) {

        String queryName = type.getQueryName();
        String [] token = queryName.split(":");
        switch(token.length) {
            case 0:
                return queryName;
            case 1:
                return queryName;
            case 2:
                return token[0];
            default:
                return token[1];
        }
    }

    /**
     * Elimina el modelo/aspecto
     * @param repositoryId
     * @param typeId De la forma [a]:[b]:[c] entonces modelo es b y tipo es c
     * @param extension
     */
    @Override
    public void deleteType(String repositoryId, String typeId, ExtensionsData extension) {
        String[] tokens = typeId.split(":");
        if (logger.isDebugEnabled()) {
            logger.debug("deleteType [repo =" + repositoryId+" type = "+typeId+"]");
        }
        String modelName="";
        String typeName = "";
        switch(tokens.length) {
            case 0:
                throw new CmisObjectNotFoundException("No existe el tipo "+typeId);
            case 1:
                throw new CmisObjectNotFoundException("No hay suficiente información para encontrar tipo:modelo "+typeId);
            default:
                modelName = tokens[tokens.length-2];
                typeName = tokens[tokens.length-1];
        }
        if (logger.isDebugEnabled()) {
            logger.debug("deleting [" + modelName+":"+typeName+"]");
        }
        customModelService.deactivateCustomModel(modelName);
        if (logger.isDebugEnabled()) {
            logger.debug(modelName+" deactivated");
        }
        if(typeId.startsWith("P")) {
            customModels.deleteCustomType(modelName, typeName);
        } else {
            customModels.deleteCustomAspect(modelName, typeName);
        }
        customModelService.activateCustomModel(modelName);
        if (logger.isDebugEnabled()) {
            logger.debug(modelName+" activated");
        }
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
