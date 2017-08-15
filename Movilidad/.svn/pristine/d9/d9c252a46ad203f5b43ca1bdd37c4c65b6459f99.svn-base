package co.gov.movilidadbogota.sipa.serv.titulo;

import co.gov.movilidadbogota.sipa.data.biz.coa.*;
import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoRepository;
import co.gov.movilidadbogota.sipa.data.doc.*;
import co.gov.movilidadbogota.sipa.data.fin.EntradaRepo;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroUtils;
import co.gov.movilidadbogota.sipa.serv.api.*;
import co.gov.movilidadbogota.sipa.serv.comparendos.FormatoComparendoChainValidationFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TituloEjecutivoServiceImpl implements TituloEjecutivoService {

    @Autowired
    FormatoComparendoChainValidationFactory chainValidationFactory;

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    TituloEjecutivoRepo tituloEjecutivoRepo;
    @Autowired
    DocumentoService documentoService;
    @Autowired
    ComparendoRepository comparendoRepository;
    @Autowired
    DocumentoRepo documentoRepo;
    @Autowired
    private FuenteTituloEjecutivoRepo fuenteTituloEjecutivoRepo;
    @Autowired
    private DeudorRepo deudorRepo;
    @Autowired
    private TituloEjecutivoReferenciaRepo tituloEjecutivoReferenciaRepo;
    @Autowired
    private EntradaRepo entradaRepo;

    @Override
    public CommandContext registrarTituloEjecutivo(UUID comparendoId, CommandContext context) throws TituloEjecutivoException {
        //Se obtiene el comparendo
        Comparendo comparendo = comparendoRepository.findOne(comparendoId);
        if (comparendo == null)
            throw new TituloEjecutivoException("No se encontró el comparendo " + comparendoId.toString());
        //Se verifica si ya tiene un titulo ejecutivo el comparendo
        if (tituloEjecutivoRepo.countByComparendoId(comparendo.getId()) > 0)
            throw new TituloEjecutivoException(
                    "Ya existe un título ejecutivo generado para el comparendo " + comparendoId.toString());
        String idResolucion = context.get(ComparendoService.CTX_RESOLUCION_AUTOMATICA_FALLO).toString();
        Documento resolucion = documentoRepo.findOne(UUID.fromString(idResolucion));
        if (resolucion == null)
            throw new TituloEjecutivoException(
                    "No se encontró el documento resolución para el comparendo " + comparendoId.toString());
        String idConstancia = context.get(ComparendoService.CTX_CONSTANCIA_EJECUTORIA_AUTOMATICA).toString();
        Documento constancia = documentoRepo.findOne(UUID.fromString(idResolucion));
        if (constancia == null)
            throw new TituloEjecutivoException(
                    "No se encontró el documento constancia ejecutoria para el comparendo " + comparendoId.toString());

        //Se guarda el título ejecutivo
        TituloEjecutivo tituloEjecutivo = new TituloEjecutivo();
        try {
            tituloEjecutivo.setId(UUID.randomUUID());
            List<UUID> referencias = new ArrayList<>();
            List<Documento> documentos = new ArrayList<>();
            referencias.add(comparendo.getId());
            //Se calcula el saldo de la deuda
            BigDecimal saldo = FinancieroUtils.saldoDeudaComparendo(comparendo.getId(), entradaRepo);
            tituloEjecutivo.setValor(saldo);
            tituloEjecutivo.setFechaTitulo(new Date());
            tituloEjecutivo.setFechaEjecutoria(new Date());
            tituloEjecutivo.setEstado(EstadoTitulo.VIGENTE);
            tituloEjecutivo.setFuenteTituloEjecutivo(
                    fuenteTituloEjecutivoRepo.findByIdAndFecha(
                            Integer.parseInt(FuenteTituloEjecutivo.CONTRAVENCIONES), new Date()));
            //TODO definir dependencia
            tituloEjecutivo.setDependencia(null);
            List<Deudor> deudores = new ArrayList<>();
            Deudor deudor = new Deudor();
            deudor.setId(UUID.randomUUID());
            deudor.setPersona(comparendo.getInfractor());
            deudor.setPorcentajeParticipacion(100);
            deudores.add(deudor);
            tituloEjecutivo.setDeudores(deudores);
            tituloEjecutivo.setEtapaTituloEjecutivo(EtapaTituloEjecutivo.INSTACIA_PROCESO_COACTIVO);
            deudorRepo.save(deudor);
            documentos.add(resolucion);
            documentos.add(constancia);
            tituloEjecutivo.setSoporte(documentos);
            tituloEjecutivoRepo.save(tituloEjecutivo);
            TituloEjecutivoReferencia tituloEjecutivoReferencia = new TituloEjecutivoReferencia();
            tituloEjecutivoReferencia.setId(UUID.randomUUID());
            tituloEjecutivoReferencia.setTituloEjecutivo(tituloEjecutivo);
            tituloEjecutivoReferencia.setReferencia(comparendo.getId());
            tituloEjecutivoReferenciaRepo.save(tituloEjecutivoReferencia);
            //Se crea el expediente
            Expediente expediente = new Expediente(tituloEjecutivo.getId(), TipoDocumental.TITULO_EJECUTIVO);
            Expediente expedienteResponse = documentoService.addExpediente(
                    expediente
            );
            // Se asocian los documentos al expediente
            documentoService.asociarDocumento(resolucion, expedienteResponse, Actuacion.REGISTRO_TITULO_EJECUTIVO);
            documentoService.asociarDocumento(constancia, expedienteResponse, Actuacion.REGISTRO_TITULO_EJECUTIVO);
        } catch (Exception cause) {
            throw new TituloEjecutivoException(String.format(
                    "Ocurrió un error registrando el titulo ejecutivo para el comparendo: '%s'",
                    comparendoId.toString()), cause);
        }

        context.put(CTX_TITULO_EJECUTIVO_ID, tituloEjecutivo.getId().toString());
        return context;
    }

    @Override
    public CommandContext iniciarProcesoCobroPersuasivo(UUID tituloEjecutivoId, CommandContext context) throws TituloEjecutivoException {
        //Se obtiene el titulo ejecutivo
        TituloEjecutivo tituloEjecutivo = tituloEjecutivoRepo.findOne(tituloEjecutivoId);
        if (tituloEjecutivo == null)
            throw new TituloEjecutivoException("No se encontró el título ejecutivo " + tituloEjecutivoId.toString());

        //Se marca el título para iniciar cobro persuasivo
        try {
            tituloEjecutivo.setEtapaTituloEjecutivo(EtapaTituloEjecutivo.COBRO_PERSUASIVO);
            tituloEjecutivoRepo.save(tituloEjecutivo);
        } catch (Exception cause) {
            throw new TituloEjecutivoException(String.format(
                    "Ocurrió un error cambiando la etapa del título ejecutivo: '%s'",
                    tituloEjecutivoId.toString()), cause);
        }
        return context;
    }
}
