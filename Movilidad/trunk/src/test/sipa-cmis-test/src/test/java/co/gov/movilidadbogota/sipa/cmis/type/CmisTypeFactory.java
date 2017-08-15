package co.gov.movilidadbogota.sipa.cmis.type;

import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.apache.chemistry.opencmis.commons.enums.ContentStreamAllowed;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.AbstractTypeDefinition;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.DocumentTypeDefinitionImpl;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.FolderTypeDefinitionImpl;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.SecondaryTypeDefinitionImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.gov.movilidadbogota.sipa.common.ConfParameters;
import co.gov.movilidadbogota.sipa.common.Constants;

/**
 * Document types Factory
 * 
 * @author Hermes
 *
 */
@Component("cmisTypeFactory")
@Scope("singleton")
public class CmisTypeFactory {	
		
	/**
	 * Create a document type definition 
	 * 
	 * @param sessionAdmin
	 * @param parentType
	 * @param typeId
	 * @param description
	 * @return
	 */
	public AbstractTypeDefinition createCmisType(String baseType, String parentType, String typeId, String description) {
		
		AbstractTypeDefinition newType = null;		       
		if(baseType.equals(BaseTypeId.CMIS_DOCUMENT.value())){
			newType = new DocumentTypeDefinitionImpl();
			newType.setBaseTypeId(BaseTypeId.CMIS_DOCUMENT);
			((DocumentTypeDefinitionImpl)newType).setIsVersionable(true);
			((DocumentTypeDefinitionImpl)newType).setContentStreamAllowed(ContentStreamAllowed.ALLOWED);
		}
		else if(baseType.equals(BaseTypeId.CMIS_FOLDER.value())){
			newType = new FolderTypeDefinitionImpl();
			newType.setBaseTypeId(BaseTypeId.CMIS_FOLDER);
		}
		else if(baseType.equals(BaseTypeId.CMIS_SECONDARY.value())){
			newType = new SecondaryTypeDefinitionImpl();
			newType.setBaseTypeId(BaseTypeId.CMIS_SECONDARY);
		}
		if(newType == null) 
			return null;
        
		newType.setParentTypeId(parentType);
        newType.setIsControllableAcl(false);
        newType.setIsControllablePolicy(false);
        newType.setIsCreatable(true);
        newType.setDescription(description);
        newType.setDisplayName(description);
        newType.setIsFileable(true);
        newType.setIsFulltextIndexed(false);
        newType.setIsIncludedInSupertypeQuery(true);
        newType.setLocalName(typeId);
        newType.setLocalNamespace(ConfParameters.getParm(Constants.CONF_NAMESPACE_URL));
        newType.setIsQueryable(true);
        newType.setQueryName(typeId);
        newType.setId(typeId);
                
        return newType;
	}	
}
