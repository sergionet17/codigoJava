package co.gov.movilidadbogota.sipa.cmis.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.apache.tika.Tika;
import org.apache.tika.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.gov.movilidadbogota.sipa.cmis.config.ConfBeans;
import co.gov.movilidadbogota.sipa.cmis.session.CmisSessionFactory;
import co.gov.movilidadbogota.sipa.common.ConfParameters;
import co.gov.movilidadbogota.sipa.common.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfBeans.class })
public class CmisAECMTypes {

	// Factory classes
	@Autowired
	private CmisSessionFactory sessionFactory;
	
	private static Session sessionAdmin;
	
	// Tika for mime types ;)
	private Tika tika = new Tika();
	
	@Before
	public void setUp() {
		String bingingType = ConfParameters.getParm(Constants.CONF_CMIS_BINDING_TYPE);

		String adminName = ConfParameters.getParm(Constants.CONF_CMIS_USERNAME_ADMIN);
		String adminPass = ConfParameters.getParm(Constants.CONF_CMIS_PASSWORD_ADMIN);
		
		// Create cmis sessions
		sessionAdmin = sessionFactory.createCmisSession(adminName, adminPass, bingingType);				
	}
	
	@Test
	public void createTypeInstances() throws IOException
	{
		Folder testFolder = (Folder) sessionAdmin.getObjectByPath("/test");
		String namespace=ConfParameters.getParm(Constants.CONF_NAMESPACE_NAME);
		String docName = "2017D" + new Date().getTime();
		// Create a document instance using type properties and inherited
		// properties
		Map<String, Object> propertiesDoc = new HashMap<String, Object>();
		propertiesDoc.put(PropertyIds.OBJECT_TYPE_ID, "D:"+namespace+":acto-administrativo");
		propertiesDoc.put(PropertyIds.NAME, docName);
		propertiesDoc.put(namespace + ":fecha-emision", new GregorianCalendar(2017, 07, 17));
		propertiesDoc.put(namespace + ":autor", "Pablo Cesar Arango");
		
		byte[] content = "Texto del acto administrativo de Transporte publico".getBytes();
		InputStream stream = new ByteArrayInputStream(content);
		String mimeType = tika.detect(stream);
		// Create the document instance with created folder as parent
		ContentStream contentStream = new ContentStreamImpl(docName, BigInteger.valueOf(content.length), mimeType,
				stream);
		testFolder.createDocument(propertiesDoc, contentStream, VersioningState.MAJOR);
		
		// Download document
		CmisObject objectDoc = sessionAdmin.getObjectByPath("/test/" + docName);
		Assert.assertNotNull(objectDoc);
		Document docDown = (Document) objectDoc;
		stream = docDown.getContentStream().getStream();
		Assert.assertNotNull(objectDoc.getProperty(namespace + ":fecha-emision"));
		Assert.assertNotNull(objectDoc.getProperty(namespace + ":autor"));
		System.out.println(objectDoc.getProperty(namespace + ":fecha-emision"));
		System.out.println(objectDoc.getProperty(namespace + ":autor"));
		
		String theString = IOUtils.toString(stream, "UTF-8");
		Assert.assertTrue(theString.equals("Texto del acto administrativo de Transporte publico"));
		
		//Add secondary type
		ArrayList<String> secondaryTypes = new ArrayList<String>();				
		secondaryTypes.add("P:"+namespace + ":investigacion-transporte-publico");
		
		propertiesDoc = new HashMap<String, Object>();
		propertiesDoc.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, secondaryTypes);
		propertiesDoc.put(namespace + ":empresa", "Taxiexpress");
		
		objectDoc.updateProperties(propertiesDoc);
		
		objectDoc = sessionAdmin.getObjectByPath("/test/" + docName);
		Assert.assertNotNull(objectDoc);
		Assert.assertNotNull(objectDoc.getProperty(namespace + ":empresa"));
		System.out.println(objectDoc.getProperty(namespace + ":empresa"));
	}

}
