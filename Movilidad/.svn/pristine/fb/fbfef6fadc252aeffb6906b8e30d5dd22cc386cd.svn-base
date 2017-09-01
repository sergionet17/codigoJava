package co.gov.movilidadbogota.sipa.serv.doc;

import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.doc.Expediente;
import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.ObjectIdImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CmisRepository implements ExternalRepository {

    @Autowired
    private CmisSessionFactory cmisSessionFactory;

    @Override
    public String createFolder(Expediente expediente) {
        Session session = cmisSessionFactory.createSession();
        Folder root = session.getRootFolder();
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(PropertyIds.OBJECT_ID, expediente.getIdOrigen().toString());
        properties.put(PropertyIds.OBJECT_TYPE_ID, expediente.getTipoDocumental().getCmisType());
        properties.put(PropertyIds.NAME, expediente.getIdOrigen().toString());
        Folder folder = root.createFolder(properties);
        Property<String> objectIdProperty = folder.getProperty(PropertyIds.OBJECT_ID);
        return objectIdProperty != null ? objectIdProperty.getValueAsString() : folder.getId();
    }

    @Override
    public String createDocument(Documento documento, Expediente expediente, byte[] contenido) throws Exception {
        Session session = cmisSessionFactory.createSession();
        CmisObject folderObject = session.getObject(expediente.getExternalId());
        if (!(folderObject instanceof Folder)) {
            throw new Exception("Object is not a folder: " + expediente.getExternalId());
        }
        Folder folder = (Folder) folderObject;
        return createDocument(documento, folder, contenido);
    }

    @Override
    public String createDocument(Documento documento, byte[] contenido) throws Exception {
        Session session = cmisSessionFactory.createSession();
        return createDocument(documento, session.getRootFolder(), contenido);
    }

    private String createDocument(Documento documento, Folder folder, byte[] contenido) throws Exception {
        Session session = cmisSessionFactory.createSession();
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(PropertyIds.OBJECT_ID, documento.getId().toString());
        properties.put(PropertyIds.OBJECT_TYPE_ID, documento.getTipoDocumental().getCmisType());
        properties.put(PropertyIds.NAME, buildName(documento));
        ContentStream contentStream = session.getObjectFactory().createContentStream(
                documento.getOriginalName(),
                contenido.length,
                documento.getContentType(),
                new ByteArrayInputStream(contenido)
        );
        Document doc = folder.createDocument(properties, contentStream, VersioningState.MAJOR);
        return doc.getId();
    }

    @Override
    public String buildName(Documento documento) {
        return String.format("%s_%s", documento.getId(), documento.getOriginalName());
    }

    @Override
    public void updateDocument(Documento documento, byte[] contenido) throws Exception {
        Session session = cmisSessionFactory.createSession();
        Document doc = (Document) session.getLatestDocumentVersion(documento.getExternalId());
        ContentStream contentStream = session.getObjectFactory().createContentStream(
                documento.getOriginalName(),
                contenido.length,
                documento.getContentType(),
                new ByteArrayInputStream(contenido)
        );
        doc.setContentStream(contentStream, true);
    }

    @Override
    public void addDocumentToFolder(Documento documento, Expediente expediente) throws Exception {
        Session session = cmisSessionFactory.createSession();
        Document doc = session.getLatestDocumentVersion(documento.getExternalId());
        CmisObject folderObject = session.getObject(expediente.getExternalId());
        if (!(folderObject instanceof Folder)) {
            throw new Exception("Object is not a folder: " + expediente.getExternalId());
        }
        Folder folder = (Folder) folderObject;
        for (CmisObject child : folder.getChildren()) {
            if (child instanceof Document) {
                Document childDocument = (Document) child;
                if (childDocument.getId().equals(documento.getExternalId())) {
                    throw new Exception(String.format(
                            "El documento '%s' ya existe en el folder '%s'",
                            documento.getId(),
                            expediente.getIdOrigen()
                    ));
                }
            }
        }
        doc.addToFolder(new ObjectIdImpl(folder.getId()), true);
    }

    @Override
    public byte[] getContenido(String externalId) {
        Document d = cmisSessionFactory.createSession().getLatestDocumentVersion(externalId);
        if (d.getContentStream() == null
                || d.getContentStream().getStream() == null
                || d.getContentStream().getLength() == 0) {
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            IOUtils.copy(d.getContentStream().getStream(), baos);
        } catch (IOException e) {
            throw new RuntimeException("No se puede leer el contenido del objeto externo: " + externalId);
        }
        return baos.toByteArray();
    }

}
