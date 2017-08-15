package co.gov.movilidadbogota.sipa.serv.comparendos.extractor;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoRepository;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.serv.api.Command;
import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ComparendoExtractor implements Command   {

    @Autowired
    ComparendoRepository comparendoRepository;

    @Override
    public void execute(CommandContext ctx) throws Exception {
        String idComparendo = ctx.get("idComparendo").toString();
        Comparendo comparendo = comparendoRepository.getOne(UUID.fromString(idComparendo));
        ctx.put("comparendo", comparendo);

        /*
        List<Object> data = new ArrayList<>();
        ReportBuilder reportBuilder = new ReportBuilder();
        reportBuilder.templatePath = "classpath:reports";
        Map<String, Object> parametros = new HashMap<>();
        data.add(comparendo);

        //Consulta el numero de comparendo unico nacional
        String comparendoCompleto = generarNumeroComparendoCompleto(comparendo.getNumero().getConsecutivo(), Constants.ORGANISMO_TRANSITO_SDM);
        //Carga la informacion de la fecha a pagar
        parametros.put("comparendo_completo", comparendoCompleto);

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
       // String codigoBarras = generarCodigoBarras(fechaActual, valor);
       // parametros.put("codigo_barras", codigoBarras);
        //Generacion del numero de volante de pago
        String numeroVolantePago = comparendoCompleto.concat(fechaActual);
        parametros.put("volante_pago", numeroVolantePago);

      //  VolantePago volantePago = new VolantePago();
       // volantePago.setComparendo();
        ctx.put("volante", parametros);
        */
    }


    /**
     * Genera el numero completo del comparendo unico nacional de 20 digitos
     *
     * @param numero            Entero que corresponde a la serie unica dada por el RUNT
     * @param organismoTransito Organismo de transito quien registra el comparendo
     * @return Retorna un numero entero que corresponde al comparendo
     */
    String generarNumeroComparendoCompleto(Integer numero, String organismoTransito) {

        //Completa de ceros a la izquierda para completa el numero con 12 digitos
        String result = StringUtils.leftPad(numero.toString(), 12, "0");
        //Retorna el organismo de transito concatenado con el numero de comparendo
        return organismoTransito.concat(result);
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

}
