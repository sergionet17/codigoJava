package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoRepository;
import co.gov.movilidadbogota.sipa.data.biz.gen.Vehiculo;
import co.gov.movilidadbogota.sipa.data.biz.gen.VehiculoConsulta;
import co.gov.movilidadbogota.sipa.data.biz.gen.VehiculoForm;
import co.gov.movilidadbogota.sipa.data.biz.gen.VehiculoRepo;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumentoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * VehiculoController administra las solicitudes realtivas a vehículos
 *
 * @author maria.rodriguez
 */
@Controller
public class VehiculoController extends BaseController {

    public static final String CONTROLLER_PATH = "/vehiculo";
    public static final String CONTROLLER_PATH_CONSULTA = CONTROLLER_PATH + "/consulta";
    public static final String CONTROLLER_PATH_BUSCAR = CONTROLLER_PATH + "/consulta/buscar";
    public static final String CONTROLLER_PATH_GENERAL = CONTROLLER_PATH + "/general";
    public static final String CONTROLLER_PATH_ASOCIADOS = CONTROLLER_PATH + "/comparendos-asociados";
    public static final String CONTROLLER_PATH_REINCIDENCIAS = CONTROLLER_PATH + "/reincidencias";
    private static final Logger LOG = LoggerFactory.getLogger(VehiculoController.class);
    @Autowired
    private
    TipoDocumentoRepo tipoDocumentoRepo;
    @Autowired
    private
    VehiculoFormValidator vehiculoFormValidator;
    @Autowired
    private
    VehiculoConsulta vehiculoConsulta;
    @Autowired
    private
    VehiculoRepo vehiculoRepo;
    @Autowired
    private
    ComparendoRepository comparendoRepository;
    @Autowired
    private
    MessageSource messages;

    public VehiculoController(TipoDocumentoRepo tipoDocumentoRepo, VehiculoFormValidator vehiculoFormValidator, VehiculoConsulta vehiculoConsulta, VehiculoRepo vehiculoRepo) {
        this.tipoDocumentoRepo = tipoDocumentoRepo;
        this.vehiculoFormValidator = vehiculoFormValidator;
        this.vehiculoConsulta = vehiculoConsulta;
        this.vehiculoRepo = vehiculoRepo;
    }

    /**
     * permite realizar la consulta de vehículos.
     *
     * @param model Defines a holder for model attributes
     * @return La vista con las opciones de búsqueda.
     */
    @GetMapping(value = CONTROLLER_PATH_CONSULTA)
    public String consulta(Model model) {


        List<TipoDocumento> tipoDocumento = tipoDocumentoRepo.findAll();
        model.addAttribute("tipoDocumento", tipoDocumento);
        model.addAttribute("vehiculoForm", new VehiculoForm());

        return "vehiculo-consulta";
    }

    /**
     * Realiza la búsqueda del vehiculo deacuerdo a los criterios de búsqueda ingresados.
     *
     * @param vehiculoForm Objeto que modela los coriterios de búsqueda.
     * @param result       Los errores devueltos por el formulario.
     * @param model Defines a holder for model attributes
     * @return La vista con los resultados de búsqueda o la vista de consulta informado los errores encontrados en la consulta.
     */
    @PostMapping(value = CONTROLLER_PATH_BUSCAR)
    public String buscar(@Valid @ModelAttribute("vehiculoForm") VehiculoForm vehiculoForm, BindingResult result, Model model) {

        vehiculoFormValidator.validate(vehiculoForm, result);
        if (result.hasErrors()) {
            List<TipoDocumento> tipoDocumento = tipoDocumentoRepo.findAll();
            model.addAttribute("tipoDocumento", tipoDocumento);
            model.addAttribute("vehiculoForm", vehiculoForm);
        }

        try {
            List<Vehiculo> vehiculos = vehiculoConsulta.findVehiculo(vehiculoForm);
            if (!vehiculos.isEmpty()) {
                List<TipoDocumento> tipoDocumento = tipoDocumentoRepo.findAll();
                model.addAttribute("tipoDocumento", tipoDocumento);
                model.addAttribute("vehiculos", vehiculos);
            } else {
                List<TipoDocumento> tipoDocumento = tipoDocumentoRepo.findAll();
                model.addAttribute("tipoDocumento", tipoDocumento);
                model.addAttribute("sinresultados", "sinresultados");
            }

        } catch (Exception ex) {
            List<TipoDocumento> tipoDocumento = tipoDocumentoRepo.findAll();
            model.addAttribute("tipoDocumento", tipoDocumento);
            model.addAttribute("error", "Ocurrió un error insesperado,intente más tarde");
        }


        return "vehiculo-consulta";
    }

    /**
     * @param id    del vehiculo
     * @param model Defines a holder for model attributes
     * @return La vista general de vehículo.
     */
    @GetMapping(value = CONTROLLER_PATH_GENERAL + "/{id}")
    public String general(@PathVariable(value = "id") UUID id, Model model) {

        Vehiculo vehiculo = vehiculoRepo.findOne(id);

        try {
            if (vehiculo != null) {
                Integer numComparendos = comparendoRepository.countByVehiculoId(id);
                model.addAttribute("numComparendos", numComparendos);
                model.addAttribute("activePill", "consulta");
                model.addAttribute("vehiculo", vehiculo);

            } else {
                model.addAttribute("sinresultados", "sinresultados");
                model.addAttribute("numComparendos", 0);
                model.addAttribute("activePill", "consulta");
            }
        } catch (Exception ex) {
            model.addAttribute("error", "Ocurrió un error inesperado, intente más tarde");
            model.addAttribute("numComparendos", 0);
            model.addAttribute("activePill", "consulta");
        }


        return "vehiculo-general";

    }

    @GetMapping(value = CONTROLLER_PATH_ASOCIADOS + "/{id}")
    public String comparendos(@PathVariable(value = "id") UUID id, Model model) {

        Vehiculo vehiculo = vehiculoRepo.findOne(id);
        List<Comparendo> comparendos = comparendoRepository.findByVehiculoId(id);
        try {
            if (!comparendos.isEmpty()) {
                Integer numComparendos = comparendoRepository.countByVehiculoId(id);
                model.addAttribute("numComparendos", numComparendos);
                model.addAttribute("activePill", "asociados");
                model.addAttribute("vehiculo", vehiculo);
                model.addAttribute("comparendos", comparendos);
            } else {
                model.addAttribute("numComparendos", 0);
                model.addAttribute("activePill", "asociados");
                model.addAttribute("vehiculo", vehiculo);
                model.addAttribute("sinresultados", "sinresultados");

            }

        } catch (Exception ex) {
            LOG.info("Ocurrio un error", ex);
            model.addAttribute("error", "Ocurrio un error inesperado, intente más tarde");
            model.addAttribute("numComparendos", 0);
            model.addAttribute("activePill", "asociados");
            model.addAttribute("vehiculo", vehiculo);

        }
        return "vehiculo-comparendos";
    }

    @GetMapping(value = CONTROLLER_PATH_REINCIDENCIAS + "/{id}")
    public String reincidencias(Model model) {
        model.addAttribute("tab", "reincidencias");
        return "vehiculo-reincidencias";
    }


}
