package co.gov.movilidadbogota.sipa.xdoc;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class GenerateDocxReport {

	public static void main(String[] args) throws IOException,
			XDocReportException {
		// 1) Load Docx file by filling Velocity template engine and cache
		// it to the registry
		InputStream in = GenerateDocxReport.class
				.getResourceAsStream("sancion_reincidencia.docx");
		 
                
                 IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,
				TemplateEngineKind.Velocity);
                                
		// 2) Create fields metadata to manage lazy loop (#forech velocity)
		// for table row.
		// 1) Create FieldsMetadata by setting Velocity as template engine
		FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
		// 2) Load fields metadata from Java Class
		fieldsMetadata.load("usuario", Usuario.class);
                
		// 3) Create context Java model
		IContext context = report.createContext();
                //Datos de prueba estos deben ir en el constructor de la clase
		//Usuario usuario = 
                //            new Usuario("111111", "23", "marzo", "17", "Andres Lozano", "10234567142");
                
		//context.put("usuario", usuario);
                
                String archivoSalida = "ID";
                String extension = ".docx";
                String archivo = archivoSalida.concat(extension);
		// 4) Generate report by merging Java model with the Docx
		OutputStream out = new FileOutputStream(new File(archivo));
		report.process(context, out);

	}

}