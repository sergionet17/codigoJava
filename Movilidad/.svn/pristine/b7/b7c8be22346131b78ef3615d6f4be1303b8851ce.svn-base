package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.aut.UsuarioRepo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TurnoFirma;
import co.gov.movilidadbogota.sipa.data.biz.comp.TurnoFirmaHistorico;
import co.gov.movilidadbogota.sipa.data.biz.comp.TurnoFirmaHistoricoRepo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TurnoFirmaRepo;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Se encarga de administrar las asignaciones de los turnos para firma de las autoridades de tránsito.
 *
 * @author maria.rodriguez
 */
@Controller
public class TurnoFirmaController extends BaseController {

    public static final String CONTROLLER_PATH = "/turno-firma";
    public static final String CONTROLLER_PATH_NEW = CONTROLLER_PATH + "/new";
    public static final String CONTROLLER_PATH_SAVE = CONTROLLER_PATH + "/save";
    public static final String CONTROLLER_PATH_CONSULTA_HISTORICO = CONTROLLER_PATH + "/consulta-historico";
    public static final Logger log = LoggerFactory.getLogger(TurnoFirma.class);
    @Autowired
    UsuarioRepo usuarioRepo;
    @Autowired
    TurnoFirmaRepo turnoFirmaRepo;
    @Autowired
    TurnoFirmaHistoricoRepo turnoFirmaHistoricoRepo;
    @Autowired
    MessageSource messages;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = CONTROLLER_PATH_NEW)
    public String createNew(Model model, Locale locale) {

        List<TurnoFirma> turnosFirma = turnoFirmaRepo.findAll(new Sort(Sort.Direction.ASC,
                "desde",
                "autoridad.persona.primerApellido",
                "autoridad.persona.segundoApellido",
                "autoridad.persona.primerNombre",
                "autoridad.persona.segundoNombre"
        ));
        List<Usuario> autoridades = usuarioRepo.findByCargoStartingWith("Autoridad");
        for (Usuario u : autoridades) {

            if (turnosFirma.stream().anyMatch(x -> x.getAutoridad().getId().equals(u.getId())) == false)
                turnosFirma.add(new TurnoFirma(u));
        }
        model.addAttribute("turnosFirma", turnosFirma);
        model.addAttribute("turnoFirma", new TurnoFirma());
        model.addAttribute("autoridades", usuarioRepo.findByCargoStartingWith("Autoridad"));

        return "turno-firma-create";

    }
    @RequestMapping(value = CONTROLLER_PATH_SAVE, method = RequestMethod.POST)
    public String saveTurnos(HttpServletRequest req, Model model) throws IOException, ParseException {

        List<TurnoFirma> turnosFirma = new ArrayList<>();
        turnosFirma.clear();
        Map<String, String> map = new HashMap<>();
        // Let's obtains parameters name here!
        Enumeration enumeration = req.getParameterNames();
        while (enumeration.hasMoreElements()) {

            String parameterName = (String) enumeration.nextElement();
            String value = req.getParameter(parameterName);
            map.put(parameterName, value);

        }
        map.forEach((k, v) -> {
            if (k.endsWith(v)) {
                TurnoFirma tf = new TurnoFirma();
                map.forEach((name, value) -> {

                    if (name.startsWith("desde_") && name.endsWith(v) && StringUtils.isNotBlank(value) && !value.equals("____-__-__")) {

                        try {
                            tf.setDesde(Constants.DATE_FORMAT.parse(value));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (name.startsWith("hasta_") && name.endsWith(v) && StringUtils.isNotBlank(value) && !value.equals("____-__-__")) {
                        try {
                            tf.setHasta(Constants.DATE_FORMAT.parse(value));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (name.startsWith("autoridad_") && name.endsWith(v))
                        tf.setAutoridad(usuarioRepo.getOne(UUID.fromString(value)));

                    if (name.startsWith("suplente_") && name.endsWith(v) && !value.equals("none"))
                        tf.setSuplenteOficial(usuarioRepo.getOne(UUID.fromString(value)));
                });

                turnosFirma.add(tf);

            }
        });
        // Verififca si las fechas en el formulario se sobreponen
        Map result = validateDatesForm(turnosFirma);
        if (!result.isEmpty()) {

            model.addAttribute("errorMap", result);
            model.addAttribute("autoridades", usuarioRepo.findByCargoStartingWith("Autoridad"));
            model.addAttribute("turnosFirma", turnosFirma);
            return "turno-firma-create";
        }
        System.out.println("Guardando los turnos de firma");
        for (TurnoFirma tf : turnosFirma) {
            if (tf.getDesde() != null) {

                TurnoFirma tfOld = turnoFirmaRepo.findByAutoridadId(tf.getAutoridad().getId());
                if (tfOld != null) {
                    tf.setId(tfOld.getId());
                    turnoFirmaRepo.save(tf);
                } else {
                    tf.setId(UUID.randomUUID());
                    turnoFirmaRepo.save(tf);
                }

            }

        }
        return String.format("redirect:%s", CONTROLLER_PATH_NEW);
    }
    @RequestMapping(value = CONTROLLER_PATH_CONSULTA_HISTORICO)
    public String consultaHistorico(Model model, RedirectAttributes redirectAttributes, Locale locale) {

        List<TurnoFirmaHistorico> historico = turnoFirmaHistoricoRepo.findAll(new Sort(Sort.Direction.ASC,
                "desde",
                "autoridad.persona.primerApellido",
                "autoridad.persona.segundoApellido",
                "autoridad.persona.primerNombre",
                "autoridad.persona.segundoNombre"
        ));
        try {
            if (!historico.isEmpty())
                model.addAttribute("historico", historico);
        } catch (Exception ex) {
            if (!historico.isEmpty())
                model.addAttribute("sinResultados", "Aún no existen registros en el histórico.");
            else
                redirectAttributes.addAttribute(Constants.FLASH_MESSAGE_ERROR, "Ocurrió un error inesperado, intente de nuevo más tarde.");

        }
        return "turno-firma-historico";
    }
    /**
     * Permite validar si las fechas se sobreponen
     *
     * @param turnosFirma {@link TurnoFirma}
     * @return errors: Un mapa con los errores que deben ser devueltos a la vista.
     */
    public Map<String, String> validateDatesForm(List<TurnoFirma> turnosFirma) {

        Map<String, String> errors = new HashMap<>();

        Optional<TurnoFirma> tfOverlap = turnosFirma.stream()
                .reduce((TurnoFirma a, TurnoFirma b) -> {
                    if ((a.getDesde().after(b.getDesde()) && a.getDesde().before(b.getHasta()))
                            || (b.getDesde().after(a.getDesde()) && b.getDesde().before(a.getHasta()))) {
                        return b;
                    } else {
                        return new TurnoFirma();
                    }
                });

        tfOverlap.ifPresent(tfo -> {
            if (tfo.getDesde() != null)
                errors.put("desdeOverlap_" + tfo.getAutoridad().getId(), "No se permite este valor, la fecha se sobrepone");
        });
        return errors;
    }
}