package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.fin.*;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import co.gov.movilidadbogota.sipa.util.SecurityHelper;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * Created by acpreda on 7/13/17.
 */
@Controller
public class FinancieroController extends BaseController {

    public static final String PATH = "/financiero";
    public static final String PATH_PAGOS = PATH + "/pagos";
    public static final String PATH_NUEVO_PAGO = PATH_PAGOS + "/nuevo";
    public static final String PATH_PAGOS_NO_APLICADOS = PATH_PAGOS + "/noaplicados";

    @Autowired
    ArchivoPagosRepo archivoPagosRepo;

    @Autowired
    TipoArchivoPagosRepo tipoArchivoPagosRepo;

    @Autowired
    PagoRepo pagoRepo;

    @Autowired
    SecurityHelper securityHelper;

    @GetMapping(PATH_PAGOS)
    public String pagos(Model model) {

        model.addAttribute(
                "pagos",
                archivoPagosRepo.findAll(new Sort(Sort.Direction.DESC, "fecha"))
        );

        return "financiero-pagos";
    }

    @GetMapping(PATH_PAGOS + "/{id}")
    public String pagosDetalle(@PathVariable("id") UUID id, Model model) {
        ArchivoPagos archivoPagos = archivoPagosRepo.findOne(id);
        archivoPagos.setPagos(pagoRepo.findByArchivoPagos(archivoPagos));
        model.addAttribute("pago", archivoPagos);
        return "financiero-pagos-detalle";
    }

    @GetMapping(PATH_NUEVO_PAGO)
    public String pagosNuevo(Model model) {
        model.addAttribute("entity", new ArchivoPagos());
        return "financiero-pagos-nuevo";
    }

    @PostMapping(PATH_PAGOS)
    public String pagosPost(
            @Valid ArchivoPagos archivoPagos,
            BindingResult bindingResult,
            @RequestParam("archivo") MultipartFile archivo,
            Model model,
            RedirectAttributes redirectAttributes
    ) {

//        if (archivo == null || archivo.isEmpty()) {
//            bindingResult.rejectValue(
//                    "contenido",
//                    "financiero.pagos.archivo.empty",
//                    "Debe seleccionar el archivo con los pagos"
//            );
//        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("entity", archivoPagos);
            return "financiero-pagos-nuevo";
        }

        try {
            CSVReader reader = new CSVReader(new InputStreamReader(archivo.getInputStream()));
            archivoPagos.setPagos(new ArrayList<>());
            String[] line;
            while ((line = reader.readNext()) != null) {
                Pago pago = new Pago();
                pago.setEstadoPago(EstadoPago.NO_APLICADO);
                pago.setFecha(Constants.DATE_FORMAT.parse(line[0]));
                pago.setId(UUID.randomUUID());
                pago.setValor(new BigDecimal(line[1]));
                pago.setReferencia(line[2]);
                pagoRepo.save(pago);
                archivoPagos.getPagos().add(pago);
            }

            archivoPagos.setCargados(0);
            archivoPagos.setRechazados(0);
            BigDecimal valorCargado = BigDecimal.ZERO;
            BigDecimal valorTotal = BigDecimal.ZERO;
            for(Pago x : archivoPagos.getPagos()) {
                if(x.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                    x.setEstadoPago(EstadoPago.NO_APLICADO);
                    archivoPagos.setRechazados(archivoPagos.getRechazados() + 1);
                } else {
                    x.setEstadoPago(EstadoPago.APLICADO);
                    archivoPagos.setCargados(archivoPagos.getCargados() + 1);
                    valorCargado = valorCargado.add(x.getValor());
                }
                valorTotal = valorTotal.add(x.getValor());
            }
            archivoPagos.setValorCargado(valorCargado);
            archivoPagos.setValorTotal(valorTotal);

            archivoPagos.setId(UUID.randomUUID());
            archivoPagos.setFecha(new Date());
            archivoPagos.setUsuario(securityHelper.getUsuario(Usuario.class));
            archivoPagosRepo.save(archivoPagos);
            for(Pago x : archivoPagos.getPagos()) {
                x.setArchivoPagos(archivoPagos);
                pagoRepo.save(x);
            }

            return String.format("redirect:%s/%s", PATH_PAGOS, archivoPagos.getId());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("redirect:%s", PATH_PAGOS);
    }

    @ModelAttribute("tipos")
    public Map<String, String> tipos() {
        return ControllerUtils.listToMap(tipoArchivoPagosRepo.findAll(), "nombre");
    }

    @Transactional
    @GetMapping(PATH_PAGOS_NO_APLICADOS)
    public String pagosNoAplicados(Model model) {
        List<Pago> pagos = pagoRepo.findByEstadoPago(EstadoPago.NO_APLICADO);
        model.addAttribute("pagos", pagos);
        return "financiero-pagos-noaplicados";
    }

}
