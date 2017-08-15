package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.cli.SipaServicesClient;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class DocumentoController extends BaseController {

    public static final String CONTROLLER_PATH = "/documentos";

    @Autowired
    SipaServicesClient sipaServicesClient;

    @GetMapping(CONTROLLER_PATH + "/{id}")
    public void download(@PathVariable("id") UUID id, HttpServletRequest request, HttpServletResponse response) {

        Documento doc = null;
        try {
            doc = sipaServicesClient.getDocumentos().getDocumento(id);
        } catch (DocumentoException e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            try {
                IOUtils.write(e.getMessage().getBytes(), response.getOutputStream());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (doc == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } else {
            response.setContentType(doc.getContentType());
            response.addHeader("Content-Disposition", "attachment; filename=\"" + doc.getOriginalName() + "\"");
            response.setStatus(HttpStatus.OK.value());
            try {
                byte[] bytes = sipaServicesClient.getDocumentos().getContenidoDocumento(doc.getId());
                IOUtils.write(bytes, response.getOutputStream());
                response.setContentLength(bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentoException e) {
                e.printStackTrace();
            }
        }

    }

}
