package co.gov.movilidadbogota.sipa.repo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.gov.movilidadbogota.sipa.common.IoUtil;
import co.gov.movilidadbogota.sipa.mongo.config.MongoConfiguration;
import co.gov.movilidadbogota.sipa.repo.service.RepoBinService;
import co.gov.movilidadbogota.sipa.repo.streams.BinDocInputStream;
import co.gov.movilidadbogota.sipa.repo.streams.BinDocOutputStream;

/**
 * Test the documents insertion with different sizes into the mongodb
 * repository.
 * 
 * @author Hermes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfiguration.class })
public class ChunkTest {

	@Autowired
	private RepoBinService repoBinService;

	@Before
	public void setUp() {
		removeFiles();
	}

	/**
	 * Delete the output folder documents
	 */
	private static void removeFiles() {
		File[] filesOutput = new File("output/").listFiles();
		for (File file : filesOutput)
			file.delete();
	}

	@Test
	public void insertAndReadDocumentsChunkSize1MBTest() throws IOException {
		this.insertWithChunkSize(1 * 1024 * 1000);//1MB chunk size
	}
	
	@Test
	public void insertAndReadDocumentsChunkSize2MBTest() throws IOException {
		this.insertWithChunkSize(2 * 1024 * 1000);//2MB chunk size
	}

	@Test
	public void insertAndReadDocumentsChunkSize5MBTest() throws IOException {
		this.insertWithChunkSize(5 * 1024 * 1000);//5MB chunk size
	}

	@Test
	public void insertAndReadDocumentsChunkSize10MBTest() throws IOException {
		this.insertWithChunkSize(10 * 1024 * 1000);//10MB chunk size
	}

	@Test
	public void insertAndReadDocumentsChunkSize15MBTest() throws IOException {
		this.insertWithChunkSize(15 * 1024 * 1000);//15MB chunk size
	}
	
	/**
	 * Insert a document into the repository with the specified chunk size.
	 * 
	 * @param chunkSize
	 * @throws IOException
	 */
	private void insertWithChunkSize(long chunkSize) throws IOException {
		File[] filesInput = new File("input/").listFiles();
		for (File fileIn : filesInput) {
			FileInputStream fileInputStream = new FileInputStream(fileIn);
			//Insert the document into the repository as a output stream
			BinDocOutputStream mongoOutput = new BinDocOutputStream(fileIn.getName(), fileIn.length(), chunkSize, repoBinService);
			IoUtil.write(fileInputStream, mongoOutput);
			mongoOutput.persist();			
			//Read the document from the repository
			BinDocInputStream inputMongo = new BinDocInputStream(fileIn.getName(), repoBinService);
			//Write the document into the output folder
			FileOutputStream fileOutputStream = new FileOutputStream(new File("output/"+fileIn.getName()));			
			IoUtil.write(inputMongo, fileOutputStream);			
			//Check the output folder document is equal to the input folder document
			File fileOut = new File("output/"+fileIn.getName());			
			Assert.assertTrue(FileUtils.contentEquals(fileOut, fileIn));
			//Remove the document from the repository
			repoBinService.removeRepoBinDocument(fileIn.getName());
		}
	}
	
	@AfterClass
	public static void tearDown() {
		removeFiles();
	}
}
