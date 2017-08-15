package co.gov.movilidadbogota.sipa.util;

import co.gov.movilidadbogota.sipa.ctrl.InconsistenciasController;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Diego Gomez on 22/06/2017.
 */
public class GeneradorCSV {

    //Metodo el cual construye un StringBuilder y retorna los bytes
    public <T> byte[] ConstructorFileCSV(List<T> c, InconsistenciasController.StringArrayMapper<T> stringArrayMapper) {
        StringBuilder sb = new StringBuilder();
        String ln = System.getProperty("line.separator");
        for (T t : c) {
            sb.append(String.join(",", stringArrayMapper.build(t)));
            sb.append(ln);
        }
        return sb.toString().getBytes();
    }


    //Metodo el cual permite descargar un archivo.
    public void descargarArchivo(File fichero, HttpServletResponse response) throws IOException {
        try {
            DefaultResourceLoader loader = new DefaultResourceLoader();
            InputStream is = new FileInputStream(fichero);
            IOUtils.copy(is, response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment; filename=" + fichero.getName() + "");
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

}
