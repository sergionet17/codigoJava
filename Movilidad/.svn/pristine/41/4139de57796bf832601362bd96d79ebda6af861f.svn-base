package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.cli.SipaServicesClient;
import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoRepository;
import co.gov.movilidadbogota.sipa.data.fin.EntradaRepo;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroUtils;
import co.gov.movilidadbogota.sipa.data.fin.VolantePago;
import co.gov.movilidadbogota.sipa.data.fin.VolantePagoRepo;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.data.gen.Parametro;
import co.gov.movilidadbogota.sipa.data.gen.ValorParametroRepo;
import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// TODO: Eliminar esta clase ya que la generación del volante de pago se debe invocar desde el controlador de comparendo
/**
 * Controlador  que genera el volante de pago de un comparendo.
 * Created by paola.charry on 22/06/2017.
 */
@Controller
@RequestMapping(value = VolantePagoController.CONTROLLER_PATH)
public class VolantePagoController extends UtilController {

    public static final String CONTROLLER_PATH = "/volante-create";
    public static final String CONTROLLER_PATH_GENERATE = "/generate";
    public static final String CONTROLLER_CANCELAR = "/cancelar";
    public static final String CONTROLLER_PATH_VOLANTE = "volante-pago";

    private static final Logger logger = LoggerFactory.getLogger(CursoAsistenciaController.class);

    @Autowired
    ValorParametroRepo valorParametroRepo;
    @Autowired
    private ComparendoRepository comparendoRepository;
    @Autowired
    private VolantePagoRepo volantePagoRepo;
    @Autowired
    private EntradaRepo entradaRepo;

    @Autowired
    private SipaServicesClient sipaServicesClient;

    /**
     * Consulta la información del comparendo y los valores a pagar
     *
     * @param id    Identificador del comparendo a generar el volante de pago
     * @param model
     * @return Formulario con la lista de los cursos pedagogicos  registrados vigentes
     */
    @RequestMapping(value = "/{id}")
    public String consultarValores(@PathVariable("id") UUID id, Model model) {

        try {
            List<String> valores = new ArrayList<>();
            List<List> valoresTotales = new ArrayList<>();
            //Se consulta el comparendo.
            Comparendo comparendo = comparendoRepository.findOne(id);

            model.addAttribute("comparendo", comparendo);
            //Calcula el porcentaje de descuento de acuerdo al tipo de comparendo y su fecha de imposicion y notificacion
            BigDecimal porcentajeDescuento = porcentajeDescuento(comparendo);
            //Se calcula el saldo de la deuda
            BigDecimal saldo = FinancieroUtils.saldoDeudaComparendo(comparendo.getId(), entradaRepo);
            //TODO Esta devolviendo el valor  saldo del comparendo en cero valor temporal
            saldo = comparendo.getValorNominal();
            //Se calcula el valor del descuento de acuerdo al saldo
            BigDecimal descuento = valorDescuento(saldo, porcentajeDescuento);
            //Consulta los cursos vigentes y con cupos

            Date fechaDescuento = fechaMaximaDescuento(comparendo);

            BigDecimal intereses = FinancieroUtils.interesesDeudaComparendo(comparendo.getId(), entradaRepo);

            model.addAttribute("valorDescuento", descuento);
            model.addAttribute("porcentajeDescuento", new BigDecimal(
                    valorParametroRepo.findValorVigenteByClave(
                            Parametro.PORCENTAJE_DESCUENTO_50, new Date()).getValor()).multiply(new BigDecimal("100")));

            model.addAttribute("fechaDescuento", fechaDescuento);
            model.addAttribute("valor", descuento);

            model.addAttribute("fechaPago", comparendo.getFechaImposicion());
            model.addAttribute("interes", intereses);

        } catch (Exception e) {
            logger.error("Error mientras se realiza la generación del volante de pago", e);
            return "volante-create";
        }
        return "volante-create";
    }

    /**
     * Se encarga de validar y generar el volante de pago
     *
     * @param abono              Cantidad de abono a cancelar
     * @param valor              Valor de pago
     * @param id                 Identificador unico del comparendo
     * @param redirectAttributes Permite retornar los mensajes de exito o fracaso
     * @return Se devuelve a la pagina donde se invoco
     */
    @RequestMapping(value = CONTROLLER_PATH_GENERATE + "/{abono}/{valorDescuento}/{id}")
    public ResponseEntity<Resource> generarVolante(@Valid @ModelAttribute("abono") BigDecimal abono,
                                                   @PathVariable(value = "valorDescuento") String valor,
                                                   @PathVariable(value = "id") UUID id,
                                                   RedirectAttributes redirectAttributes) {
        try {
            if (BigDecimal.ZERO.equals(abono) || BigDecimal.ZERO.equals(new BigDecimal(valor))) {
                redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_ERROR, "Error mientras se realiza" +
                        " la toma de asistencia y genera el certificado");

            } else {
                Comparendo comparendo = comparendoRepository.findOne(id);
                VolantePago volantePago = new VolantePago();
                volantePago.setAbono(abono);

                return generarVolantePago(valor, volantePago, comparendo, redirectAttributes);
            }
        } catch (Exception e) {
            logger.error("Error mientras se realiza la toma de asistencia y genera el certificado", e);
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_ERROR, "Error mientras se realiza la toma" +
                    " de asistencia y genera el certificado");
        }
        return null;
    }

    /**
     * Genera el reporte del volante de pago en formato PDF
     *
     * @param valor              Valor de pago
     * @param comparendo         objeto de comparendo
     * @param redirectAttributes Permite retornar los mensajes de exito o fracaso
     */
    private ResponseEntity<Resource> generarVolantePago(String valor,
                                                        VolantePago volantePago,
                                                        Comparendo comparendo,
                                                        RedirectAttributes redirectAttributes) {
    /*
        try {

            List<Object> data = new ArrayList<>();
            ReportBuilder reportBuilder = new ReportBuilder();
            reportBuilder.templatePath = "classpath:reports";
            Map<String, Object> parametros = new HashMap<>();
            data.add(comparendo);
            //Consulta el numero de comparendo unico nacional
            String comparendoCompleto = generarNumeroComparendoCompleto(comparendo.getNumero().getConsecutivo(), Constants.ORGANISMO_TRANSITO_SDM);
            //Carga la informacion de la fecha a pagar
            parametros.put("comparendo_completo", comparendoCompleto);
            if (!volantePago.getAbono().equals(BigDecimal.ZERO)) {
                //Carga la información del valor a cancelar
                parametros.put("valor_pago", volantePago.getAbono().toString());
            } else {
                //Carga la información del valor a cancelar
                parametros.put("valor_pago", valor);
            }
            //Envia la fecha de generacion del volante de pago
            DateFormat formatter = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
            String fechaActual = formatter.format(new Date());
            parametros.put("fecha_hora_actual", fechaActual);
            formatter = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN_DDMMYY);
            fechaActual = formatter.format(new Date());
            parametros.put("fecha_actual", fechaActual);
            formatter = new SimpleDateFormat("ddMMyy");
            fechaActual = formatter.format(new Date());
            //Genera codigo de barras para le recaudo
            String codigoBarras = generarCodigoBarras(fechaActual, valor);
            //TODO hace falta aclarar el tipo de barcode a generar
            parametros.put("codigo_barras", codigoBarras);
            //Generacion del numero de volante de pago
            String numeroVolantePago = comparendoCompleto.concat(fechaActual);
            parametros.put("volante_pago", numeroVolantePago);
            //Genera el PDF de acuerdo a la data y parametros enviados
            byte[] bytes = reportBuilder.pdf(
                    "volante-pago-contravensional",
                    data,
                    parametros
            );
            // Volcado de los bytes a un archivo temporal
            try {
                File file = File.createTempFile(String.format("sipa-reports-%s", comparendo.getNumero().getValor()), ".pdf");
                FileOutputStream fw = new FileOutputStream(file);
                fw.write(bytes);
                //Se crea un objeto de la clase Documento para guardar la información
                FileInputStream fis = new FileInputStream(file);

                //Se persiste el volante de pago
                volantePago.setId(numeroVolantePago);
                volantePagoRepo.save(volantePago);

                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(file.length())
                        .contentType(MediaType.parseMediaType(Constants.MIME_TYPE_PDF))
                        .body(resource);
            } catch (IOException e) {
                logger.error("Error mientras se realiza la generacion del volante de pago", e);
                redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_ERROR, "Error mientras se realiza la generacion del volante de pago");
            }

        } catch (Exception e) {
            logger.error("Error mientras se realiza la toma de asistencia y genera el certificado", e);
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_ERROR, "Error mientras se realiza la toma de asistencia y genera el certificado");

        }
        */
        return null;
    }

    /**
     * Generar la estructura la cadena de caracteres para construir el codigo de barras para el recaudo contranvensional
     *
     * @param fecha Fecha maximo de pago
     * @param valor Valor a cancelar
     * @return Devuelve una cadena de caracteres que representara el codigo de barras
     */
    private String generarCodigoBarras(String fecha, String valor) {
        //TODO se debe generar los siguientes identificadores de forma global
        //Numero de cuenta
        String identificadorEmpresa = Constants.RECAUDO_IDENTIFICADOR_EMPRESA;
        //Numero de volante de pago
        String referenciaPago = Constants.RECAUDO_REFENCIA_PAGO;
        //Valor a cancelar
        String valorPagar = Constants.RECAUDO_VALOR_PAGO;
        //Fecha maxima de pago
        String fechaMaximoPago = Constants.RECAUDO_FECHA_PAGO;

        StringBuilder codigoBarras = new StringBuilder();
        codigoBarras.append(identificadorEmpresa);
        codigoBarras.append(referenciaPago);
        codigoBarras.append(valorPagar.concat(valor.replace(".", "")));
        codigoBarras.append(fechaMaximoPago.concat(fecha));

        return codigoBarras.toString();
    }

    /**
     * Funcion que retorna al formulario anterior
     *
     * @return
     */
    @RequestMapping(value = CONTROLLER_CANCELAR + "/{numero}")
    private String cancelar(@PathVariable(value = "numero") String numero) {
        return String.format("redirect:/comparendo/general/%s", numero);
    }


    /**
     * Genera el reporte del volante de pago en formato PDF
     *
     * @param comparendoId              Id del comparendo
     * @param redirectAttributes Permite retornar los mensajes de exito o fracaso
     */
    @RequestMapping(value = CONTROLLER_PATH_VOLANTE + "/{id}")
    private ResponseEntity<Resource> generarVolantePago(@PathVariable(value = "id")UUID comparendoId,
                                                        RedirectAttributes redirectAttributes) {

        try {
            sipaServicesClient.getVolantePago().generarVolantePago(comparendoId);
            CommandContext context = new CommandContext();
            context.put("idComparendo", comparendoId);
            sipaServicesClient.getVolantePago().generarVolantePago(comparendoId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
