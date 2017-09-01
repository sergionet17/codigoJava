package co.gov.movilidadbogota.sipa.cmis.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.apache.chemistry.opencmis.commons.enums.PropertyType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisBaseException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.AbstractTypeDefinition;
import org.junit.After;
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
 * Test concurrency 
 * 
 * @author Hermes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfBeans.class })
public class ConcurrencyTestClient {

	@Autowired
	private CmisSessionFactory sessionFactory;
	@Autowired
	private CmisTypeFactory typeFactory;
	@Autowired
	private PropertiesTypeFactory propertiesTypeFactory;
	
	// Testing object types
	private ObjectType actoAdministrativoDocType;
	// Type used to create type for testing
	private Session sessionAdmin;
		
	private static String TEST_CONTENT_PREFIX = "This is a test document, iteration: ";
	
	private String folderParent;
	private int folderSubfolderCount;
	private int documentsCount;
	private int incrementsCount;
	private Client[] clients;
	
	// Namespace used for the repository to register types and properties.
	private String namespaceName;
	
	
	@Before
	public void setUp() throws Exception {
		// Create cmis sessions
		String bingingType = ConfParameters.getParm(Constants.CONF_CMIS_BINDING_TYPE);
		String adminName = ConfParameters.getParm(Constants.CONF_CMIS_USERNAME_ADMIN);
		String adminPass = ConfParameters.getParm(Constants.CONF_CMIS_PASSWORD_ADMIN);
		namespaceName = ConfParameters.getParm(Constants.CONF_NAMESPACE_NAME);		
		sessionAdmin = sessionFactory.createCmisSession(adminName, adminPass, bingingType);
		
		//Create type for test
		actoAdministrativoDocType = this.createType();
		Assert.assertNotNull(actoAdministrativoDocType);
		
		//Concurrency test params
		int testClientCount = Integer.parseInt(ConfParameters.getParm(Constants.CONF_CMIS_CLIENT_COUNT));		
		folderParent = ConfParameters.getParm(Constants.CONF_CMIS_FOLDER_PARENT);
		folderSubfolderCount = Integer.parseInt(ConfParameters.getParm(Constants.CONF_CMIS_SUBFOLDER_COUNT));
		documentsCount = Integer.parseInt(ConfParameters.getParm(Constants.CONF_CMIS_DOCUMENTS_COUNT));		
		incrementsCount = Integer.parseInt(ConfParameters.getParm(Constants.CONF_CMIS_INCREMENTS_COUNT));
		
		System.out.println("Concurrency test initializing, with " + testClientCount
				+ " concurrent clients spread across " + folderSubfolderCount + " folders ('" + folderParent
				+ "/test-concurrency/test-subfolder-*') on " + ConfParameters.getParm(Constants.CONF_CMIS_URL) + " server "
				+ ", each client performing " + incrementsCount + 				
				+ documentsCount + " documents (versioning=' none ')...");
		//Create the clients for concurrency testing
		clients = new Client[testClientCount];
		for (int clientIndex = 0; clientIndex < testClientCount; clientIndex++) {
			clients[clientIndex] = new Client(clientIndex + 1);
		}			
	}
		
	
	/**
	 * Create a type
	 * 
	 */
	private ObjectType createType() {				
		String parentType = "cmis:document";
		String typeId = "D:" + namespaceName + ":"+ ConfParameters.getParm(Constants.CONF_CMIS_TYPE_NAME);
		String description = "prueba";
		AbstractTypeDefinition typeDef = typeFactory.createCmisType(BaseTypeId.CMIS_DOCUMENT.value(), parentType,
				typeId, description);
		
		String propId = namespaceName + ":increment";
		PropertyDefinition<?> spd = propertiesTypeFactory.createPropertyDefinition(propId, PropertyType.STRING);
		typeDef.addPropertyDefinition(spd);

		ObjectType type = null;
		type = sessionAdmin.createType(typeDef);
		return type;
	}
	
	/**
	 * Get the child folder 
	 * 
	 * @param session
	 * @param parentFolder
	 * @param name
	 * @return
	 * @throws CmisBaseException
	 */
	public Folder getChildFolder(Session session, 
			Folder parentFolder, String name) throws CmisBaseException {
		try {
			return (Folder) session.getObjectByPath(parentFolder.getPath() + '/' + name);
		} catch (Exception confe) {
		}
		Map<String, String> folderProps = new HashMap<String, String>();
		folderProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
		folderProps.put(PropertyIds.NAME, name);
		return parentFolder.createFolder(folderProps);
	}

	/**
	 * Create the content stream for the documents
	 * 
	 * @param session
	 * @param fileName
	 * @param content
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public ContentStream createContentStream(Session session, String fileName,
			String content) throws UnsupportedEncodingException {
		byte[] contentBytes = content.getBytes("UTF-8");
		return session.getObjectFactory().createContentStream(fileName, contentBytes.length,
				"text/plain", new ByteArrayInputStream(contentBytes));
	}

	/**
	 * Read the content stream string content
	 * 
	 * @param source
	 * @return
	 * @throws IOException
	 */
	public String readString(Reader source) throws IOException {
		StringBuffer stringBuffer = new StringBuffer(10240);
		char[] buffer = new char[10240];
		int bytesRead = -1;
		while ((bytesRead = source.read(buffer)) > 0) {
			stringBuffer.append(buffer, 0, bytesRead);
		}
		return stringBuffer.toString();
	}

	@Test
	public void cmisConcurrencyTest() throws Exception {
		System.err.println("Concurrency test starting...");
		
		//Create the future tasks for the repo clients
		ArrayList<FutureTask<Boolean>> clientTasks = new ArrayList<FutureTask<Boolean>>(clients.length);
		for (int clientIndex = 0; clientIndex < clients.length; clientIndex++) {
			clientTasks.add(clientIndex, new FutureTask<Boolean>(clients[clientIndex]));
		}
		//Start the threads 
		ExecutorService executorService = Executors.newCachedThreadPool();
		long testStartTime = System.currentTimeMillis();
		for (int taskIndex = 0; taskIndex < clientTasks.size(); taskIndex++) {
			executorService.execute(clientTasks.get(taskIndex));
		}

		boolean done = false;
		while (!done) { // Loop until all tasks are done.

			boolean foundRunningTask = false;
			for (int taskIndex = 0; taskIndex < clientTasks.size(); taskIndex++) {

				if (!clientTasks.get(taskIndex).isDone()) {
					foundRunningTask = true;
					continue;
				}
				clientTasks.get(taskIndex).get(); 			
			}
			if (!foundRunningTask) {
				done = true;
			} else {
				Thread.sleep(250); 
			}

		} // while (!done)

		long testEndTime = System.currentTimeMillis();

		executorService.shutdown();

		System.out.println("Concurrency test completed, with " + clients.length + " concurrent clients spread across "
				+ folderSubfolderCount + " folders on " + ConfParameters.getParm(Constants.CONF_CMIS_URL) + " server "
				+ " running '"
				+ clients[0].session.getRepositoryInfo().getProductName() + "' version '"
				+ clients[0].session.getRepositoryInfo().getProductVersion() + "', each client performing "		
				+ "explicitly versioned) to each of " + documentsCount + " documents "
				+ " in a total of "
				+ Math.round((testEndTime - testStartTime) / 1000d) + " seconds (average "
				+ String.format("%.1f", Double.valueOf(((testEndTime - testStartTime) / documentsCount) / 1000d))
				+ " seconds/document).");
		
	}

	/**
	 * The repo client.
	 * 
	 * @author Hermes
	 *
	 */
	public class Client implements Callable<Boolean> {
		protected int clientNumber;
		protected String clientUUID = UUID.randomUUID().toString();
		protected Session session;
		protected Folder clientFolder;

		public Client(int clientNumber) throws Exception {
			this.clientNumber = clientNumber;

			System.err.println("Client " + String.format("%03d", Integer.valueOf(clientNumber)) + ": UUID " + clientUUID
					+ ": Initializing...");
			
			String adminName = ConfParameters.getParm(Constants.CONF_CMIS_USERNAME_ADMIN);
			String adminPass = ConfParameters.getParm(Constants.CONF_CMIS_PASSWORD_ADMIN);
			String bingingType = ConfParameters.getParm(Constants.CONF_CMIS_BINDING_TYPE);

			session = sessionFactory.createCmisSession(adminName, adminPass, bingingType);
			Folder testFolderParent = null;
			try{
				testFolderParent = (Folder) session.getObjectByPath("/"+folderParent);
			}
			catch(CmisObjectNotFoundException e)
			{
				//La carpeta no existe: Crearla
				Map<String, String> folderProps = new HashMap<String, String>();
				folderProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
				folderProps.put(PropertyIds.NAME, folderParent);
				testFolderParent = ((Folder) session.getObjectByPath("/")).createFolder(folderProps);
			}
			Folder testFolder = getChildFolder(session, testFolderParent,
					"test-concurrency");
			clientFolder = getChildFolder(session, testFolder,
					"test-subfolder-" + String.valueOf(((clientNumber - 1) % folderSubfolderCount) + 1));

			return;
		}

		/**
		 * Create the document
		 * 
		 * @param session
		 * @param clientFolder
		 * @param documentNumber
		 * @return
		 * @throws UnsupportedEncodingException
		 * @throws InterruptedException
		 */
		protected Document createTestDocument(Session session, Folder clientFolder,
				int documentNumber) throws UnsupportedEncodingException, InterruptedException {
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put(PropertyIds.OBJECT_TYPE_ID, "D:" + namespaceName + ":"+ConfParameters.getParm(Constants.CONF_CMIS_TYPE_NAME));
			properties.put(PropertyIds.NAME, "client-" + clientUUID + "-doc-" + String.valueOf(documentNumber));
			properties.put(namespaceName + ":increment", "client-" + clientUUID + "-doc-" + String.valueOf(documentNumber)+ "-incr-0");
					
			ContentStream contentStream = createContentStream(session,
					properties.get(namespaceName + ":increment").toString(), TEST_CONTENT_PREFIX + String.valueOf(0));
			Document testDocument = clientFolder.createDocument(properties, contentStream, VersioningState.MAJOR);
			
			return testDocument;
		}

		/**
		 * Check if the changes done to the documents had effect.
		 * 
		 * @param testDocument
		 * @param documentNumber
		 * @param expectedIncrement
		 * @throws IllegalStateException
		 * @throws IOException
		 * @throws UnsupportedEncodingException
		 * @throws InterruptedException
		 */
		protected void verifyDocument(Document testDocument, int documentNumber,
				int expectedIncrement)
				throws IllegalStateException, IOException, UnsupportedEncodingException, InterruptedException {
			String expectedName = "client-" + clientUUID + "-doc-" + String.valueOf(documentNumber) + "-incr-"
					+ String.valueOf(expectedIncrement) + "";
			String name = testDocument.getPropertyValue(namespaceName + ":increment");
			if (!expectedName.equals(name))
				throw new IllegalStateException(
						"Mismatched Name! Expected '" + expectedName + "', got '" + name + "'.");
			String expectedContent = TEST_CONTENT_PREFIX + String.valueOf(expectedIncrement);
			String content = readString(
					new InputStreamReader(testDocument.getContentStream().getStream(), "UTF-8"));
			if (!expectedContent.equals(content))
				throw new IllegalStateException(
						"Mismatched Content! Expected '" + expectedContent + "', got '" + content + "'.");
			return;
		}

		/**
		 * Change the document property and input stream
		 * 
		 * @param testDocument
		 * @param documentNumber
		 * @param newIncrement
		 * @param session
		 * @return
		 * @throws UnsupportedEncodingException
		 * @throws InterruptedException
		 */
		protected ObjectId incrementDocument(Document testDocument, int documentNumber, int newIncrement,
				Session session)
				throws UnsupportedEncodingException, InterruptedException {
			
			Document newDocument = (Document) session.getObject(testDocument.checkOut());
			
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put(namespaceName + ":increment", "client-" + clientUUID + "-doc-" + String.valueOf(documentNumber)
						+ "-incr-" + String.valueOf(newIncrement));
			ContentStream contentStream = createContentStream(session,
					properties.get(namespaceName + ":increment").toString(), TEST_CONTENT_PREFIX + String.valueOf(newIncrement));
								
			ObjectId nId = newDocument.checkIn(true, properties, contentStream, "My new version comment");
			
			//return newDocument;
			return nId;
		}

		/* (non-Javadoc)
		 * @see java.util.concurrent.Callable#call()
		 */
		@Override
		public Boolean call() throws Exception {			
			for (int documentNumber = 1; documentNumber <= documentsCount; documentNumber++) {
				long docStartTime = System.currentTimeMillis();
				//The client create the document
				ObjectId testDocumentId = createTestDocument(session, clientFolder, documentNumber);
				
				for (int increment = 0; increment <incrementsCount; increment++) {
					Document testDocument = (Document) session.getObject(testDocumentId);
					//check the last update for the document
					verifyDocument(testDocument, documentNumber, increment);
					//update the document
					testDocumentId = incrementDocument(testDocument, documentNumber, increment + 1,
							session);

				}
				((Document) session.getObject(testDocumentId))
						.delete(true);
				
				long docEndTime = System.currentTimeMillis();
				System.err.println("Client " + String.format("%03d", Integer.valueOf(clientNumber)) + ": Document "
						+ String.format("%02d", Integer.valueOf(documentNumber)) + ": Completed in "
						+ String.format("%.1f", Double.valueOf((docEndTime - docStartTime) / 1000d)) + " seconds.");
			}

			System.err.println("Client " + String.format("%03d", Integer.valueOf(clientNumber)) + ": Complete.");
			System.err.flush();
			return Boolean.TRUE;
			
		}

	} // Client
	
	@After
	public void tearDown() {
		//if(actoAdministrativoDocType != null)sessionAdmin.deleteType(actoAdministrativoDocType.getId());
	}

}
