package co.gov.movilidadbogota.sipa.extract.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ocr.TesseractOCRParser;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import co.gov.movilidadbogota.sipa.common.ConfParameters;
import co.gov.movilidadbogota.sipa.common.Constants;

@Component("textExtractor")
@Scope("singleton")
public class TextExtractor {
	
	// Tika for mime types ;)
	private Tika tika = new Tika();
	
	/**
	 * The MIME types that are excluded by default. Currently, this list
	 * consists of:
	 * <ul>
	 * <li>application/x-archive</li>
	 * <li>application/x-bzip</li>
	 * <li>application/x-bzip2</li>
	 * <li>application/x-cpio</li>
	 * <li>application/x-gtar</li>
	 * <li>application/x-gzip</li>
	 * <li>application/x-tar</li>
	 * <li>application/zip</li>
	 * <li>application/vnd.teiid.vdb</li>
	 * <li>image/*</li>
	 * <li>audio/*</li>
	 * <li>video/*</li>
	 * </ul>
	 */
	private static final Set<MediaType> DEFAULT_EXCLUDED_MIME_TYPES = new HashSet<MediaType>(
			Arrays.asList(MediaType.application("x-archive"), MediaType.application("x-bzip"),
					MediaType.application("x-bzip2"), MediaType.application("x-cpio"), MediaType.application("x-gtar"),
					MediaType.application("x-gzip"), MediaType.application("x-tar"), MediaType.application("zip"),
					MediaType.application("vnd.teiid.vdb"), MediaType.audio("*"), MediaType.video("*")));
	
	private Integer writeLimit;
	
	public TextExtractor() {		
		this.init();		
	}

	private void init() {
		writeLimit = Integer.parseInt(ConfParameters.getParm(Constants.CONF_EXTRACT_WRITE_LIMIT));
	}

	/**
	 * Extract the first writeLimit characters to the given ByteArrayInputStream.
	 * 
	 * @param byteStream
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public String extractText(ByteArrayInputStream byteStream) throws IOException, SAXException, TikaException{
		String mimeType = tika.detect(byteStream);
		if(!this.supportsMimeType(mimeType))
			return null;
		Metadata metadata = prepareMetadata(mimeType);
		BodyContentHandler handler = (writeLimit == -1) ? new BodyContentHandler()
				: new BodyContentHandler(writeLimit + 1);
		this.extract(byteStream, metadata, handler);
		String text = handler.toString();
		if (!text.isEmpty()) {
			text = Normalizer.normalize(text, Form.NFD);
			text = text.replaceAll("[^\\p{Alpha}\\p{Digit}\\p{Space}\\p{InCombiningDiacriticalMarks}]", "");
			return text;
		}
		return null;
		
	}

	/**
	 * Creates a new tika metadata object used by the parser. This will contain
	 * the mime-type of the content being parsed.
	 * 
	 */
	private final Metadata prepareMetadata(String mimeType){
		Metadata metadata = new Metadata();
		metadata.set(Metadata.CONTENT_TYPE, mimeType);		
		return metadata;
	}

	/**
	 * The easiest text extractor ever.
	 * 
	 * @param byteStream
	 * @param metadata
	 * @param handler
	 * @throws TikaException 
	 * @throws SAXException 
	 * @throws IOException 
	 */
	private void extract(ByteArrayInputStream byteStream, Metadata metadata, BodyContentHandler handler) throws IOException, SAXException, TikaException {		
		PDFParserConfig pdfConfig = new PDFParserConfig();
		pdfConfig.setExtractInlineImages(true);
		pdfConfig.setExtractUniqueInlineImagesOnly(false);

		ParseContext context = new ParseContext();
		context.set(Parser.class, new TesseractOCRParser());//Parser for text extraction from images
		context.set(PDFParserConfig.class, pdfConfig);//Parser for pdf documents with images inside it.

		AutoDetectParser parser = new AutoDetectParser();
		try{
		parser.parse(byteStream, handler, metadata, context);}
		catch(Exception e){}//When the write limit is reached an exception is thrown but the handler contains the readed characters.
	}
	
	/**
	 * Check the mime type is supported for text extraction
	 * 
	 * @param mimeType
	 * @return
	 */
	public boolean supportsMimeType(String mimeType) {
		MediaType mediaType = MediaType.parse(mimeType);
		if (mediaType == null) {
			return false;
		}
		for (MediaType excludedMediaType : DEFAULT_EXCLUDED_MIME_TYPES) {
			if (excludedMediaType.equals(mediaType))
				return false;
			if (excludedMediaType.getSubtype().equalsIgnoreCase("*")
					&& mediaType.getType().equalsIgnoreCase(excludedMediaType.getType()))
				return false;

		}
		return true;
	}


}
