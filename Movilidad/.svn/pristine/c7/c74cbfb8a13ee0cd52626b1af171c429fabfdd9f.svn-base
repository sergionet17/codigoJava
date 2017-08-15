package co.gov.movilidadbogota.sipa.xdoc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class GenerateXMLFields {

  public static void main(String[] args) throws XDocReportException,IOException {
    
    // 1) Create FieldsMetadata by setting Velocity as template engine
    FieldsMetadata fieldsMetadata = new FieldsMetadata(TemplateEngineKind.Velocity.name());
    // 2) Load fields metadata from Java Class
    fieldsMetadata.load("usuario",Usuario.class);
    // 3) Generate XML fields in the file "project.fields.xml".
    // Extension *.fields.xml is very important to use it with MS Macro XDocReport.dotm
    // FieldsMetadata#saveXML is called with true to indent the XML.
    File xmlFieldsFile = new File("project.fields.xml");
    fieldsMetadata.saveXML(new FileOutputStream(xmlFieldsFile), true);
  }
}
