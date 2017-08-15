package co.gov.movilidadbogota.sipa.cmis.type;

import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.enums.Cardinality;
import org.apache.chemistry.opencmis.commons.enums.PropertyType;
import org.apache.chemistry.opencmis.commons.enums.Updatability;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.AbstractPropertyDefinition;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.PropertyBooleanDefinitionImpl;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.PropertyDateTimeDefinitionImpl;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.PropertyDecimalDefinitionImpl;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.PropertyStringDefinitionImpl;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.PropertyUriDefinitionImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Properties Type Factory
 * 
 * @author Hermes
 *
 */
@Component("propertiesTypeFactory")
@Scope("singleton")
public class PropertiesTypeFactory {
	
	/**
	 * Create a single property definition with a choice list
	 * 
	 * @param propId
	 * @param propType
	 * @return
	 */
	public PropertyDefinition<?> createPropertyDefinition(String propId, PropertyType propType) {

		Cardinality cardinality = Cardinality.SINGLE;
		String description = propType.value()+" property definition";
		String displayName = propType.value()+"PropertyDefinition";
		Updatability updatability = Updatability.READWRITE;
		Boolean inherited = false;
		Boolean required = false;
		
		AbstractPropertyDefinition<?> result = null;

		switch (propType) {
		case BOOLEAN:
			result = new PropertyBooleanDefinitionImpl();
			break;
		case DATETIME:
			result = new PropertyDateTimeDefinitionImpl();
			break;
		case DECIMAL:
			result = new PropertyDecimalDefinitionImpl();
			break;
		case STRING:
			result = new PropertyStringDefinitionImpl();
			break;
		case URI:
			result = new PropertyUriDefinitionImpl();
			break;
		default:
			throw new RuntimeException("Unknown datatype! Spec change?");
		}

		result.setId(propId);
		result.setLocalName(propId);
		result.setDisplayName(displayName);
		result.setDescription(description);
		result.setPropertyType(propType);
		result.setCardinality(cardinality);
		result.setUpdatability(updatability);
		result.setIsInherited(inherited);
		result.setIsRequired(required);
		result.setIsQueryable(true);
		result.setQueryName(propId);

		return result;
	}
	

}
