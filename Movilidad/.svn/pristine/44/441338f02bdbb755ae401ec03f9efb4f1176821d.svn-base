package co.gov.movilidadbogota.cmis.types;

import org.alfresco.mbeans.ConnectorServerFactory;
import org.alfresco.opencmis.CMISConnector;
import org.apache.chemistry.opencmis.commons.data.CmisExtensionElement;
import org.apache.chemistry.opencmis.commons.data.ExtensionsData;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.definitions.TypeDefinition;
import org.apache.chemistry.opencmis.commons.exceptions.CmisNotSupportedException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class MovilidadCmisServiceImpl extends org.alfresco.opencmis.AlfrescoCmisServiceImpl {
    private static Log logger = LogFactory.getLog(ConnectorServerFactory.class);

    public MovilidadCmisServiceImpl(CMISConnector connector) {
        super(connector);
    }
    @Override
    public TypeDefinition createType(String repositoryId, TypeDefinition type, ExtensionsData extension) {
        checkRepositoryId(repositoryId);
        System.out.println("El id del repositorio es "+repositoryId);
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
        throw new CmisNotSupportedException("Not supported, but there is hope!");
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
