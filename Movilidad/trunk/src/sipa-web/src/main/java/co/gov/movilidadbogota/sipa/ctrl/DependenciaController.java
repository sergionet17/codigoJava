package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Permiso;
import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.aut.UsuarioRepo;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.data.loc.Dependencia;
import co.gov.movilidadbogota.sipa.data.loc.DependenciaRepo;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;
import java.util.UUID;

/**
 * Controlador para administrar las dependencias
 *
 * @author lorenzo.pinango
 */
@Controller
@RequestMapping(value = DependenciaController.CONTROLLER_PATH)
public class DependenciaController extends BaseController {
    public static final String CONTROLLER_PATH = "/dependencia";
    public static final String CONTROLLER_PATH_LIST = "";
    public static final String CONTROLLER_PATH_CREATE = "/create";
    public static final String CONTROLLER_PATH_EDIT = "/edit";
    public static final String CONTROLLER_PATH_USER_VALID = "/userValidate";
    public static final String CONTROLLER_PATH_USER_SELECT = "/dependencias";

    private static final Logger LOGGER = LoggerFactory.getLogger(DependenciaController.class);

    @Autowired
    DependenciaRepo dependenciaRepo;
    @Autowired
    UsuarioRepo usuarioRepo;
    @Autowired
    MessageSource messages;

    /**
     * Lista todas las dependencias
     */
    @PreAuthorize("hasAuthority('" + Permiso.LISTAR_DEPENDENCIA + "')")
    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String list(Model model) {
        try {
            LOGGER.info("Buscar las dependencias");
            model.addAttribute("dependencias", dependenciaRepo.findAllDependenciasPrimarias());
            model.addAttribute("dependenciasSinResponsable", dependenciaRepo.findAllResponsableNull());
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "dependencia-list";
    }

    /**
     * Se inicializa el formulario para la captura de la información de la dependencia
     *
     * @return Se retorna a la pagina de registro la dependencia
     */
    @PreAuthorize("hasAuthority('" + Permiso.CREAR_DEPENDENCIA + "')")
    @GetMapping(value = CONTROLLER_PATH_CREATE)
    public String create(Model model, Locale locale) {
        try {
            //Inicializa la forma para realizar el registro de una dependencia
            model.addAttribute("dependenciaForm", new Dependencia());
            model.addAttribute("dependencias", ControllerUtils.listToMapWithDefault(dependenciaRepo.findAll(),
                    "nombre", messages, locale));
            model.addAttribute("usuarios", ControllerUtils.listToMapWithDefault(usuarioRepo.findAll(),
                    "persona.primerNombre,persona.segundoNombre,persona.primerApellido,persona.segundoApellido", messages,
                    locale));
            model.addAttribute("editar", 0);
            LOGGER.info("Formulario de registro de una dependencia");

        } catch (Exception e) {

            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "dependencia-create";
    }

    /**
     * Se inicializa el formulario para editar la información de una dependencia
     *
     * @return Se retorna a la pagina de editar la dependencia
     */
    @PreAuthorize("hasAuthority('" + Permiso.MODIFICAR_DEPENDENCIA + "')")
    @RequestMapping(path = CONTROLLER_PATH_EDIT + "/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") UUID id, Model model, Locale locale) {
        try {
            //Inicializa la forma para realizar la modificacion de una dependencia
            Dependencia dependencia = dependenciaRepo.findById(id);
            model.addAttribute("dependenciaForm", dependencia);
            model.addAttribute("dependencias", ControllerUtils.listToMapWithDefault(dependenciaRepo.findAllNotId(id),
                    "nombre", messages, locale));
            model.addAttribute("usuarios", ControllerUtils.listToMapWithDefault(usuarioRepo.findAll(),
                    "persona.primerNombre,persona.segundoNombre,persona.primerApellido,persona.segundoApellido",
                    messages, locale));
            model.addAttribute("editar", 1);
            LOGGER.info("Formulario de modificacion de una dependencia");

        } catch (Exception e) {

            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "dependencia-create";
    }

    /**
     * Redirecciona en el caso de que se intente ingresar al editar sin id
     *
     * @return string
     */
    @RequestMapping(value = CONTROLLER_PATH_EDIT, method = RequestMethod.GET)
    public String editHandler() {
        return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
    }

    /**
     * Se raliza las validaciones de tipo de dato y de negocio.
     * Se persiste una dependencia {@link Dependencia}
     *
     * @param dependencia
     * @param result
     * @param editar
     * @param model
     * @param redirectAttributes
     * @param locale
     * @return en caso de no pasar las validaciones de tipo de dato y de negocio se retorna al formulario de registro
     * en caso contrario se persiste un {@link Dependencia}
     */
    @PostMapping(CONTROLLER_PATH_LIST)
    public String persistirDependencia(
            @Valid @ModelAttribute("dependenciaForm") Dependencia dependencia,
            BindingResult result,
            @RequestParam("editar") Integer editar,
            Model model,
            RedirectAttributes redirectAttributes,
            Locale locale) {

        try {
            if (dependencia.getNombre() == null) {
                result.rejectValue("nombre", "dependencia.nombre.empty");
            } else if (dependencia.getNombre().isEmpty()) {
                result.rejectValue("nombre", "dependencia.nombre.empty");
            }
            if (result.hasErrors()) {
                if (LOGGER.isDebugEnabled())
                    LOGGER.debug(String.format("Se encontraron errores en las validaciones de forma: %s", result));
                if (editar == 0)
                    model.addAttribute("dependencias", ControllerUtils.listToMapWithDefault(dependenciaRepo.findAll(),
                            "nombre", messages, locale));
                else
                    model.addAttribute("dependencias", ControllerUtils.listToMapWithDefault(dependenciaRepo.findAllNotId(dependencia.getId()),
                            "nombre", messages, locale));
                model.addAttribute("usuarios", ControllerUtils.listToMapWithDefault(usuarioRepo.findAll(),
                        "persona.primerNombre,persona.segundoNombre,persona.primerApellido,persona.segundoApellido",
                        messages, locale));
                model.addAttribute("editar", editar);
                return "dependencia-create";
            }
            if (editar == 0)
                dependencia.setId(UUID.randomUUID());
            if (dependencia.getDependencia().getId() != null) {
                Dependencia dependenciaSuperior = dependenciaRepo.findById(dependencia.getDependencia().getId());
                if (dependenciaSuperior != null)
                    dependencia.setDependencia(dependenciaSuperior);
                else
                    dependencia.setDependencia(null);
            } else
                dependencia.setDependencia(null);
            Usuario responsable = null;
            if (dependencia.getResponsable().getId() != null) {
                responsable = usuarioRepo.findOne(dependencia.getResponsable().getId());
                if (responsable.getId() != null) {
                    // Se actualiza la dependencia anterior
                    Dependencia anterior = null;
                    if (editar == 0)
                        anterior = dependenciaRepo.findByResponsableId(responsable.getId());
                    else
                        anterior = dependenciaRepo.findByResponsableIdNotDependencia(responsable.getId(),
                                dependencia.getId());
                    if (anterior != null) {
                        anterior.setResponsable(null);
                        dependenciaRepo.save(anterior);
                    }
                    dependencia.setResponsable(responsable);
                } else
                    dependencia.setResponsable(null);
            } else
                dependencia.setResponsable(null);
            //Se guarda la información de la forma en el objeto Dependencia
            dependenciaRepo.save(dependencia);
            // Se actualiza la dependencia del usuario
            if (responsable != null) {
                responsable.setDependencia(dependencia);
                usuarioRepo.save(responsable);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
            return "dependencia-create";
        }

        if (editar == 0)
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                    messages.getMessage("dependencia.action.create.success", new Object[]{dependencia.getNombre()}, locale));
        else
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                    messages.getMessage("dependencia.action.edit.success", new Object[]{dependencia.getNombre()}, locale));
        return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
    }

    /**
     * Valida si el usuario es jefe de dependencia o si esta vinculado a una
     * dependencia diferente a la que sera el representante
     *
     * @param usuarioId     representa el identificador del usuario
     * @param dependenciaId representa el identificador del usuario
     * @return Retorna mensaje para la confirmacion
     */
    @GetMapping(value = CONTROLLER_PATH_USER_VALID + "/{usuarioId}/{dependenciaId}")
    @ResponseBody
    public String validarUsuario(@PathVariable("usuarioId") UUID usuarioId,
                                 @PathVariable("dependenciaId") String dependenciaId,
                                 Locale locale) {
        try {
            //Validamos si el usuario es jefe en ouna dependencia
            Dependencia dependencia = null;
            Usuario usuario = null;
            if (dependenciaId.equals("0")) {
                dependencia = dependenciaRepo.findByResponsableId(usuarioId);
                if (dependencia == null) {
                    usuario = usuarioRepo.findOne(usuarioId);
                    if (usuario.getDependencia() != null)
                        return messages.getMessage("dependencia.confirm.dependencia",
                                new Object[]{usuario.getPersona().obtenerNombreCompleto(),
                                        usuario.getDependencia().getNombre()}, locale);
                    else
                        return null;
                } else {
                    return messages.getMessage("dependencia.confirm.responsable",
                            new Object[]{dependencia.getResponsable().getPersona().obtenerNombreCompleto(),
                                    dependencia.getNombre()}, locale);
                }
            } else {
                dependencia = dependenciaRepo.findByResponsableIdNotDependencia(usuarioId, UUID.fromString(dependenciaId));
                if (dependencia == null) {
                    usuario = usuarioRepo.findOne(usuarioId);
                    if (usuario.getDependencia() != null) {
                        if (usuario.getDependencia().getId().equals(UUID.fromString(dependenciaId)))
                            return null;
                        else
                            return messages.getMessage("dependencia.confirm.dependencia",
                                    new Object[]{usuario.getPersona().obtenerNombreCompleto(),
                                            usuario.getDependencia().getNombre()}, locale);
                    } else
                        return null;
                } else {
                    return messages.getMessage("dependencia.confirm.responsable",
                            new Object[]{dependencia.getResponsable().getPersona().obtenerNombreCompleto(),
                                    dependencia.getNombre()}, locale);
                }
            }
        } catch (Exception e) {
            LOGGER.error(String.format("Mientras buscaba el usuario %s", usuarioId), e);
            return null;
        }
    }


    @GetMapping(CONTROLLER_PATH_USER_SELECT)
    public String dependencias(Model model) {
        model.addAttribute(
                "dependencias",
                dependenciaRepo.findAllDependenciasPrimarias()
        );
        return "dependencia-list";
    }

    @PostMapping(CONTROLLER_PATH_USER_SELECT)
    public String dependenciasPost(
            @ModelAttribute("idDependencia")
            @RequestParam("idDependencia")
                    UUID idDependencia,
            Model model
    ) {
        LOGGER.debug("Dependencia seleccionada: {}", idDependencia);
        model.addAttribute("idDependencia", idDependencia);
        return "prueba-dependencias-post";
    }

}
