package co.gov.movilidadbogota.sipa.serv.titulo;

import co.gov.movilidadbogota.sipa.data.biz.coa.*;
import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoRepository;
import co.gov.movilidadbogota.sipa.data.doc.Actuacion;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.doc.Expediente;
import co.gov.movilidadbogota.sipa.data.doc.TipoDocumental;
import co.gov.movilidadbogota.sipa.data.fin.EntradaRepo;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroUtils;
import co.gov.movilidadbogota.sipa.serv.api.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TituloEjecutivoServiceImpl implements TituloEjecutivoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TituloEjecutivoServiceImpl.class);

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private TituloEjecutivoRepo tituloEjecutivoRepo;
    @Autowired
    private DocumentoService documentoService;
    @Autowired
    private ComparendoRepository comparendoRepository;
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
        if (comparendo == null) {
            String msg = "No se encontró el comparendo " + comparendoId.toString();
            LOGGER.error(msg);
            throw new TituloEjecutivoException(msg);
        }
        //Se verifica si ya tiene un titulo ejecutivo el comparendo
        if (tituloEjecutivoRepo.countByComparendoId(comparendo.getId()) > 0) {
            String msg = "Ya existe un título ejecutivo generado para el comparendo " + comparendoId.toString();
            LOGGER.error(msg);
            throw new TituloEjecutivoException(msg);
        }
        //Se obtiene los documentos
        String idResolucion = context.get(ComparendoService.CTX_ID_DOCUMENTO_RESOLUCION_AUTOMATICA_FALLO).toString();
        String idConstancia = context.get(ComparendoService.CTX_ID_DOCUMENTO_CONSTANCIA_EJECUTORIA_AUTOMATICA).toString();

        Documento resolucion = null;
        Documento constancia = null;
        try {
            resolucion = documentoService.getDocumento(UUID.fromString(idResolucion));
            constancia = documentoService.getDocumento(UUID.fromString(idConstancia));
        } catch (DocumentoException e) {
            String msg = String.format("Error obteniendo los documentos asociados al comparendo: '%s'", comparendoId);
            LOGGER.warn(msg, e);
            throw new TituloEjecutivoException(msg, e);
        }

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
            String msg = String.format(
                    "Ocurrió un error registrando el titulo ejecutivo para el comparendo: '%s'",
                    comparendoId.toString());
            LOGGER.error(msg);
            throw new TituloEjecutivoException(msg, cause);
        }

        context.put(CTX_TITULO_EJECUTIVO_ID, tituloEjecutivo.getId().toString());
        return context;
    }

    @Override
    public CommandContext iniciarProcesoCobroPersuasivo(UUID tituloEjecutivoId, CommandContext context) throws TituloEjecutivoException {
        //Se obtiene el titulo ejecutivo
        TituloEjecutivo tituloEjecutivo = tituloEjecutivoRepo.findOne(tituloEjecutivoId);
        if (tituloEjecutivo == null) {
            String msg = "No se encontró el título ejecutivo " + tituloEjecutivoId.toString();
            LOGGER.error(msg);
            throw new TituloEjecutivoException(msg);
        }

        //Se marca el título para iniciar cobro persuasivo
        try {
            tituloEjecutivo.setEtapaTituloEjecutivo(EtapaTituloEjecutivo.COBRO_PERSUASIVO);
            tituloEjecutivoRepo.save(tituloEjecutivo);
        } catch (Exception cause) {
            String msg = String.format(
                    "Ocurrió un error cambiando la etapa del título ejecutivo: '%s'",
                    tituloEjecutivoId.toString());
            LOGGER.error(msg);
            throw new TituloEjecutivoException(msg, cause);
        }
        return context;
    }
}
