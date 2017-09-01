package co.gov.movilidadbogota.sipa.extract.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tika.exception.TikaException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import co.gov.movilidadbogota.sipa.extract.config.ConfBeans;
import co.gov.movilidadbogota.sipa.extract.service.TextExtractor;

/**
 * Text extraction testing for different document mime-types.
 * 
 * @author Hermes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfBeans.class })
public class TextExtractionTest {
	
	@Autowired
	private TextExtractor textExtractor;
	
	
	@Test
	public void extractFromMSWordWithImageTest() throws IOException, SAXException, TikaException{
		Path path = Paths.get("docs/holamundo2.docx");
		byte[] data = Files.readAllBytes(path);
		ByteArrayInputStream fileInputStream = new ByteArrayInputStream(data);
		String textExtracted = textExtractor.extractText(fileInputStream);
		Assert.assertTrue(textExtracted.contains("Hola mundo"));
		Assert.assertTrue(textExtracted.contains("HERMES"));
		System.out.println(textExtracted);
	}
	
	@Test
	public void extractFromTiffTest() throws IOException, SAXException, TikaException{
		Path path = Paths.get("docs/cita.tif");
		byte[] data = Files.readAllBytes(path);
		ByteArrayInputStream fileInputStream = new ByteArrayInputStream(data);
		String textExtracted = textExtractor.extractText(fileInputStream);
		Assert.assertTrue(textExtracted.contains("Viernes"));	
		System.out.println(textExtracted);
	}
	
	@Test
	public void extractFromTxtTest() throws IOException, SAXException, TikaException{
		Path path = Paths.get("docs/pendientes reuniones agendar.txt");
		byte[] data = Files.readAllBytes(path);
		ByteArrayInputStream fileInputStream = new ByteArrayInputStream(data);
		String textExtracted = textExtractor.extractText(fileInputStream);
		Assert.assertTrue(textExtracted.contains("adriana"));	
		System.out.println(textExtracted);
	}
	
	@Test
	public void extractFromPdfWithImageTest() throws IOException, SAXException, TikaException{
		Path path = Paths.get("docs/holamundo2.pdf");
		byte[] data = Files.readAllBytes(path);
		ByteArrayInputStream fileInputStream = new ByteArrayInputStream(data);
		String textExtracted = textExtractor.extractText(fileInputStream);
		Assert.assertTrue(textExtracted.contains("Hola mundo"));
		Assert.assertTrue(textExtracted.contains("HERMES"));	
		System.out.println(textExtracted);
	}
	
	@Test
	public void extractFromJpgTest() throws IOException, SAXException, TikaException{
		Path path = Paths.get("docs/sdm.jpg");
		byte[] data = Files.readAllBytes(path);
		ByteArrayInputStream fileInputStream = new ByteArrayInputStream(data);
		String textExtracted = textExtractor.extractText(fileInputStream);
		Assert.assertTrue(textExtracted.contains("vehiculo"));	
		System.out.println(textExtracted);
	}
	
	@Test
	public void extractFromPdfTest() throws IOException, SAXException, TikaException{
		Path path = Paths.get("docs/Erich Gamma-Design Patterns.pdf");
		byte[] data = Files.readAllBytes(path);
		ByteArrayInputStream fileInputStream = new ByteArrayInputStream(data);
		String textExtracted = textExtractor.extractText(fileInputStream);
		Assert.assertTrue(textExtracted.contains("Erich Gamma"));
		System.out.println(textExtracted);
	}
	
	@Test
	public void extractFromMSWord() throws IOException, SAXException, TikaException{
		Path path = Paths.get("docs/Tramites Virtuales.docx");
		byte[] data = Files.readAllBytes(path);
		ByteArrayInputStream fileInputStream = new ByteArrayInputStream(data);
		String textExtracted = textExtractor.extractText(fileInputStream);
		Assert.assertTrue(textExtracted.contains("Plan de trabajo y Cronograma"));	
		System.out.println(textExtracted);
	}

	
}
