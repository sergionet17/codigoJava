package co.gov.movilidadbogota.sipa.cmis.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.DocumentType;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.Ace;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.data.PropertyData;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.apache.chemistry.opencmis.commons.enums.PropertyType;
import org.apache.chemistry.opencmis.commons.enums.UnfileObject;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisRuntimeException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisStorageException;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.AbstractTypeDefinition;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.AccessControlEntryImpl;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.AccessControlPrincipalDataImpl;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.apache.tika.Tika;
import org.apache.tika.io.IOUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.gov.movilidadbogota.sipa.cmis.config.ConfBeans;
import co.gov.movilidadbogota.sipa.cmis.session.CmisSessionFactory;
import co.gov.movilidadbogota.sipa.cmis.type.CmisTypeFactory;
import co.gov.movilidadbogota.sipa.cmis.type.PropertiesTypeFactory;
import co.gov.movilidadbogota.sipa.common.ConfParameters;
import co.gov.movilidadbogota.sipa.common.Constants;

/**
 * Test the CMIS normal operations: ACL, version management, relations,
 * searching (Content and metadata), type creation and instantiation.
 * 
 * @author Hermes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfBeans.class })
public class CmisOperationsTest {

	// Factory classes
	@Autowired
	private CmisSessionFactory sessionFactory;
	@Autowired
	private CmisTypeFactory typeFactory;
	@Autowired
	private PropertiesTypeFactory propertiesTypeFactory;

	// Tika for mime types ;)
	private Tika tika = new Tika();

	// Users used for testing
	private static Session sessionAdmin;

	private Session sessionUser1;

	private Session sessionUser2;

	// Namespace used for the repository to register types and properties.
	private String namespaceName;

	// Testing object types
	private static ObjectType actoAdministrativoDocType;
	private static ObjectType aperturaInvTPDocType;
	private static ObjectType expedienteFolderType;
	private static ObjectType expedienteTPFolderType;
	private static ObjectType empresaSecondaryType;
	private static ObjectType empresaTPSecondaryType;
	private static ObjectType aperturaInvTPTaxisDocType;

	// Sufix used to differentiate the properties used for each type
	private String propertiesSufixAA = "aa";
	private String propertiesSufixAITP = "aitp";
	private String propertiesSufixEX = "ex";
	private String propertiesSufixEXTP = "extp";
	private String propertiesSufixEMP = "emp";
	private String propertiesSufixEMPTP = "emptp";
	private String propertiesSufixAITPT = "aitpt";

	private static boolean typesCreated = true;
	/**
	 * Setup the test parameters and create the document, folder and secondary
	 * types used for testing.
	 */
	@Before
	public void setUp() {
		try {
			String bingingType = ConfParameters.getParm(Constants.CONF_CMIS_BINDING_TYPE);

			String adminName = ConfParameters.getParm(Constants.CONF_CMIS_USERNAME_ADMIN);
			String adminPass = ConfParameters.getParm(Constants.CONF_CMIS_PASSWORD_ADMIN);

			String user1Name = ConfParameters.getParm(Constants.CONF_CMIS_USERNAME_USER1);
			String user1Pass = ConfParameters.getParm(Constants.CONF_CMIS_PASSWORD_USER1);

			String user2Name = ConfParameters.getParm(Constants.CONF_CMIS_USERNAME_USER2);
			String user2Pass = ConfParameters.getParm(Constants.CONF_CMIS_PASSWORD_USER2);

			namespaceName = ConfParameters.getParm(Constants.CONF_NAMESPACE_NAME);

			// Create cmis sessions
			sessionAdmin = sessionFactory.createCmisSession(adminName, adminPass, bingingType);
			sessionUser1 = sessionFactory.createCmisSession(user1Name, user1Pass, bingingType);
			sessionUser2 = sessionFactory.createCmisSession(user2Name, user2Pass, bingingType);

			if(!CmisOperationsTest.typesCreated) {
				// Create the document types used during testing
				createDocumentTypes();
				// Create the folder types used during testing.
				createFolderTypes();
				// Create the secondary types used during testing.
				createSecondaryTypes();
				CmisOperationsTest.typesCreated = true;
			}
		} catch(Throwable t) {
			t.printStackTrace();
		}
				
	}

	/**
	 * Create document types
	 */
	private void createDocumentTypes() {
		String parentType = "cmis:document";
		String typeId = namespaceName + ":actoadministrativo";
		String description = "acto_administrativo";
		AbstractTypeDefinition typeDef = typeFactory.createCmisType(BaseTypeId.CMIS_DOCUMENT.value(), parentType,
				typeId, description);
		actoAdministrativoDocType = createType(typeId, typeDef, propertiesSufixAA);

		parentType = namespaceName + ":actoadministrativo";
		typeId = namespaceName + ":aperturainvtp";
		description = "apertura_investigacion_transporte_publico";
		typeDef = typeFactory.createCmisType(BaseTypeId.CMIS_DOCUMENT.value(), parentType, typeId, description);
		aperturaInvTPDocType = createType(typeId, typeDef, propertiesSufixAITP);
		
		parentType = namespaceName + ":aperturainvtp";
		typeId = namespaceName + ":aperturainvtptaxis";
		description = "apertura_investigacion_transporte_publico_taxis";
		typeDef = typeFactory.createCmisType(BaseTypeId.CMIS_DOCUMENT.value(), parentType, typeId, description);
		aperturaInvTPTaxisDocType = createType(typeId, typeDef, propertiesSufixAITPT);
	}

	/**
	 * Create folder types
	 */
	private void createFolderTypes() {
		String parentType = "cmis:folder";
		String typeId = namespaceName + ":expediente";
		String description = "expediente";
		AbstractTypeDefinition typeDef = typeFactory.createCmisType(BaseTypeId.CMIS_FOLDER.value(), parentType, typeId,
				description);
		expedienteFolderType = createType(typeId, typeDef, propertiesSufixEX);

		parentType = namespaceName + ":expediente";
		typeId = namespaceName + ":expedientetp";
		description = "expediente_transporte_publico";
		typeDef = typeFactory.createCmisType(BaseTypeId.CMIS_FOLDER.value(), parentType, typeId, description);
		expedienteTPFolderType = createType(typeId, typeDef, propertiesSufixEXTP);
	}

	/**
	 * Create secondary types
	 */
	private void createSecondaryTypes() {
		String parentType = "cmis:secondary";
		String typeId = namespaceName + ":empresa";
		String description = "metadata empresa";
		AbstractTypeDefinition typeDef = typeFactory.createCmisType(BaseTypeId.CMIS_SECONDARY.value(), parentType,
				typeId, description);
		empresaSecondaryType = createType(typeId, typeDef, propertiesSufixEMP);

		parentType = namespaceName + ":empresa";
		typeId = namespaceName + ":empresatp";
		description = "metadata_empresa_transporte_publico";
		propertiesSufixEMPTP = "emptp";
		typeDef = typeFactory.createCmisType(BaseTypeId.CMIS_SECONDARY.value(), parentType, typeId, description);
		empresaTPSecondaryType = createType(typeId, typeDef, propertiesSufixEMPTP);
	}

	/**
	 * Create a type
	 * 
	 * @param typeId
	 * @param propertiesSufix
	 * @return
	 */
	private ObjectType createType(String typeId, AbstractTypeDefinition typeDef, String propertiesSufix) {
		createProperties(typeDef, propertiesSufix);

		ObjectType type = null;
		try {
			type = sessionAdmin.createType(typeDef);
			System.out.println("Type created: " + type.toString());
		} catch (Exception e) {
			e.printStackTrace();
			assert false : "An exception was thrown when trying to create a new type definition. Message: "
					+ e.getMessage();
		}

		try {
			type = sessionAdmin.getTypeDefinition(typeId);
			assert type != null : "Unable to retrieve new type. ";
		} catch (Exception e) {
			e.printStackTrace();
			assert false : "Got exception. Cannot get the type definition from the repository. Message: "
					+ e.getMessage();
		}

		return type;
	}

	/**
	 * Create properties of the type definition
	 * 
	 * @param typeDef
	 * @param propertiesSufix
	 */
	private void createProperties(AbstractTypeDefinition typeDef, String propertiesSufix) {
		// build property definitions - string, decimal, boolean, date
		String propId = namespaceName + ":str" + propertiesSufix;
		PropertyDefinition<?> spd = propertiesTypeFactory.createPropertyDefinition(propId, PropertyType.STRING);
		propId = namespaceName + ":dec" + propertiesSufix;
		PropertyDefinition<?> dpd = propertiesTypeFactory.createPropertyDefinition(propId, PropertyType.DECIMAL);
		propId = namespaceName + ":bool" + propertiesSufix;
		PropertyDefinition<?> bpd = propertiesTypeFactory.createPropertyDefinition(propId, PropertyType.BOOLEAN);
		propId = namespaceName + ":date" + propertiesSufix;
		PropertyDefinition<?> fpd = propertiesTypeFactory.createPropertyDefinition(propId, PropertyType.DATETIME);

		typeDef.addPropertyDefinition(spd);
		typeDef.addPropertyDefinition(dpd);
		typeDef.addPropertyDefinition(bpd);
		typeDef.addPropertyDefinition(fpd);
	}
	
	/**
	 * Create the document and folder instances testing the type inheritance
	 * 
	 * @throws IOException
	 */
	@Test
	public void typesInstantiationTest() throws IOException {
		sessionAdmin.clear();
		// Get the root folder
		Folder rootFolder = (Folder) sessionAdmin.getObjectByPath("/");

		String folderName = "2017F" + new Date().getTime();
		// Create a folder instance using type properties and inherited
		// properties
		Map<String, Object> propertiesFolder = new HashMap<String, Object>();
		propertiesFolder = new HashMap<String, Object>();
		propertiesFolder.put(PropertyIds.OBJECT_TYPE_ID, "F:"+namespaceName + ":expedientetp");
		propertiesFolder.put(PropertyIds.NAME, folderName);
		propertiesFolder.put(namespaceName + ":str" + propertiesSufixEXTP, "Expediente");
		propertiesFolder.put(namespaceName + ":date" + propertiesSufixEXTP, new GregorianCalendar(2017, 07, 17));
		propertiesFolder.put(namespaceName + ":bool" + propertiesSufixEXTP, true);
		propertiesFolder.put(namespaceName + ":dec" + propertiesSufixEXTP, 1);
		propertiesFolder.put(namespaceName + ":str" + propertiesSufixEX, "Expediente transporte publico");
		propertiesFolder.put(namespaceName + ":date" + propertiesSufixEX, new GregorianCalendar(2017, 07, 17));
		propertiesFolder.put(namespaceName + ":bool" + propertiesSufixEX, true);
		propertiesFolder.put(namespaceName + ":dec" + propertiesSufixEX, 1);

		// Create the folder instance with root folder as parent
		rootFolder.createFolder(propertiesFolder);

		// Download folder
		CmisObject objectFolder = sessionAdmin.getObjectByPath("/" + folderName);
		Assert.assertNotNull(objectFolder);
		Assert.assertNotNull(objectFolder.getProperty(namespaceName + ":str" + propertiesSufixEXTP));
		Assert.assertNotNull(objectFolder.getProperty(namespaceName + ":date" + propertiesSufixEXTP));
		Assert.assertNotNull(objectFolder.getProperty(namespaceName + ":bool" + propertiesSufixEXTP));
		Assert.assertNotNull(objectFolder.getProperty(namespaceName + ":dec" + propertiesSufixEXTP));
		Assert.assertNotNull(objectFolder.getProperty(namespaceName + ":str" + propertiesSufixEX));
		Assert.assertNotNull(objectFolder.getProperty(namespaceName + ":date" + propertiesSufixEX));
		Assert.assertNotNull(objectFolder.getProperty(namespaceName + ":bool" + propertiesSufixEX));
		Assert.assertNotNull(objectFolder.getProperty(namespaceName + ":dec" + propertiesSufixEX));

		String docName = "2017D" + new Date().getTime();
		// Create a document instance using type properties and inherited
		// properties
		Map<String, Object> propertiesDoc = new HashMap<String, Object>();
		propertiesDoc.put(PropertyIds.OBJECT_TYPE_ID, "D:"+namespaceName + ":aperturainvtptaxis");
		propertiesDoc.put(PropertyIds.NAME, docName);
		propertiesDoc.put(namespaceName + ":str" + propertiesSufixAITPT, "Acto apertura transporte publico de taxis");
		propertiesDoc.put(namespaceName + ":date" + propertiesSufixAITPT, new GregorianCalendar(2017, 07, 17));
		propertiesDoc.put(namespaceName + ":bool" + propertiesSufixAITPT, true);
		propertiesDoc.put(namespaceName + ":dec" + propertiesSufixAITPT, 1);
		propertiesDoc.put(namespaceName + ":str" + propertiesSufixAITP, "Acto apertura transporte publico");
		propertiesDoc.put(namespaceName + ":date" + propertiesSufixAITP, new GregorianCalendar(2017, 07, 17));
		propertiesDoc.put(namespaceName + ":bool" + propertiesSufixAITP, true);
		propertiesDoc.put(namespaceName + ":dec" + propertiesSufixAITP, 1);
		propertiesDoc.put(namespaceName + ":str" + propertiesSufixAA, "Acto administrativo");
		propertiesDoc.put(namespaceName + ":date" + propertiesSufixAA, new GregorianCalendar(2017, 07, 17));
		propertiesDoc.put(namespaceName + ":bool" + propertiesSufixAA, true);
		propertiesDoc.put(namespaceName + ":dec" + propertiesSufixAA, 1);

		byte[] content = "Texto del acto administrativo de Transporte publico".getBytes();
		InputStream stream = new ByteArrayInputStream(content);
		String mimeType = tika.detect(stream);
		// Create the document instance with created folder as parent
		ContentStream contentStream = new ContentStreamImpl(docName, BigInteger.valueOf(content.length), mimeType,
				stream);
		((Folder) objectFolder).createDocument(propertiesDoc, contentStream, VersioningState.MAJOR);

		// Download document
		Document objectDoc = (Document)sessionAdmin.getObjectByPath("/" + folderName + "/" + docName);
		Assert.assertNotNull(objectDoc);
		Document docDown = (Document) objectDoc;
		stream = docDown.getContentStream().getStream();
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":str" + propertiesSufixAITPT));
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":date" + propertiesSufixAITPT));
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":bool" + propertiesSufixAITPT));
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":dec" + propertiesSufixAITPT));
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":str" + propertiesSufixAITP));
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":date" + propertiesSufixAITP));
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":bool" + propertiesSufixAITP));
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":dec" + propertiesSufixAITP));
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":str" + propertiesSufixAA));
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":date" + propertiesSufixAA));
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":bool" + propertiesSufixAA));
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":dec" + propertiesSufixAA));
		String theString = IOUtils.toString(stream, "UTF-8");
		Assert.assertTrue(theString.equals("Texto del acto administrativo de Transporte publico"));
		
		//Add secondary type
		ArrayList<String> secondaryTypes = new ArrayList<String>();
				
		secondaryTypes.add("P:"+namespaceName + ":empresatp");
		propertiesDoc = new HashMap<String, Object>();
		propertiesDoc.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, secondaryTypes);
		propertiesDoc.put(namespaceName + ":str" + propertiesSufixEMP, "propiedad1text");
		propertiesDoc.put(namespaceName + ":str" + propertiesSufixEMPTP, "propiedad1text");

		objectDoc.updateProperties(propertiesDoc);
		
		objectDoc = (Document)sessionAdmin.getObjectByPath("/" + folderName + "/" + docName);
		Assert.assertNotNull(objectDoc);
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":str" + propertiesSufixEMP));
		Assert.assertNotNull(objectDoc.getProperty(namespaceName + ":str" + propertiesSufixEMPTP));

		// Delete the folder and its content elements
		sessionAdmin.deleteTree(objectFolder,true,UnfileObject.DELETE,false);
	}

	/**
	 * Create ACL testing for different users
	 */
	@Test
	public void aclTest() {
		sessionAdmin.clear();
		// Get the root folder
		Folder rootFolder = (Folder) sessionAdmin.getObjectByPath("/");

		String folderName = "2017F" + new Date().getTime();
		// Create a folder instance using type properties and inherited
		// properties
		Map<String, Object> propertiesFolder = new HashMap<String, Object>();
		propertiesFolder.put(PropertyIds.OBJECT_TYPE_ID, "F:"+namespaceName + ":expedientetp");
		propertiesFolder.put(PropertyIds.NAME, folderName);

		// Create the folder instance with root folder as parent
		Folder testFolder = (Folder) rootFolder.createFolder(propertiesFolder);
		
		String folderName2 = "2017F" + new Date().getTime();
		// Create a folder instance using type properties and inherited
		// properties
		propertiesFolder = new HashMap<String, Object>();
		propertiesFolder.put(PropertyIds.OBJECT_TYPE_ID, "F:"+namespaceName + ":expedientetp");
		propertiesFolder.put(PropertyIds.NAME, folderName2);

		// Create the folder instance with root folder as parent
		testFolder.createFolder(propertiesFolder);

		String docName = "2017D" + new Date().getTime();
		// Create a document instance
		Map<String, Object> propertiesDoc = new HashMap<String, Object>();
		propertiesDoc.put(PropertyIds.OBJECT_TYPE_ID, "D:"+namespaceName + ":aperturainvtp");
		propertiesDoc.put(PropertyIds.NAME, docName);

		testFolder.createDocument(propertiesDoc, null, VersioningState.MAJOR);

		// Create the Access control entry (ACE) to the created folder for user1
		AccessControlEntryImpl aceUser1 = new AccessControlEntryImpl();
		String user1 = ConfParameters.getParm(Constants.CONF_CMIS_USERNAME_USER1);
		aceUser1.setPrincipal(new AccessControlPrincipalDataImpl(user1));
		aceUser1.setPermissions(Arrays.asList("cmis:read", "cmis:write"));// is
																			// the
																			// same
																			// as
																			// cmis:all
		aceUser1.setDirect(true);

		// Create the Access control entry (ACE) to the created folder for admin user
		AccessControlEntryImpl aceAdmin = new AccessControlEntryImpl();
		String userAdmin = ConfParameters.getParm(Constants.CONF_CMIS_USERNAME_ADMIN);
		aceAdmin.setPrincipal(new AccessControlPrincipalDataImpl(userAdmin));
		aceAdmin.setPermissions(Arrays.asList("cmis:all"));
		aceAdmin.setDirect(true);

		ArrayList<Ace> acl = new ArrayList<Ace>(Arrays.asList(aceUser1,aceAdmin));
		CmisObject folderObj = sessionAdmin.getObjectByPath("/" + folderName);
		sessionAdmin.setAcl(folderObj, acl);
		Assert.assertNotNull(sessionAdmin.getAcl(folderObj, true));// Check the
																	// ace was
																	// created

		// Test the access cmis:read granted for the user1 
		CmisObject objFolder = sessionUser1.getObjectByPath("/" + folderName + "/" + docName);
		Assert.assertNotNull(objFolder);
		
		// Test the access violation cmis:read for the user2
		objFolder = null;
		try{
			objFolder = sessionUser2.getObjectByPath("/" + folderName + "/" + docName);
		} catch (Exception e) {
		}
		Assert.assertNull(objFolder);
		
		objFolder = null;
		try{
			objFolder = sessionUser2.getObjectByPath("/" + folderName + "/" + folderName2);
		} catch (Exception e) {
		}
		Assert.assertNull(objFolder);

		// Test the user1 document creation
		objFolder = sessionUser1.getObjectByPath("/" + folderName);
		docName = "2017D" + new Date().getTime();
		propertiesDoc = new HashMap<String, Object>();
		propertiesDoc.put(PropertyIds.OBJECT_TYPE_ID, "D:"+namespaceName + ":aperturainvtp");
		propertiesDoc.put(PropertyIds.NAME, docName);


		// Create the Access control entry (ACE) to the created folder for user2
		AccessControlEntryImpl aceUser2 = new AccessControlEntryImpl();
		String user2 = ConfParameters.getParm(Constants.CONF_CMIS_USERNAME_USER2);
		aceUser2.setPrincipal(new AccessControlPrincipalDataImpl(user2));
		aceUser2.setPermissions(Arrays.asList("cmis:read"));
		aceUser2.setDirect(true);

		acl = new ArrayList<Ace>(Arrays.asList(aceUser2));
		folderObj = sessionAdmin.getObjectByPath("/" + folderName);
		sessionAdmin.setAcl(folderObj, acl);
		Assert.assertNotNull(sessionAdmin.getAcl(folderObj, true));// Check the
																	// ace was
																	// created		

		// Test the access violation cmis:write for the user2
		objFolder = sessionUser2.getObjectByPath("/" + folderName);
		docName = "2017D" + new Date().getTime();
		propertiesDoc = new HashMap<String, Object>();
		propertiesDoc.put(PropertyIds.OBJECT_TYPE_ID, "D:"+namespaceName + ":aperturainvtp");
		propertiesDoc.put(PropertyIds.NAME, docName);


		// Delete the folder and its content elements
		sessionAdmin.deleteTree(testFolder,true,UnfileObject.DELETE,false);

	}

	/**
	 * Test the text extraction, index and search.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void searchTest() throws IOException, InterruptedException {
		sessionAdmin.clear();
		Folder folderRoot = (Folder) sessionAdmin.getObjectByPath("/");
		// Create the documents
		ArrayList<String> documents = new ArrayList<String>();
		File[] files = new File("testImages/").listFiles();
		for (File file : files) {
			String docName = "2017D" + new Date().getTime();
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put(PropertyIds.OBJECT_TYPE_ID, "D:"+namespaceName + ":aperturainvtp");
			properties.put(PropertyIds.NAME, docName);
			properties.put(PropertyIds.CONTENT_STREAM_FILE_NAME, docName);
			properties.put(namespaceName + ":str" + propertiesSufixAITP, "Mi documento");

			InputStream fileinputStream = new FileInputStream(file);

			String mimeType = null;
			InputStream is = new FileInputStream(file);
			mimeType = tika.detect(is);
			is.close();

			ContentStream contentStream = new ContentStreamImpl(docName, BigInteger.valueOf(file.length()), mimeType,
					fileinputStream);
			folderRoot.createDocument(properties, contentStream, VersioningState.MAJOR);
			fileinputStream.close();
			
			documents.add(docName);
		}
		Thread.sleep(10000);// Time required to index the documents text

		// Create a contains text query (Full text search)
		String fullTextQuery = "SELECT cmis:objectId FROM " + namespaceName + ":aperturainvtp" + " WHERE CONTAINS('rodilla')";
		
		//String fullTextQuery = "SELECT * FROM [nt:resource] WHERE CONTAINS([jcr:data],'rodilla')";
		ItemIterable<QueryResult> results = sessionAdmin.query(fullTextQuery,false);
		boolean hints = false;
		for (QueryResult hit : results) {
			PropertyData<?> property = hit.getPropertyById("cmis:objectId");
			System.out.println(property);
			hints = true;
		}
		Assert.assertTrue(hints);
		
		fullTextQuery = "SELECT cmis:objectId FROM " + namespaceName + ":aperturainvtp" + " WHERE CONTAINS('adriana')";
		
		//fullTextQuery = "SELECT * FROM [nt:resource] WHERE CONTAINS([jcr:data],'adriana')";
		results = sessionAdmin.query(fullTextQuery,false);
		hints = false;
		for (QueryResult hit : results) {
			PropertyData<?> property = hit.getPropertyById("cmis:objectId");
			System.out.println(property);
			hints = true;
		}
		Assert.assertTrue(hints);

		// Create a metadata query
		String paramQuery = "SELECT cmis:objectId FROM " + namespaceName + ":aperturainvtp" + " WHERE "
				+ namespaceName + ":str" + propertiesSufixAITP + " LIKE '%documento'";
		
		//String paramQuery = "SELECT * FROM ["+namespaceName+":aperturainvtp] WHERE CONTAINS(["+namespaceName+":str"+propertiesSufixAITP+"] , 'documento')";
		results = sessionAdmin.query(paramQuery, false);
		hints = false;
		for (QueryResult hit : results) {
			PropertyData<?> property = hit.getPropertyById("cmis:objectId");
			System.out.println(property);
			hints = true;
		}
		Assert.assertTrue(hints);
		
		for(String doc: documents){
			Document docVersion = (Document) sessionAdmin.getObjectByPath("/" + doc);
			sessionAdmin.delete(docVersion);
		}
			
	}

	/**
	 * Test the document version creation
	 * 
	 * @throws IOException
	 */
	@Test
	public void versionTest() throws IOException {
		sessionAdmin.clear();
		// Get the root folder
		Folder folder = (Folder) sessionAdmin.getObjectByPath("/");

		String docName = "2017D" + new Date().getTime();
		// Create the first version of the document
		Map<String, Object> propertiesDoc = new HashMap<String, Object>();
		propertiesDoc.put(PropertyIds.OBJECT_TYPE_ID,"D:"+ namespaceName + ":aperturainvtp");
		propertiesDoc.put(PropertyIds.NAME, docName);
		propertiesDoc.put(namespaceName + ":str" + propertiesSufixAITP, "Version 1 STR");

		byte[] content = "Version 1 Bin".getBytes();
		InputStream stream = new ByteArrayInputStream(content);
		String mimeType = tika.detect(stream);
		// Create the document instance with root folder as parent
		ContentStream contentStream = new ContentStreamImpl(docName, BigInteger.valueOf(content.length), mimeType,
				stream);
		Document doc1Version = folder.createDocument(propertiesDoc, contentStream, VersioningState.MAJOR);
		Assert.assertNotNull(doc1Version);

		// Check the document is versionable
		DocumentType docType = (DocumentType) doc1Version.getType();
		Assert.assertTrue(docType.isVersionable());

		Document docCheckOut = (Document) sessionAdmin.getObject(doc1Version.checkOut());
		// Create the second version of the document
		propertiesDoc = new HashMap<String, Object>();
		propertiesDoc.put(PropertyIds.OBJECT_TYPE_ID,"D:"+ namespaceName + ":aperturainvtp");
		propertiesDoc.put(PropertyIds.NAME, docName);
		propertiesDoc.put(namespaceName + ":str" + propertiesSufixAITP, "Version 2 STR");

		content = "Version 2 Bin".getBytes();
		stream = new ByteArrayInputStream(content);
		mimeType = tika.detect(stream);
		// Create the document instance with root folder as parent
		contentStream = new ContentStreamImpl(docName, BigInteger.valueOf(content.length), mimeType, stream);

		docCheckOut.checkIn(true, propertiesDoc, contentStream, "My new version comment");

		// Download versions and check it has two versions
		Document docLastVersion = (Document) sessionAdmin.getObjectByPath("/" + docName);
		int checkVersion1 = 0;
		int checkVersion2 = 0;
		Assert.assertTrue(docLastVersion.getAllVersions().size() == 2);
		for (Document versionDoc : docLastVersion.getAllVersions()) {
			stream = versionDoc.getContentStream().getStream();
			String binText = IOUtils.toString(stream, "UTF-8");
			if (binText.equals("Version 1 Bin"))
				checkVersion1++;
			if (binText.equals("Version 2 Bin"))
				checkVersion2++;
			Object propValue = versionDoc.getPropertyValue(namespaceName + ":str" + propertiesSufixAITP);
			if (propValue.equals("Version 1 STR"))
				checkVersion1++;
			if (propValue.equals("Version 2 STR"))
				checkVersion2++;
		}
		Assert.assertTrue(checkVersion1 == 2);
		Assert.assertTrue(checkVersion2 == 2);

		// Delete the document and it´s versions
		sessionAdmin.delete(docLastVersion);
	}
	
	/**
	 * It´s be necessary to model relations into the repository 
	 */
	//@Test
	public void relationshipsTest() {
		// TODO
	}

	@AfterClass
	public static void tearDown() {
		// No eliminamos los tipos porque alfresco deja cosas pegadas al tipo, y por lo tanto este no se puede
		// eliminar.
		/*
		if(aperturaInvTPTaxisDocType != null)sessionAdmin.deleteType(aperturaInvTPTaxisDocType.getId());
		if(aperturaInvTPDocType != null)sessionAdmin.deleteType(aperturaInvTPDocType.getId());
		if(actoAdministrativoDocType != null)sessionAdmin.deleteType(actoAdministrativoDocType.getId());
		if(expedienteTPFolderType != null)sessionAdmin.deleteType(expedienteTPFolderType.getId());
		if(expedienteFolderType != null)sessionAdmin.deleteType(expedienteFolderType.getId());
		if(empresaTPSecondaryType != null)sessionAdmin.deleteType(empresaTPSecondaryType.getId());		
		if(empresaSecondaryType != null)sessionAdmin.deleteType(empresaSecondaryType.getId());		
		*/
	}

}